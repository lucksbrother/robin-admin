package com.robin.authserver.security.passwordencoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author Robin
 * @Desc 密码编码自定义
 */
@Slf4j
public class RobinPasswordEncoder implements PasswordEncoder {

    private static final StringJoiner JOINER = new StringJoiner("@");

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = java.util.Objects.requireNonNull(encrypt(UUID.randomUUID().toString(), "MD5")).substring(0, 4);
        String realPassword = java.util.Objects.requireNonNull(encrypt(rawPassword + salt, "SHA-512")).substring(0, 20);
        return JOINER.add(salt).add(realPassword).toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StringUtils.isEmpty(encodedPassword)) {
            if (log.isErrorEnabled()) {
                log.error("密码数据为空");
            }
            return false;
        }
        String[] parts = encodedPassword.split("@");
        if (parts.length < 1) {
            if (log.isErrorEnabled()) {
                log.error("密码格式不正确");
            }
            return false;
        }
        String salt = parts[0];
        String realPassword = parts[1];
        if (salt == null || realPassword == null) {
            if (log.isErrorEnabled()) {
                log.error("解析后密码格式不正确");
            }
            return false;
        }
        String encryptResult = encrypt(rawPassword + salt, "SHA-512");
        if (StringUtils.isEmpty(encryptResult)) {
            return false;
        }
        if (encryptResult.substring(0, 20).equals(realPassword)) {
            return true;
        }
        return false;
    }


    /**
     * 字符串编码
     *
     * @param str         被编码的字符串
     * @param encryptMode 编码方式："SHA-512"，"MD5"
     * @return 编码后的字符串
     */
    private String encrypt(String str, String encryptMode) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(encryptMode);
        } catch (NoSuchAlgorithmException e) {
            if (log.isErrorEnabled()) {
                log.error("编码实例化出现异常，尝试使用默认SHA-512方式");
            }
            try {
                messageDigest = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException ex) {
                if (log.isErrorEnabled()) {
                    log.error("默认编码实例化出现异常，尝试使用默认SHA-512方式");
                }
                return null;
            }
        }
        messageDigest.update(str.getBytes());
        byte[] byteBuffer = messageDigest.digest();
        StringBuilder strHexString = new StringBuilder();
        for (byte b : byteBuffer) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        return strHexString.toString();
    }
}
