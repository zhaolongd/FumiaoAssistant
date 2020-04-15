package com.fumiao.core.uitls;

import android.util.Base64;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static android.util.Base64.NO_WRAP;

/**
 * Created by chee on 2018/9/6.
 */
public class RsaUtils {

    /**
     * RSA算法
     */
    public static final String RSA = "RSA";
    /**
     * 加密方式
     */
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    /**
     * 福喵公钥
     */
    public static final String FUMIAO_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC81Q0VoNSvIJv2CJoeOBmFpTnHStf9slqa/kwlHS8DgvzLDtECTwR/fPfUM6FZfeg99MNK7+nSW5FqzcIsy/BKgcQgQFHwH9lAnVgEUIoafqDR4Fko+vTP96NaZTqEw9BDi5sDY9uLdsOt5XCIr9OaflONkBsWnqmlAGhsZLlLcwIDAQAB";
    /**
     * 公钥加密
     */
    public static String encryptByPublicKey(String data) {
        try {
            byte[] keyBytes = Base64.decode(FUMIAO_KEY, NO_WRAP);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = null;
            keyFactory = KeyFactory.getInstance(RSA);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] mi = cipher.doFinal(data.getBytes());

            return Base64.encodeToString(mi, Base64.CRLF);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }
}
