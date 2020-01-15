/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package org.springblade.common.tool;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.common.constant.Constant;
import org.springblade.common.xss.SQLFilter;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark Wenjunchi
 */
public class Query<T> {

	public IPage<T> getPage(Map<String, Object> params) {
		return this.getPage(params, null, false);
	}

	public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
		//分页参数
		long curPage = 1;
		long limit = 10;

		if (params.get(Constant.PAGE) != null) {
			curPage = Long.parseLong(String.valueOf(params.get(Constant.PAGE)));
			//     curPage=(Long)params.get(Constant.PAGE);
		}
		if (params.get(Constant.LIMIT) != null) {
			limit = Long.parseLong(String.valueOf(params.get(Constant.LIMIT)));
			//  limit=(Long)params.get(Constant.LIMIT);
		}

		//分页对象
		Page<T> page = new Page<>(curPage, limit);

		//分页参数
		params.put(Constant.PAGE, page);

		//排序字段
		//防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
		String orderField = SQLFilter.sqlInject((String) params.get(Constant.ORDER_FIELD));
		String order = (String) params.get(Constant.ORDER);

		//前端字段排序
		if (!StringUtils.isEmpty(orderField) && !StringUtils.isEmpty(order)) {
			if (Constant.ASC.equalsIgnoreCase(order)) {
				return page.setAsc(orderField);
			} else {
				return page.setDesc(orderField);
			}
		}

		//默认排序
		if (isAsc) {
			page.setAsc(defaultOrderField);
		} else {
			page.setDesc(defaultOrderField);
		}

		return page;
	}
}
