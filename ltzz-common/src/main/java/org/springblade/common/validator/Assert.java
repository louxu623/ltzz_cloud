package org.springblade.common.validator; /**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

import org.springblade.common.exception.RRException;
import org.springframework.util.StringUtils;

/**
 * 数据校验
 *
 * @author Mark Wenjunchi
 */
public abstract class Assert {

	public static void isBlank(String str, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new RRException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new RRException(message);
		}
	}
}
