package com.leoyon.vote.file.dao;

import com.leoyon.vote.file.UploadFile;

public interface UploadFileDao {

	void add(UploadFile entity);

	UploadFile get(long id);

}
