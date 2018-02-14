package com.leoyon.vote.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leoyon.vote.role.SysRole;

public interface SysUserRoleDao {

	List<SysRole> getUserRoles(Long uid);
	
	void clearUserRoles(Long uid);

	void addUserRole(@Param("uid") Long uid, @Param("rid") Long rid);
}
