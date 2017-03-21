package app.fahmi.affanafahmi.aparoksha17.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.fahmi.affanafahmi.aparoksha17.R;

public class Login extends AppCompatActivity {

    private EditText passwd;
    private EditText email;

    private String mailID;
    private String pass;

    private FirebaseAuth mAuth;

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressdialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_DARK);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setTitle("Signing In");
        progressdialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();

        passwd = (EditText) findViewById(R.id.editTextPassword);
        email = (EditText) findViewById(R.id.editTextEmail);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
            if(user.isEmailVerified()){
                Intent intent = new Intent(Login.this, Wallet.class);
                startActivity(intent);
            }

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
            if(user.isEmailVerified()){
            Intent intent = new Intent(Login.this, Wallet.class);
            startActivity(intent);
            }
    }

    Boolean isValidEmail(String email){
        try {
            String mydomain = email;
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = mydomain.matches(emailregex);

            return b;
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void signup(View view) {
        Intent intent = new Intent(Login.this, SignUP.class);
        startActivity(intent);
    }

    public void login(View view) {

        mailID = email.getText()+"";
        pass = passwd.getText()+"";


        if(pass.length()>5 && isValidEmail(mailID)) {
            progressdialog.show();
            mAuth.signInWithEmailAndPassword(mailID, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressdialog.hide();
                    if (task.isSuccessful()) {
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            Toast.makeText(getApplicationContext(), "Login was Successful !!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this, Wallet.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Email not verified . Verify your email to login.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed !!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else
            Toast.makeText(getApplicationContext(),"Invalid login credentials !!" , Toast.LENGTH_LONG).show();
    }

    public void forgot(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        builder.setTitle("Password Recovery");
        builder.setMessage("Enter your email address and we'll send you a recovery link.");
        builder.setCancelable(false);

        final EditText input = new EditText(this);


        input.setHint("Enter your email.");
        input.setHintTextColor(getResources().getColor(R.color.whiteS));
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input.setTextColor(Color.WHITE);
        builder.setView(input);
        builder.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(isValidEmail(input.getText().toString())) {
                    mAuth.sendPasswordResetEmail(input.getText().toString());
                    Toast.makeText(getApplicationContext(),"We’ve sent you a recovery link.", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplicationContext(),"Invalid emailID !!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom))
                .setCancelable(false)
                .setTitle("Exit")
                .setMessage("Would you like to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // logout
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // user doesn't want to logout
                    }
                })
                .show();
    }
}
