package com.leoyon.vote.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.command.dao.SysCommandDao;
import com.leoyon.vote.user.SysUser;

@Service
public class SysCommandServiceImp implements SysCommandService {
	
	@Autowired
	private SysCommandDao commandDao; 

	@Override
	public List<SysCommand> listByUser(SysUser user) {		
		return commandDao.listByUser(user.getId());
	}

}
