package com.wuxin.util.validata;

public class ValidateUtil {
    /**
     * 验证输入的数据是否为空
     * @param data 输入数据
     * @return 如果不是null返回true，否则返回false
     */
    public static boolean validateEmpty(String data) {
        if (data == null || "".equals(data)) {
            return false ;
        }
        return true ;
    }

    /**
     * 进行数据的正则操作验证
     * @param data 要验证的数据
     * @param regex 要执行验证的正则表达式
     * @return 如果验证通过返回true，否则返回false
     */
    public static boolean validateRegex(String data,String regex) {
        if (validateEmpty(data)) { // 数据不为空
            return data.matches(regex) ;    // 验证验证
        }
        return false ;
    }

    /**
     * 验证两个数据是否相同，不区分大小写
     * @param dataa 数据一
     * @param datab 数据二
     * @return 如果相同返回true，否则返回false
     */
    public static boolean validateSame(String dataa,String datab) {
        if (validateEmpty(dataa) && validateEmpty(datab)) {
            return dataa.equalsIgnoreCase(datab) ;
        }
        return false ;
    }
}
