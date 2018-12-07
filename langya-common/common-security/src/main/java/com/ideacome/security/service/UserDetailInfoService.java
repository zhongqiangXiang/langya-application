package com.ideacome.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.ideacome.base.enums.YesOrNo;
import com.ideacome.common.db.domain.SysFunction;
import com.ideacome.common.db.domain.SysFunctionExample;
import com.ideacome.common.db.domain.SysFunctionUserPermission;
import com.ideacome.common.db.domain.SysFunctionUserPermissionExample;
import com.ideacome.common.db.domain.SysRole;
import com.ideacome.common.db.domain.SysRoleFunction;
import com.ideacome.common.db.domain.SysRoleFunctionExample;
import com.ideacome.common.db.domain.SysRoleUser;
import com.ideacome.common.db.domain.SysRoleUserExample;
import com.ideacome.common.db.domain.SysUser;
import com.ideacome.common.db.domain.SysUserExample;
import com.ideacome.common.db.mapper.SysFunctionMapper;
import com.ideacome.common.db.mapper.SysFunctionUserPermissionMapper;
import com.ideacome.common.db.mapper.SysRoleFunctionMapper;
import com.ideacome.common.db.mapper.SysRoleMapper;
import com.ideacome.common.db.mapper.SysRoleUserMapper;
import com.ideacome.common.db.mapper.SysUserMapper;
import com.ideacome.security.vo.UserDetailVO;
import com.ideacome.security.vo.UserDetailVO.SysFunctionWithFuncionPermission;
import com.ideacome.security.vo.UserDetailVO.SysRoleWithFunction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailInfoService {
	@Autowired
	private SysFunctionUserPermissionMapper sysFunctionUserPermissionMapper;
	@Autowired
	private SysFunctionMapper sysFunctionMapper;
	@Autowired
	private SysRoleFunctionMapper sysRoleFuncionMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/**
	 * 此处注意：user_name 在数据库中需要唯一存储！！！！！！！！
	 * @param userNmae
	 * @return
	 */
	public UserDetailVO getUserDetailVO(String userNmae){
		SysUserExample sysUserExample = new SysUserExample();
		sysUserExample.createCriteria().andSysFlagEqualTo(YesOrNo.yes.getValue()).andUserNameEqualTo(userNmae);
		sysUserExample.setOrderByClause("modify_time desc,modify_time desc");
		List<UserDetailVO> userDetailVOList = getUserDetailVOSelective(sysUserExample);
		if(CollectionUtils.isEmpty(userDetailVOList)){
			return null;
		}
		return userDetailVOList.get(0);
	}
	
	private List<UserDetailVO> getUserDetailVOSelective(SysUserExample sysUserExample){
		log.info("UserDetailInfoService.getUserDetailVOSelective：sysUserExample-{}",JSON.toJSONString(sysUserExample));
		List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
		if(CollectionUtils.isEmpty(sysUsers)){
			return null;
		}
		List<UserDetailVO> userDetailVOs = new ArrayList<>();
		sysUsers.forEach(sysUser->{
			UserDetailVO vo = new UserDetailVO();
			vo.setSysUser(sysUser);
			
			List<SysRoleWithFunction> sysRoleWithFunctionList = new ArrayList<>();
			List<SysRole> sysRoleList = getSysRoleSelective(sysUser);
			if(! CollectionUtils.isEmpty(sysRoleList)){
				sysRoleList.forEach(obj -> {
					
					SysRoleWithFunction sysRoleWithFunction = new SysRoleWithFunction();
					List<SysFunctionWithFuncionPermission> sysFunctionWithFuncionPermissionList = new ArrayList<>();
					
					List<SysFunction> sysFunctionList = getSysFunctionSelective(obj);
					if(! CollectionUtils.isEmpty(sysFunctionList)){
						sysFunctionList.forEach(sysFunction -> {
							SysFunctionUserPermission sysFunctionUserPermission = getSysFunctionUserPermission(sysFunction.getId(),sysUser.getId());
							if(sysFunctionUserPermission != null){
								SysFunctionWithFuncionPermission sysFunctionWithFuncionPermission = new SysFunctionWithFuncionPermission();
								sysFunctionWithFuncionPermission.setSysFunction(sysFunction);
								sysFunctionWithFuncionPermission.setSysFunctionUserPermission(sysFunctionUserPermission);
								sysFunctionWithFuncionPermissionList.add(sysFunctionWithFuncionPermission);
							}
						});
					}
					sysRoleWithFunction.setSysFunctionWithFuncionPermissionList(sysFunctionWithFuncionPermissionList);
					sysRoleWithFunction.setSysRole(obj);
					
					sysRoleWithFunctionList.add(sysRoleWithFunction);
				});
			}
			
			vo.setSysRoleWithFunctionList(sysRoleWithFunctionList);
			
			userDetailVOs.add(vo);
		});
		
		
		return userDetailVOs;
	}
	
	private SysFunctionUserPermission getSysFunctionUserPermission(Integer sysFunctionId,Integer userId){
		SysFunctionUserPermissionExample sysFunctionUserPermissionExample = new SysFunctionUserPermissionExample();
		sysFunctionUserPermissionExample.createCriteria().andSysFlagEqualTo(YesOrNo.yes.getValue()).andFunctionIdEqualTo(sysFunctionId).andUserIdEqualTo(userId);
		List<SysFunctionUserPermission> sysFunctionPermissionList = getSysFunctionUserPermissionSelective(sysFunctionUserPermissionExample);
		if(CollectionUtils.isEmpty(sysFunctionPermissionList)){
			return null;
		}
		return sysFunctionPermissionList.get(0);
	}
	
	private List<SysRole> getSysRoleSelective(SysUser sysUser){
		SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
		sysRoleUserExample.createCriteria().andUserIdEqualTo(sysUser.getId()).andSysFlagEqualTo(YesOrNo.yes.getValue()).andValidFlagEqualTo(YesOrNo.yes.getValue());
		List<SysRoleUser> sysRoleUserList = getSysRoleUserSelective(sysRoleUserExample);
		if(CollectionUtils.isEmpty(sysRoleUserList)){
			return null;
		}
		List<SysRole> sysRoleList = new ArrayList<>();
		sysRoleUserList.forEach(obj -> {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(obj.getRoleId());
			if(sysRole != null){
				sysRoleList.add(sysRole);
			}
		});
		
		return sysRoleList;
	}
	
	private List<SysFunction> getSysFunctionSelective(SysRole sysRole){
		SysRoleFunctionExample sysRoleFunctionExample = new SysRoleFunctionExample();
		sysRoleFunctionExample.createCriteria().andRoleIdEqualTo(sysRole.getId()).andSysFlagEqualTo(YesOrNo.yes.getValue()).andValidFlagEqualTo(YesOrNo.yes.getValue());
		List<SysRoleFunction> sysRoleFunctionList = getSysRoleFunctionSelective(sysRoleFunctionExample);
		if(CollectionUtils.isEmpty(sysRoleFunctionList)){
			return null;
		}
		List<SysFunction> result = new ArrayList<>();
		sysRoleFunctionList.forEach(obj->{
			SysFunction sysFunction = sysFunctionMapper.selectByPrimaryKey(obj.getFunctionId());
			if(sysFunction != null){
				result.add(sysFunction);
			}
		});
		
		return result;
	}
	
	private List<SysRoleUser> getSysRoleUserSelective(SysRoleUserExample sysRoleUserExample){
		return sysRoleUserMapper.selectByExample(sysRoleUserExample);
	}
	
	private List<SysRoleFunction> getSysRoleFunctionSelective(SysRoleFunctionExample sysRoleFunctionExample){
		return sysRoleFuncionMapper.selectByExample(sysRoleFunctionExample);
	}
	
	private List<SysFunction> getSysFunctionSelective(SysFunctionExample sysFunctionExample){
		return sysFunctionMapper.selectByExample(sysFunctionExample);
	}
	
	private List<SysFunctionUserPermission> getSysFunctionUserPermissionSelective(SysFunctionUserPermissionExample sysFunctionUserPermissionExample){
		return sysFunctionUserPermissionMapper.selectByExample(sysFunctionUserPermissionExample);
	}
	
	
	/**
	 * 根据当前功能，查找所有的子孙关系的功能集
	 * @param sysFunction
	 * @param sysFunctionWithChildrenList
	 */
	private void getSysFunctionChildrenSelective(SysFunction sysFunction,List<SysFunctionWithChildren> sysFunctionWithChildrenList){
		SysFunctionExample sysFunctionExample = new SysFunctionExample();
		sysFunctionExample.createCriteria().andParentIdEqualTo(sysFunction.getId()).andSysFlagEqualTo(YesOrNo.yes.getValue());
		List<SysFunction> sysFunctions = getSysFunctionSelective(sysFunctionExample);
		SysFunctionWithChildren sysFunctionWithChildren = new SysFunctionWithChildren();
		if(CollectionUtils.isEmpty(sysFunctions)){
			sysFunctionWithChildren.setSysFunction(sysFunction);
			sysFunctionWithChildrenList.add(sysFunctionWithChildren);
		}else{
			sysFunctionWithChildren.setSysFunction(sysFunction);
			sysFunctionWithChildren.setSysFunctionChildren(sysFunctions);
			sysFunctionWithChildrenList.add(sysFunctionWithChildren);
			sysFunctions.forEach(sysFunctionObj -> {
				getSysFunctionChildrenSelective(sysFunctionObj,sysFunctionWithChildrenList);
			});
		}
	}
	
	@Data
	public static class SysFunctionWithChildren{
		private SysFunction sysFunction;
		private List<SysFunction> sysFunctionChildren;
	}
}
