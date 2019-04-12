import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;
import com.iapppay.security.api.impl.RSAX509PublicKeyEncrypt;

public class PwdUtil {
    public static void main(String[] args) throws Exception {
        RSAX509PublicKeyEncrypt en = null;
        // 数据库中的公约
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGrwmrWyMSzfu9VNCU/1vxxHRfyoX20f8DJbEDYiufulKVtTRi/zsSqL8/OuJMdSq+k40bcc2q2ckkfhu8dPthGAfvnap6lwTNW/oQbT03lfgCXiJY8/JtkUWxd5IZ5k/w0m7XIKDuYZb7JESujtpOsj+/8vI4Ne3Vk+2PZprypQIDAQAB";
        try {
            en = new RSAX509PublicKeyEncrypt(pubKey);
            String pwd = "112266"; // 用户的支付密码
            int length = pwd.length();
            String temp = "";
            if (length < 10) {
                temp = "0" + length;
            } else {
                temp = "" + length;
            }
            String key = "abcdefghigkl"; // 客户端随机8~16位字符串
            length = key.length();
            String temp2 = "";
            if (length < 10) {
                temp2 = "0" + length;
            } else {
                temp2 = "" + length;
            }
            String s = temp + pwd + "00" + temp2 + key;
            byte[] rsa = en.encryptToBytes(s.getBytes());
            String base = Base64.getEncoder().encodeToString(rsa);
            System.out.println("PayPasswd:" + "2|2|" + base);
            // 客户端随机公约
            pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSPHGcV3h4+eOyMwt2DdEVFeA1oyNeEd3I4pOcY9L4iCnKEHYA9GRw3KbaOo0S7ipqUMYgiLO2blNlXdaVcF8eGwQ8WeHuc81iWsaih853ttUEsNB4JDT1GH0ijkWACw+nKWuAQ0trDzXxaopDrU+EclEkX+JMZlc3IQprWkWSWwIDAQAB";
            System.out.println("PubKey:" + pubKey);
            String sign = DigestUtils.sha256Hex(pubKey + "&" + key);
            System.out.println("Sign:" + sign);
        } finally {
        }
    }
}
