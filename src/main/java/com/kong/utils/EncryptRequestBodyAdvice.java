//package com.kong.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.util.IOUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.MethodParameter;
////import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Type;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//@RestControllerAdvice
//public class EncryptRequestBodyAdvice implements RequestBodyAdvice {
//
//	private final ObjectMapper objectMapper = new ObjectMapper();
//	protected Log logger = LogFactory.getLog(this.getClass());
//
//	@Autowired
//	private Environment environment;
////	@Value("${app.deskey}")
//	private String desKey;
//
//	@Override
//	public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return methodParameter.hasMethodAnnotation(Encrypt.class) && methodParameter.hasParameterAnnotation(RequestBody.class);
//	}
//
//	@Override
//	public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return o;
//	}
//
//	@Override
//	public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage,
//										   MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return new HttpInputMessage() {
//			@Override
//			public InputStream getBody() throws IOException {
//				logger.info("此处进行解密数据");
//				String requestStr = IOUtils.(httpInputMessage.getBody(), Charset.forName("utf-8"));
//				Map paramMap = objectMapper.readValue(requestStr, Map.class);
//				logger.info("paramMap=" + paramMap);
//				if (StringUtils.isEmpty(desKey)) {
//					String requestData = objectMapper.writeValueAsString(paramMap.get("data"));
//					logger.info("requestData=" + requestData);
//					if (paramMap.containsKey("page")) {
//						Map requestMap = JSONObject.parseObject(requestData,Map.class);
//						requestMap.put("page", paramMap.get("page"));
//						requestData = objectMapper.writeValueAsString(requestMap);
//						return new ByteArrayInputStream(requestData.getBytes(StandardCharsets.UTF_8));
//					}
//					return new ByteArrayInputStream(requestData.getBytes(StandardCharsets.UTF_8));
//				}
//				DESUtils desUtils = new DESUtils(desKey);
//				String requestData = (String) paramMap.getOrDefault("data", "");
//				requestData = desUtils.decryptString(requestData);
//				//如果有分页信息,则需要拿出data进行解密,page不用解密
//				if (paramMap.containsKey("page")) {
//					Map requestMap = JSONObject.parseObject(requestData,Map.class);
//					requestMap.put("page", paramMap.get("page"));
//					requestData = objectMapper.writeValueAsString(requestMap);
//					return new ByteArrayInputStream(requestData.getBytes(StandardCharsets.UTF_8));
//				}
//				return new ByteArrayInputStream(requestData.getBytes(StandardCharsets.UTF_8));
//
//			}
//
//			@Override
//			public HttpHeaders getHeaders() {
//				return httpInputMessage.getHeaders();
//			}
//		};
//	}
//
//	@Override
//	public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return o;
//	}
//}