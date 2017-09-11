package com.krt.ruanjian.course.enums;

/**
 * 专业枚举类
 * @author pengYi
 * @date 2017-9-9
 */
public class MajorEnum {
    private static final String CODE_SOFT_PROJECT = "080902";
    private static final String CODE_NETWORK_PROJECT = "080903";
    private static final String CODE_INFO_SECURITY = "080904";
    private static final String CODE_IOT = "080905";
    private static final String CODE_DIGITAL_META_PROJECT = "080906";

    private static final MajorEnum SOFT_PROJECT = new MajorEnum(CODE_SOFT_PROJECT, "软件工程");
    private static final MajorEnum NETWORK_PROJECT = new MajorEnum(CODE_NETWORK_PROJECT, "网络工程");
    private static final MajorEnum INFO_SECURITY = new MajorEnum(CODE_INFO_SECURITY, "信息安全");
    private static final MajorEnum IOT = new MajorEnum(CODE_IOT, "物联网工程");
    private static final MajorEnum DIGITAL_META_PROJECT = new MajorEnum(CODE_DIGITAL_META_PROJECT, "数字媒体技术");

    private String code;
    private String name;

    public static MajorEnum getMajorNameByCode(String code) {
        if (code == null || code.equals("")) {
            return null;
        }
        switch (code) {
            case CODE_SOFT_PROJECT : return SOFT_PROJECT;
            case CODE_NETWORK_PROJECT : return NETWORK_PROJECT;
            case CODE_INFO_SECURITY : return INFO_SECURITY;
            case CODE_IOT : return IOT;
            case CODE_DIGITAL_META_PROJECT : return DIGITAL_META_PROJECT;
            default:throw new IllegalArgumentException("请核对传入参数");
        }
    }

    public MajorEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
