package com.leoyon.vote.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.leoyon.vote.AppConfig;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.file.dao.UploadFileDao;

@RestController("文件")
@Scope("prototype")
public class FileController extends AuthenticationController {
	
	@Autowired
	private AppConfig app;
	
	@Autowired
	private UploadFileDao dao;
	
	@GetMapping(value="/file/{id}", name="下载文件,no token")
	public void downloadFile(@PathVariable long id, HttpServletResponse resp) throws ResponseException, FileNotFoundException, IOException {
		UploadFile file = dao.get(id);
		if(file == null)
			throw new ResponseException("错误的id");
		resp.setContentType(file.getMimetype());
		IOUtils.copy(new FileInputStream(new File(file.getFolder(), file.getFile())), resp.getOutputStream());
	}

	@PostMapping(value="/upload-file", name="上传文件")
	public JsonResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = file.getOriginalFilename();
		
		String mimetype = file.getContentType();
		String ext = Files.getFileExtension(fileName);
		
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		String folder = app.getUploadFileFolder();
		Files.write(file.getBytes(), new File(folder, newFileName));
		
		UploadFile entity = new UploadFile();
		entity.setFile(newFileName);
		entity.setFolder(folder);
		entity.setMimetype(mimetype);
		entity.setSize(file.getSize());
		
		dao.add(entity);
		
		return JsonResponse.sucess(entity);
	}
}
