package com.leoyon.vote.file;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;

@RestController("文件")
@Scope("prototype")
public class UploadController extends AuthenticationController {

	@PostMapping(value="/upload-file", name="上传文件")
	public JsonResponse uploadFile(@RequestParam("file") MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(fileName);
		String ext = Files.getFileExtension(fileName);
		
		return JsonResponse.sucess();
	}
}
