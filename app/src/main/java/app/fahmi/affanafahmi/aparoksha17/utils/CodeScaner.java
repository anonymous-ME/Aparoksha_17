package app.fahmi.affanafahmi.aparoksha17.utils;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.fahmi.affanafahmi.aparoksha17.activities.EntryPass;
import app.fahmi.affanafahmi.aparoksha17.activities.Wallet;
import app.fahmi.affanafahmi.aparoksha17.model.Ticket;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodeScaner extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private DatabaseReference mDatabase;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        setTitle("IIITA PAY");


    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    public Double bal = 0.0;
    public volatile boolean isUpdated = false;
    @Override
    public void handleResult(final Result result) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child("Yjks4IPR4sYyXL2GPyyZ7imyKs82").child("wallet_balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                bal =  Double.parseDouble(snapshot.getValue().toString());
                System.out.println(snapshot.getValue());
                isUpdated = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                isUpdated = true;
            }
        });
        Toast.makeText(CodeScaner.this, "Booking Your Ticket...", Toast.LENGTH_LONG).show();
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                while(!isUpdated);
                String[] str = result.getText().split(":");
                try {
                    if ((Double.parseDouble(str[1])) <= bal) {
                        Bundle data = new Bundle();
                        data.putString("event_name", str[0]);
                        data.putString("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                        data.putString("amt", str[1] + "  ₹");
                        Ticket tkt = new Ticket("ID", str[0], Double.parseDouble(str[1]), new Date());
                        mDatabase.child("users").child("Yjks4IPR4sYyXL2GPyyZ7imyKs82").child("Tickets").child(str[0]).setValue(tkt);
                        mDatabase.child("users").child("Yjks4IPR4sYyXL2GPyyZ7imyKs82").child("wallet_balance").setValue( round( ((bal-(Double.parseDouble(str[1])))) , 2 ) );
                        CodeScaner.this.startActivity(new Intent(CodeScaner.this, EntryPass.class).putExtras(data));
                        SendSMS.sendSms(str[0],str[2],"Rahul",str[1]);
                        }else{
                        Toast.makeText(CodeScaner.this, "Your balance is too low to make this purchase.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CodeScaner.this, Wallet.class));
                    }
                }catch (Exception e){
                    Toast.makeText(CodeScaner.this, "Invalid Event QR Code!! ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CodeScaner.this, Wallet.class));
                }
            }
        });



    }




    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
