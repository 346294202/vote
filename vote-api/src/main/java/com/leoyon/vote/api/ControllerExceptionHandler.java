package com.leoyon.vote.api;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleException(MissingServletRequestParameterException e) {
		LOG.error(e.getMessage(), e);
        return JsonResponse.fail(Error.MISSING_PARAM.getValue(), Error.MISSING_PARAM.getLabel()+"'"+e.getParameterName()+"'");
    }
	
	@ExceptionHandler(ResponseException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleException(ResponseException e) {
		LOG.error(e.getMessage(), e);
        return JsonResponse.fail(e.getCode(), e.getError());
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		LOG.error("参数验证失败", e);
		StringBuilder sb = new StringBuilder();
		e.getBindingResult().getAllErrors().forEach( i -> {
			sb.append(i.getDefaultMessage());
			sb.append(';');
		});
        return JsonResponse.fail(Error.VALID_EXCEPT.getValue(), sb.toString());
    }
	
	@ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse handleException(Throwable e) {
		LOG.error("未知异常", e);
        return JsonResponse.fail(Error.UNKNOWN_EXCEPT);
    }

}
