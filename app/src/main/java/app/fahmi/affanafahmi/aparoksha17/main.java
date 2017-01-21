package app.fahmi.affanafahmi.aparoksha17;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);



        ImageView animationTarget = (ImageView) this.findViewById(R.id.testImage);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animationTarget.startAnimation(animation);

        Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(tf);

        new ParticleSystem(this, 500, R.drawable.pop, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.testImage), 500);
        new ParticleSystem(this, 500, R.drawable.pop2, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.testImage), 500);

        ActivityCompat.requestPermissions(main.this,
                new String[]{Manifest.permission.VIBRATE},
                1);

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 200 milliseconds
        v.vibrate(200);


    }

    public void animate(View view) {
        view.setEnabled(false);
        ImageView animationTarget = (ImageView) this.findViewById(R.id.testImage);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animationTarget.startAnimation(animation);


        new ParticleSystem(this, 500, R.drawable.pop, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.testImage), 500);
        new ParticleSystem(this, 500, R.drawable.pop2, 5000)
                .setSpeedRange(2.1f, 0.02f)
                .oneShot(findViewById(R.id.testImage), 500);
        ActivityCompat.requestPermissions(main.this,
                new String[]{Manifest.permission.VIBRATE},
                1);

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 200 milliseconds
        v.vibrate(200);


    }

}
