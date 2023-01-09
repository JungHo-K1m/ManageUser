import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

public class encryption {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String pw = "asdqwe123!@#";
        String en;

        en = enc(enc(pw));

        System.out.println("pw gonna : "+en);

        SHA256 sha256 = new SHA256();

        String pw_sha = sha256.encrypt(sha256.encrypt(pw));
        System.out.println("pw gonna : " + pw_sha);
    }


    //SHA256암호화 알고리즘
    private static String enc(String pw) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        StringBuffer hexString = new StringBuffer();
        for(int i = 0; i < byteData.length; i++){
            String hex = Integer.toHexString(0xff & byteData[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
