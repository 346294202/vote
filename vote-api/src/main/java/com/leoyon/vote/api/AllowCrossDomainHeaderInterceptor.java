package com.leoyon.vote.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 处理跨域访问
 * @author WangJiang
 *
 */
public class AllowCrossDomainHeaderInterceptor extends HandlerInterceptorAdapter {

    /**
     * 检查请求
     * 是否存在 token、version、channel 字段
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

    	//BEGIN下面代码处理AJAX跨域问题
    	//当Access-Control-Allow-Credentials为true时，Access-Control-Allow-Origin不能是*，且不能是IP
    	httpServletResponse.addHeader("Access-Control-Allow-Origin","*");//httpServletRequest.getScheme()+"://"+httpServletRequest.getRemoteHost());
    	httpServletResponse.setHeader("Access-Control-Allow-Origin","*");//httpServletRequest.getScheme()+"://"+httpServletRequest.getRemoteHost());
//    	httpServletResponse.addHeader("Access-Control-Allow-Credentials","true");
//    	httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
    	if("OPTIONS".equals(httpServletRequest.getMethod().toUpperCase())){
    		httpServletResponse.setHeader("Access-Control-Allow-Methods","GET, PUT, POST, DELETE, OPTIONS");
    		httpServletResponse.setHeader("Access-Control-Max-Age","1000");
    		httpServletResponse.setHeader("Access-Control-Allow-Headers",
    				httpServletRequest.getHeader("Access-Control-Request-Headers"));
    		return true;
    	}
    	//END
    	
        return true;
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Origin","*");
	}


}
