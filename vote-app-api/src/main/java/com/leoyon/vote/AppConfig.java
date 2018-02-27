package com.leoyon.vote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, 
                         prefix = "app")
public class AppConfig {
	
	private Integer verifyCodeExpirSeconds = 300;

	private Integer tokenExpirSeconds = 10;//token的过期秒数

	public Integer getTokenExpirSeconds() {
		return tokenExpirSeconds;
	}

	public void setTokenExpirSeconds(Integer tokenExpirSeconds) {
		this.tokenExpirSeconds = tokenExpirSeconds;
	}

	public Integer getVerifyCodeExpirSeconds() {
		return verifyCodeExpirSeconds;
	}

	public void setVerifyCodeExpirSeconds(Integer verifyCodeExpirSeconds) {
		this.verifyCodeExpirSeconds = verifyCodeExpirSeconds;
	}
	
}
