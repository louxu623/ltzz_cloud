package org.springblade.common.em;

/**
 * @author luox
 * @description 订单平台
 * @date 2019/8/14
 */
public enum Platform {
    /**
     * 淘宝/天猫
     */
    TAOBAO(1, "淘宝/天猫"),
    /**
     * 拼多多
     */
    PDD(2, "拼多多"),
    /**
     * 京东
     */
    JINGDONG(3, "京东"),
    /**
     * 多麦
     */
    DM(4, "多麦");
    private int code;
    private String name;
    private Platform(int code, String name){
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return this.code + "_" + this.name;
    }
}
