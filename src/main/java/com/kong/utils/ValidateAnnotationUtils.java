package com.kong.utils;


import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.kong.enums.ReturnCode;
import com.kong.exception.TradeException;

/**
 * @Description: 注解验证
 * @Author:
 * @Date:
 */
public class ValidateAnnotationUtils {

	/**
	 * 校验注解是否通过
	 *
	 * @param t
	 * @param <T>
	 */
	public static <T> void validate(T t) {
		if (null == t) {
			TradeException ex = new TradeException(ReturnCode.request_param_is_null.getCode(),
					"请求参数不能为空");
			throw ex;
		}

		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = vFactory.getValidator();
		Set set = validator.validate(t);
		if (set.size() > 0) {
			for (Iterator<ConstraintViolation> itr = set.iterator(); itr.hasNext(); ) {
				ConstraintViolation constraintVio = itr.next();
				Annotation annotation = constraintVio.getConstraintDescriptor().getAnnotation();
				String errorMsg = constraintVio.getMessage();
				if ((annotation instanceof NotNull)
						|| (annotation instanceof NotEmpty)
						|| (annotation instanceof NotBlank)) {
					TradeException ex = new TradeException(ReturnCode.request_param_is_null.getCode(), errorMsg);
					throw ex;
				} else {
					TradeException ex = new TradeException(ReturnCode.request_param_not_valid.getCode(), errorMsg);
					throw ex;
				}
			}
		}
	}

}
