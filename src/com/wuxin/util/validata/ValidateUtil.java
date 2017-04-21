package com.wuxin.util.validata;

public class ValidateUtil {
    /**
     * ��֤����������Ƿ�Ϊ��
     * @param data ��������
     * @return �������null����true�����򷵻�false
     */
    public static boolean validateEmpty(String data) {
        if (data == null || "".equals(data)) {
            return false ;
        }
        return true ;
    }

    /**
     * �������ݵ����������֤
     * @param data Ҫ��֤������
     * @param regex Ҫִ����֤��������ʽ
     * @return �����֤ͨ������true�����򷵻�false
     */
    public static boolean validateRegex(String data,String regex) {
        if (validateEmpty(data)) { // ���ݲ�Ϊ��
            return data.matches(regex) ;    // ��֤��֤
        }
        return false ;
    }

    /**
     * ��֤���������Ƿ���ͬ�������ִ�Сд
     * @param dataa ����һ
     * @param datab ���ݶ�
     * @return �����ͬ����true�����򷵻�false
     */
    public static boolean validateSame(String dataa,String datab) {
        if (validateEmpty(dataa) && validateEmpty(datab)) {
            return dataa.equalsIgnoreCase(datab) ;
        }
        return false ;
    }
}
