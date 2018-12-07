package com.ideacome.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ideacome.common.db.domain.SysFunction;
import com.ideacome.security.vo.UserDetailVO;
import com.ideacome.security.vo.UserDetailVO.SysFunctionWithFuncionPermission;
import com.ideacome.security.vo.UserDetailVO.SysRoleWithFunction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailSecurityService implements UserDetailsService {
	@Autowired
	private UserDetailInfoService userDetailInfoService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserDetailSecurityService.loadUserByUsername:username-{}",username);
		UserDetailVO userDetailVO = userDetailInfoService.getUserDetailVO(username);
		if(userDetailVO != null){
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			List<SysRoleWithFunction> sysRoleWithFunctionList = userDetailVO.getSysRoleWithFunctionList();
			for(SysRoleWithFunction sysRoleWithFunction : sysRoleWithFunctionList){
				List<SysFunctionWithFuncionPermission> sysFunctionWithFuncionPermissionList = sysRoleWithFunction.getSysFunctionWithFuncionPermissionList();
				for(SysFunctionWithFuncionPermission sysFunctionWithFuncionPermission : sysFunctionWithFuncionPermissionList ){
					SysFunction sysFunction = sysFunctionWithFuncionPermission.getSysFunction();
					SimpleGrantedAuthority auth = new SimpleGrantedAuthority(sysFunction.getFunCode());
					authorities.add(auth);
				}
			}
			
			return new User(username, userDetailVO.getSysUser().getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException(username);
	}

}
