package com.leoyon.vote.command;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.command.dao.SysCommandDao;
import com.leoyon.vote.user.SysUser;

@Service
public class SysCommandServiceImp implements SysCommandService {
	
	@Autowired
	private SysCommandDao commandDao; 

	@Override
	public void update(SysCommand command) {
		commandDao.update(command);
	}

	@Override
	public void add(SysCommand command) {
		commandDao.add(command);
	}

	@Override
	public List<SysCommand> find(FindSysCommandRequest rqst) {
		return commandDao.find(rqst);
	}

	@Override
	public List<SysCommand> all() {
		return commandDao.all();
	}

	@Override
	public boolean existed(Long id) {
		
		Long ret = commandDao.existed(id);
		
		return ret != null && ret.equals(id);
	}

	@Override
	public void delete(SysCommand entity) {
		commandDao.delete(entity);
	}

	@Override
	public int count(FindSysCommandRequest rqst) {
		return commandDao.count(rqst);
	}


}
