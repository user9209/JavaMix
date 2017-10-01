import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    MessageDigest md5;

    public MD5() throws NoSuchAlgorithmException {
        md5 = MessageDigest.getInstance("MD5");
    }

    public String md5(String in) {
        md5.update(StandardCharsets.UTF_8.encode(in));
        return String.format("%032x",new BigInteger(1,md5.digest()));
    }

    public static String getMD5(String in)
    {
        try {
            return new MD5().md5(in);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
