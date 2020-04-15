package com.fumiao.core.uitls;

import android.util.Base64;
import com.fumiao.core.okgo.Convert;
import java.net.URLDecoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    // 加密秘钥
    private static final String AES_KEY = "CC457A96E1AA0EFC";
    private static String ivParameter = "B04F4B4CC50FF644";


    // 加密
    public static String encrypt(String sSrc){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            String secretKey = MD5Utils.getMD5(AES_KEY).substring(8, 24);
//            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            byte[] param  = Base64.encode(encrypted, Base64.NO_WRAP);// 此处使用BASE64做转码。
            return new String(param, "UTF-8");
        }catch(Exception ex){
            return null;
        }
    }


    // 解密
    public static String decrypt(String sSrc, String key) {
        try {
            String secretKey = MD5Utils.getMD5(key).substring(8, 24);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            String originalString = URLDecoder.decode(sSrc, "UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] paramBytes = cipher.doFinal(Base64.decode(originalString, Base64.NO_WRAP));
            String paramJson = new String(paramBytes, "UTF-8");
            return paramJson;
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> T decryptToObject(String sSrc, String key, Class<T> clazz) {
        try {
            //对进行AES解密获取KEY为16位，通过传进来key进去MD5加密截取
            String secretKey = MD5Utils.getMD5(key).substring(8, 24);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            String originalString = URLDecoder.decode(sSrc, "UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] paramBytes = cipher.doFinal(Base64.decode(originalString, Base64.NO_WRAP));
            String paramJson = new String(paramBytes, "UTF-8");
            return Convert.fromJson(paramJson, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static byte[] decryptTobyte(String sSrc, String key) {
        try {
            //对进行AES解密获取KEY为16位，通过传进来key进去MD5加密截取
            String secretKey = MD5Utils.getMD5(key).substring(8, 24);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] paramBytes = cipher.doFinal(Base64.decode(sSrc, Base64.NO_WRAP));
            String paramJson = new String(paramBytes, "UTF-8");
            //对AES解密后的字符再Base64解密得到二进制流
            return Base64.decode(paramJson, Base64.NO_WRAP);
        } catch (Exception ex) {
            return null;
        }
    }

}
