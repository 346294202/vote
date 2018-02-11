package com.leoyon.vote.command;

import java.util.List;

import com.leoyon.vote.user.SysUser;

public interface SysCommandService {

	List<SysCommand> listByUser(SysUser user);

}
