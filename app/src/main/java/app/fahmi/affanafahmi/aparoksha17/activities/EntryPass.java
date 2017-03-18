package app.fahmi.affanafahmi.aparoksha17.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.plattysoft.leonids.ParticleSystem;

import app.fahmi.affanafahmi.aparoksha17.R;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class EntryPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_pass);
        setTitle("E - Entry Pass");
        Bundle data = getIntent().getExtras();
        if(data != null) {
            ((TextView) findViewById(R.id.event_name)).setText(data.getString("event_name"));
            ((TextView) findViewById(R.id.dot)).setText(data.getString("date"));
            ((TextView) findViewById(R.id.amt)).setText(data.getString("amt"));
        }
        ImageView animationTarget = (ImageView) this.findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animationTarget.startAnimation(animation);


        new ParticleSystem(this, 500, R.drawable.pop, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.imageView2), 500);
        new ParticleSystem(this, 500, R.drawable.pop2, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.imageView2), 500);
        ActivityCompat.requestPermissions(EntryPass.this,
                new String[]{Manifest.permission.VIBRATE},
                1);

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 200 milliseconds
        v.vibrate(200);
        showQR();
    }

    public void animate(View view) {
        ImageView animationTarget = (ImageView) this.findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animationTarget.startAnimation(animation);


        new ParticleSystem(this, 500, R.drawable.pop, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.imageView2), 500);
        new ParticleSystem(this, 500, R.drawable.pop2, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.imageView2), 500);
        ActivityCompat.requestPermissions(EntryPass.this,
                new String[]{Manifest.permission.VIBRATE},
                1);

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 200 milliseconds
        v.vibrate(200);
    }
    public void showQR(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            ImageView imageView = (ImageView) findViewById(R.id.imageView2);

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Bitmap bitmap = encodeAsBitmap(user.getUid());
                            imageView.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }});
            }
        }).start();

    }
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 1000, 1000, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 1000, 0, 0, w, h);
        return bitmap;
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(EntryPass.this, Wallet.class));
    }
}
