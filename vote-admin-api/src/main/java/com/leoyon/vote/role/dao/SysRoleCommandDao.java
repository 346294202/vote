package com.leoyon.vote.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leoyon.vote.command.SysCommand;

public interface SysRoleCommandDao {

	List<SysCommand> getRoleCommands(Long rid);

	void clearRoleCommands(Long rid);

	void addRoleCommand(@Param("rid") Long rid, @Param("cid") Long cid);

}
