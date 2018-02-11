package com.leoyon.vote.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


@ControllerAdvice
@ResponseBody
@EnableWebMvc
public class ControllerExceptionHandler {
	
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(ResponseException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleException(ResponseException e) {
		LOG.error(e.getMessage(), e);
        return JsonResponse.RespFail(e.getCode());
    }

	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleException(Exception e) {
		LOG.error("未知异常,", e);
        return JsonResponse.RespFail(Error.UNKNOWN_EXCEPT);
    }

}
