package app.fahmi.affanafahmi.aparoksha17.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.fahmi.affanafahmi.aparoksha17.R;
import app.fahmi.affanafahmi.aparoksha17.model.User;

public class SignUP extends AppCompatActivity {

    private static final String TAG = SignUP.class.getSimpleName();
    private EditText email;
    private EditText number;
    private EditText passwd;
    private EditText Edt_name;

    private String password;
    private String mail;
    private String mobile;
    private String name;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ProgressDialog progressdialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressdialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_DARK);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setTitle("Registering You");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        email = (EditText) findViewById(R.id.editTextEmail);
        number = (EditText) findViewById(R.id.editTextMobile);
        passwd = (EditText) findViewById(R.id.editTextPassword);
        Edt_name = (EditText) findViewById(R.id.editTextName);
    }

    public void login(View view) {
        Intent intent = new Intent(SignUP.this, Login.class);
        startActivity(intent);
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
    Boolean isIIITEmail(String email){
        try {
            String mydomain = email;
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@iiita\\.ac\\.in";
            Boolean b = mydomain.matches(emailregex);

            return b;
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUP.this, "Signup successful. Verification email sent.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUP.this, Login.class);
                                startActivity(intent);
                            }
                        }
                    });
        }

    }

    public void signup(View view) {
        password = ""+passwd.getText();
        mail = ""+email.getText();
        mobile = ""+number.getText();
        name = ""+Edt_name.getText();
        if(password.length() > 5) {
            if (isValidEmail(mail)) {
                if (mobile.length() == 10) {
                    progressdialog.show();
                    if (isIIITEmail(mail)) {
                        //IIITA STUDENT
                        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(name, mail, mobile, 0.0, true);
                                    mDatabase.child(task.getResult().getUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                sendVerificationEmail();
                                            }else {
                                                //TODO
                                                //Remove user
                                                Toast.makeText(getApplicationContext(), "Sorry unable to register you!!", Toast.LENGTH_LONG).show();
                                            }
                                            progressdialog.hide();
                                        }
                                    });
                                } else {
                                    Toast.makeText(getApplicationContext(), "Sorry unable to register you!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        //NOT IIITA
                        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(name, mail, mobile, 0.0, false);
                                    mDatabase.child(task.getResult().getUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                sendVerificationEmail();
                                                Toast.makeText(getApplicationContext(), "Registration was successful!!", Toast.LENGTH_LONG).show();
                                            }else {
                                                //TODO
                                                //Remove user
                                                Toast.makeText(getApplicationContext(), "Sorry unable to register you!!", Toast.LENGTH_LONG).show();
                                            }
                                            progressdialog.hide();
                                        }
                                    });
                                } else {
                                    Toast.makeText(getApplicationContext(), "Sorry unable to register you!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid mobile number !!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter a valid emailID !!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Password is too short, minimum 6 characters required !!", Toast.LENGTH_LONG).show();
        }
    }
}
