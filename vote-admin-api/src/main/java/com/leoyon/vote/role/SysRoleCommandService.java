package com.leoyon.vote.role;

import java.util.List;

import com.leoyon.vote.command.SysCommand;

public interface SysRoleCommandService {

	List<SysCommand> getRoleCommands(Long roleId);

	void setRoleCommands(Long roleId, List<Long> commandIds) throws Exception;

}
