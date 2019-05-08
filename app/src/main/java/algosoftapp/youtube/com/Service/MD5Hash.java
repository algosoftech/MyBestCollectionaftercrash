package algosoftapp.youtube.com.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by abc on 28/12/2018.
 */

public class MD5Hash {
    public static void main(String[] args) {

        String s = "896b071c05e22f3e4cf24b66ef9e5ab3 ";
        String  res = md5(s);
        System.out.println(res);

    }
    private static String md5(String s) {
        try {

            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }
}
