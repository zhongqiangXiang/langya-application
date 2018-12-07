package com.ideacome.security.vo;

import java.util.List;

import com.ideacome.common.db.domain.SysFunction;
import com.ideacome.common.db.domain.SysFunctionUserPermission;
import com.ideacome.common.db.domain.SysRole;
import com.ideacome.common.db.domain.SysUser;

import lombok.Data;

@Data
public class UserDetailVO {
	private SysUser sysUser;
	private List<SysRoleWithFunction> sysRoleWithFunctionList;
	
	@Data
	public static class SysFunctionWithFuncionPermission{
		private SysFunction sysFunction;
		private SysFunctionUserPermission sysFunctionUserPermission;
	}
	@Data
	public static class SysRoleWithFunction{
		private SysRole sysRole;
		private List<SysFunctionWithFuncionPermission> sysFunctionWithFuncionPermissionList;
	}
	
}
