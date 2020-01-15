package org.springblade.common.tool;

/**
 * @author lq
 * @date 2019\6\28 0028 15:09
 */
public class ExceptionCode {
    //客户账户不存在
    public static Integer CUSTOMER_ACCOUNT_NOT_EXIST = 40001;
    //插入额度池变更流水失败
    public static Integer INSERT_QUOTA_DETAILED_FAILED = 40002;
    //额度池余额不足
    public static Integer AVAILABLE_CREDIT_INSUFFICIENT = 40003;
    //修改额度池金额失败
    public static Integer UPDATE_AVAILABLE_CREDIT_FAILED = 40004;
    //未开启公司一级发行权限
    public static Integer UNOPENED_PERMISSIONS = 40005;
    //找不到CAT_LIST
    public static Integer NOT_FOUND_CAT_LIST = 40006;
    //充值卡未生效
    public static Integer RECHARGE_CARD_INVALID = 40007;
    //  砸金蛋活动剩余参与次数不足，但是还可以通过兑换获得机会
    public static Integer EGG_COUNT_UNLACK = 50000;
    //  砸金蛋活动剩余参与次数不足
    public static Integer EGG_COUNT_LACK = 50001;
    //  获取中奖记录出错
    public static Integer EGG_RECORD_FAILED = 50002;
    // 获取活动开始时间失败
    public static Integer GET_START_TIME_FAILED = 40008;
    // 获取抽奖卡券信息失败
    public static Integer GET_COUPON_FAILED = 40009;
}
