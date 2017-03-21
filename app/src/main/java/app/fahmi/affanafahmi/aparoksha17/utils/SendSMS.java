package app.fahmi.affanafahmi.aparoksha17.utils;

/**
 * Created by affan on 15/3/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSMS {
    public static void sendSms(final String event_name,final String num,final String usr_name,final String amt) {
        new Thread(new Runnable() {
            @Override
            public void run() {


            try {
                // Construct data
                String c = "riv5tvU4SRGW";
                String b = "-tt8pxoQcLH";
                String a = "e/2A9jDvHRk";
                String d = "j3Y1qIui";
                String user = "username=" + URLEncoder.encode("aparoksha.dev@gmail.com", "UTF-8");
                String hash = "&apikey=" + URLEncoder.encode(a+b+c+d, "UTF-8");
                String message = "&message=" + URLEncoder.encode(usr_name+" is now registered for the event "+event_name+". Paid "+amt+ " INR", "UTF-8");
                String sender = "&sender=" + URLEncoder.encode("IIITA PAY", "UTF-8");
                String numbers = "&numbers=" + URLEncoder.encode(num, "UTF-8");

                // Send data
                String data = "http://api.textlocal.in/send/?" + user + hash + numbers + message + sender;
                URL url = new URL(data);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String sResult="";
                while ((line = rd.readLine()) != null) {
                    // Process line...
                    sResult=sResult+line+" ";
                }
                rd.close();

                System.out.println("\n\n\n\n\n==========================>"+sResult);
            } catch (Exception e) {
                System.out.println("\n\n\n\n\n==========================>"+"Error SMS "+e);
                //return "Error "+e;
            }
            }
        }).start();
    }
}