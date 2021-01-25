package cn.lw.mybatis.autoenum.common;

import cn.lw.mybatis.autoenum.core.DbEnum;

public enum CommonYesNoEnum implements DbEnum {

    /**
     * 是
     */
    YES("1", "是"),
    /**
     * 否
     */
    NO("0", "否");

    private final String code;
    private final String name;

    CommonYesNoEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
