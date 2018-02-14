package com.leoyon.vote.user;

import java.util.List;

import com.leoyon.vote.role.SysRole;

public interface SysUserRoleService {

	List<SysRole> getUserRoles(Long id);

	void setUserRoles(Long id, List<Long> parseList) throws Exception;

}
