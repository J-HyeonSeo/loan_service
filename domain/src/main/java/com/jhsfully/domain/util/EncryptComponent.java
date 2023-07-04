package com.jhsfully.domain.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class EncryptComponent {

    @Value("${encrypt.secretKey}")
    private String secretKey;

    private Base64.Encoder encoder = Base64.getEncoder();
    private Base64.Decoder decoder = Base64.getDecoder();


    public String encryptString(String string) throws InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedString = cipherPkcs5(Cipher.ENCRYPT_MODE).doFinal(
                string.getBytes(StandardCharsets.UTF_8)
        );
        return new String(encoder.encode(encryptedString));
    }

    public String decryptString(String string) throws InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] byteString = decoder.decode(string);

        byte[] decryptString = cipherPkcs5(Cipher.DECRYPT_MODE).doFinal(
            byteString
        );

        return new String(decryptString);
    }

    private Cipher cipherPkcs5(int opMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey sk = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8));
        c.init(opMode, sk, iv);
        return c;
    }
}
