/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */
package org.springblade.common.validator.group;

import javax.validation.GroupSequence;

/**
 * import javax.validation.GroupSequence;
 * <p>
 * /**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author Mark Wenjunchi
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
