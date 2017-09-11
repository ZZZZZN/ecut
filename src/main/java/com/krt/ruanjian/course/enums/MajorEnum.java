package com.krt.ruanjian.course.enums;

/**
 * 专业枚举类
 * @author pengYi
 * @date 2017-9-9
 */
public class MajorEnum {
    private static final String CODE_TONGXIN = "1001";
    private static final String CODE_COMPUTER_SCIENCE = "1002";
    private static final String CODE_SOFT_PROJECT = "1003";
    private static final String CODE_IOT = "1004";
    private static final String CODE_NETWORK_PROJECT = "1005";
    private static final String CODE_DIGITAL_META = "1006";

    private static final MajorEnum TONGXIN = new MajorEnum(CODE_TONGXIN, "通信工程");
    private static final MajorEnum COMPUTER_SCIENCE = new MajorEnum(CODE_TONGXIN, "计算机科学与技术");
    private static final MajorEnum SOFT_PROJECT = new MajorEnum(CODE_TONGXIN, "软件工程专业");
    private static final MajorEnum IOT = new MajorEnum(CODE_TONGXIN, "物联网工程");
    private static final MajorEnum NETWORK_PROJECT = new MajorEnum(CODE_TONGXIN, "网络工程专业");
    private static final MajorEnum DIGITAL_META = new MajorEnum(CODE_TONGXIN, "数字媒体专业");

    private String code;
    private String name;

    public static MajorEnum getMajorNameByCode(String code) {
        if (code == null || code.equals("")) {
            return null;
        }
        switch (code) {
            case CODE_TONGXIN : return TONGXIN;
            case CODE_COMPUTER_SCIENCE : return COMPUTER_SCIENCE;
            case CODE_SOFT_PROJECT : return SOFT_PROJECT;
            case CODE_IOT : return IOT;
            case CODE_NETWORK_PROJECT : return NETWORK_PROJECT;
            case CODE_DIGITAL_META : return DIGITAL_META;
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
