package com.krt.common.util;

import com.krt.common.config.Constant;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * 项目名称：krtCenter
 * 类名称：AESvbjavajs
 * 类描述： AES加密算法
 * 创建人：殷帅
 * 创建时间：2015-9-7 下午05:50:16
 */
public class AESvbjavajs {

    /**
     * @param args
     */
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密
     *
     * @param content
     * @param aes
     * @return
     */
    public static byte[] getAESEncrypt(byte[] content, byte[] aes) {
        if (content == null || content.length < 0 || aes == null || aes.length < 0) {
            System.out.println("密钥或内容为空...");
            return null;
        }
        try {
            //补齐16位
            byte[] content_temp;
            int w = content.length % 16;
            if (w != 0) {
                content_temp = new byte[content.length + 16 - w];
                for (int i = 0; i < content.length; i++) {
                    content_temp[i] = content[i];
                }
                ;
            } else
                content_temp = content;

            SecretKeySpec sks = new SecretKeySpec(aes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] jiamihou = cipher.doFinal(content_temp);
            return jiamihou;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     * @param aes
     * @return
     */
    public static byte[] getAESDecrypt(byte[] content, byte[] aes) {
        if (content == null || content.length == 0 || aes == null || aes.length < 0) {
            System.out.println("密钥或内容为空...");
            return null;
        }
        try {
            SecretKeySpec sks = new SecretKeySpec(aes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");    //AES/ECB/NoPadding
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] jiemihou = cipher.doFinal(content);
            //去除多余空字节(0)
            return jiemihou;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param content
     * @param aes
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAESEncrypt(String content, String aes) throws UnsupportedEncodingException {
        return parseByte2HexStr(getAESEncrypt(content.getBytes("UTF-8"), aes.getBytes()));

    }

    /**
     * 加密
     *
     * @param content
     * @param aes
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAESAndBase64Encrypt(String content, String aes) throws UnsupportedEncodingException {
        content = JavaBase64.encode(content.getBytes("UTF-8"), 0, content.getBytes("UTF-8").length);
        return parseByte2HexStr(getAESEncrypt(content.getBytes("UTF-8"), aes.getBytes()));

    }

    /**
     * 解密
     *
     * @param content
     * @param aes
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static String getAESAndBase64Decrypt(String content, String aes) throws UnsupportedEncodingException, IOException {
        content = new String(getAESDecrypt(parseHexStr2Byte(content), aes.getBytes()), "UTF-8").replaceAll("\0", "");
        return new String(JavaBase64.decode(content.toString()), "UTF-8");
    }

    /**
     * 解密
     *
     * @param content
     * @param aes
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static String getAESDecrypt(String content, String aes) throws UnsupportedEncodingException, IOException {
        return new String(getAESDecrypt(parseHexStr2Byte(content), aes.getBytes()), "UTF-8").replaceAll("\0", "");
    }

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        //System.out.println(AESvbjavajs.getAESAndBase64Decrypt("532C31FB93E9E177D81FEA35A66194F7EA57D98C048F3FF74911E8DE52F2DC0DEB9397679F26A0477195343534EAAE47", "yjmrdkzc1davaafv"));
        //253B27279251BD3D911ABEC192D012CF
        // FB9508D65C2C6B5395F04F0504A8B33C
        System.out.println(AESvbjavajs.getAESEncrypt("123456", Constant.PASS_KEY));
        System.out.println(AESvbjavajs.getAESDecrypt("D75F3FA23EA540271AF8986C85252807", Constant.PASS_KEY));
    }
}