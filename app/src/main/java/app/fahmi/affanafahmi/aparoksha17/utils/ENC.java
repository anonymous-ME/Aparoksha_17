package app.fahmi.affanafahmi.aparoksha17.utils;

import android.util.Base64;

/**
 * Created by affan on 15/3/17.
 */

public class ENC {
    public static String decrypt(String strToDecrypt)
    {
        String data = "::";
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;
        byte[] bytes = Base64.decode(strToDecrypt,flags);
        for (int i = 0;i < bytes.length;i++){
            bytes[i] = (byte) (bytes[i] - (3+i));
        }
        data = new String(bytes);
        return data;
    }
}
