package app.fahmi.affanafahmi.aparoksha17.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import com.plattysoft.leonids.ParticleSystem;

import app.fahmi.affanafahmi.aparoksha17.R;

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

        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
      /*  ((TextView) findViewById(R.id.textView3)).setTypeface(tf);
        ((TextView) findViewById(R.id.event_name)).setTypeface(tf);
        ((TextView) findViewById(R.id.dot_lbl)).setTypeface(tf);
        ((TextView) findViewById(R.id.dot)).setTypeface(tf);
        ((TextView) findViewById(R.id.dot_lbl)).setTypeface(tf);
        ((TextView) findViewById(R.id.event_name_lbl)).setTypeface(tf);
        ((TextView) findViewById(R.id.amt)).setTypeface(tf);
        ((TextView) findViewById(R.id.amt_lbl)).setTypeface(tf);*/


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
    @Override
    public void onBackPressed() {
        startActivity(new Intent(EntryPass.this, Wallet.class));
    }
}
