package cn.lw.mybatis.autoenum.common;

import cn.lw.mybatis.autoenum.core.DbEnum;

public enum CommonSexEnum implements DbEnum {

    /**
     * 男
     */
    MALE("male", "男"),
    /**
     * 女
     */
    FEMALE("female", "女");

    private final String code;
    private final String name;

    CommonSexEnum(String code, String name) {
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
