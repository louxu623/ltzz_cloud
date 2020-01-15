package org.springblade.common.em;

/**
 * 市场类型
 *
 * @author luox
 * @date 2019/10/28
 */
public enum MarketType {
    /**
     * 沪市
     */
    SH(1, "沪市"),
    /**
     * 深市
     */
    SZ(2, "深市");
    private int code;
    private String name;

    private MarketType(int code, String name) {
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
