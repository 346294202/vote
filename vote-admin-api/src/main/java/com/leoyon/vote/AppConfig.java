package com.leoyon.vote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, 
                         prefix = "app")
public class AppConfig {
	
	private String uploadFileFolder = "/tmp/vote/upload/";
	
	private Integer verifyCodeExpirSeconds = 300;
	
	private Integer tokenExpirSeconds = 10;//token的过期秒数

	public Integer getTokenExpirSeconds() {
		return tokenExpirSeconds;
	}

	public void setTokenExpirSeconds(Integer tokenExpirSeconds) {
		this.tokenExpirSeconds = tokenExpirSeconds;
	}
	
	private Integer pageSize = 20;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getVerifyCodeExpirSeconds() {
		return verifyCodeExpirSeconds;
	}

	public void setVerifyCodeExpirSeconds(Integer verifyCodeExpirSeconds) {
		this.verifyCodeExpirSeconds = verifyCodeExpirSeconds;
	}

	public String getUploadFileFolder() {
		return uploadFileFolder;
	}

	public void setUploadFileFolder(String uploadFileFolder) {
		this.uploadFileFolder = uploadFileFolder;
	}

}
