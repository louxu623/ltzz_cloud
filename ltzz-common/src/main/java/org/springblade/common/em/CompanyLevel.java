package org.springblade.common.em;

/**
 * 公司级别
 *
 * @author luox
 * @date 2019/8/26
 */
public enum CompanyLevel {
    /**
     * 普通
     */
    ORDINARY(1, "普通"),
    /**
     * 合伙人
     */
    PARTNER(0, "合伙人");
    private int code;
    private String name;

    private CompanyLevel(int code, String name) {
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
