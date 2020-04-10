package com.kong.utils;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kong.data.PageResult;
import com.kong.exception.TradeException;

@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private Environment environment;
//	@Value("${app.deskey}")
	private String desKey;

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return returnType.hasMethodAnnotation(Encrypt.class);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
								  ServerHttpResponse response) {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "1");
			resultMap.put("reason", "成功");
			if (StringUtils.isEmpty(body)) {
				resultMap.put("data", "{}");
				return resultMap;
			}
			if (StringUtils.isEmpty(desKey)) {
				if (body instanceof PageResult) {
					PageResult pageResult = (PageResult) body;
					resultMap.put("page", pageResult.getPage());
					resultMap.put("data", pageResult.getResultList());
					return resultMap;
				}
				if (body instanceof List) {
					List<Map> list = (List<Map>) body;
					if (list == null || list.size() == 0) {
						resultMap.put("data", "[]");
						return resultMap;
					}
					resultMap.put("data", list);
					return resultMap;
				}
				resultMap.put("data", body);
				return resultMap;
			}
			DESUtils desUtils = new DESUtils(desKey);
			if (body instanceof Map) {
				Map param = (Map) body;

				//对message进行加密
				if (param.size() != 0) {
					String requestString = objectMapper.writeValueAsString(body);
					String desAfter = desUtils.encryptString(requestString);
					resultMap.put("data", desAfter);
				} else {
					resultMap.put("data", "{}");
				}
				return resultMap;
			}
			if (body instanceof List) {
				List<Map> list = (List<Map>) body;
				if (list == null || list.size() == 0) {
					resultMap.put("data", "[]");
					return resultMap;
				}
				//对list进行加密处理
				String listString = objectMapper.writeValueAsString(list);
				String desAfter = desUtils.encryptString(listString);
				resultMap.put("data", desAfter);
				return resultMap;
			}
			if (body instanceof PageResult) {
				PageResult pageResult = (PageResult) body;
				resultMap.put("page", pageResult.getPage());
				String listString = objectMapper.writeValueAsString(pageResult.getResultList());
				String desAfter = desUtils.encryptString(listString);
				resultMap.put("data", desAfter);
				return resultMap;
			}
			String listString = objectMapper.writeValueAsString(body);
			String desAfter = desUtils.encryptString(listString);
			resultMap.put("data", desAfter);
			return resultMap;


		} catch (Exception e) {
			e.printStackTrace();
			throw new TradeException("9999", "服务器内部错误");
		}
	}
}