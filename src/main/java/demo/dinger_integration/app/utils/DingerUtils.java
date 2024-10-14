package demo.dinger_integration.app.utils;

import demo.dinger_integration.app.constant.DingerConstant;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import static org.apache.commons.codec.digest.HmacUtils.hmacSha256;
import org.apache.commons.lang3.ArrayUtils;

import static demo.dinger_integration.app.constant.DingerConstant.SECRET_KEY;


@Component
public class DingerUtils {

    public static String getEncodedPayload(String jsonPayload){

        String encryptPayload;
        try {
            encryptPayload = Base64.getEncoder().encodeToString(encrypt(jsonPayload));
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return URLEncoder.encode(encryptPayload, StandardCharsets.UTF_8);
    }

    public static String getHashValue(String jsonPayload) {
        return hashedValue(jsonPayload);
    }

    private static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    private static byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(DingerConstant.PAYMENT_ENCRYPTION_KEY));
        byte[] enBytes = null;
        int i = 0;
        while (i < data.length()) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data.getBytes(), i, i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
            i += 64;
        }
        return enBytes;
    }

    private static String hashedValue(String payload) {

        byte[] result = hmacSha256(SECRET_KEY.getBytes(), payload.getBytes());
        return bytesToHex(result);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
}