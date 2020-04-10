package com.kong.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kong.exception.TradeException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	protected Log logger = LogFactory.getLog(this.getClass());
    @ExceptionHandler(TradeException.class)
	@ResponseBody
    public Object handleTradeExceptionException(TradeException e, HttpServletResponse response) {
        // 从异常对象中拿到ObjectError对象
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
//        return objectError.getDefaultMessage();
		logger.error(e);
		Map<String, Object> map = new HashMap<>();
		map.put("code", "-1");
		map.put("reason", e.getResultMsg());
		map.put("data","{}");
		response.setStatus(200);
		return map;
    }
	@ExceptionHandler(value = {Exception.class})
	@ResponseBody
	public Object handleException(Exception e, HttpServletResponse response){
		logger.error(e);
		if (e.getCause() != null) {
			logger.error(e.getCause());
		}
		e.printStackTrace();
		Map<String, Object> map = new HashMap<>();
		map.put("code", "-1");
		map.put("reason", "服务器内部错误");
		map.put("data","{}");
		response.setStatus(500);
		return map;
	}
}