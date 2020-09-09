package com.project.gemastik.reminder.verify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.gemastik.reminder.MainActivity;
import com.project.gemastik.reminder.R;
import com.subhrajyoti.passwordview.PasswordView;

public class SignUpActivity extends AppCompatActivity {

    EditText username, email;
    PasswordView pw, cpw;
    CardView signin, googleSign;

    FirebaseAuth fAuth;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.usersign);
        email = findViewById(R.id.mailsign);
        pw = findViewById(R.id.pwsign);
        cpw = findViewById(R.id.cpwsign);


        fAuth = FirebaseAuth.getInstance();

        signin = findViewById(R.id.btnsignup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registUser();
            }
        });

        googleSign = findViewById(R.id.btnsignup_google);
        googleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnsignup_google:
                        signIn();
                        break;
                }
            }
        });
    }

    private void signIn(){

    }

    private void registUser(){
        String txuser, txmail, txpass, txcpass;

        txuser = username.getText().toString();
        txmail = email.getText().toString();
        txpass = pw.getText().toString();
        txcpass = cpw.getText().toString();


        if (TextUtils.isEmpty(txuser)){
            Toast.makeText(getApplicationContext(),"Username tidak boleh kosong!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txmail)){
            Toast.makeText(getApplicationContext(),"Email tidak boleh kosong!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(txpass)){
            Toast.makeText(getApplicationContext(),"Password tidak boleh kosong!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(txcpass)){
            Toast.makeText(getApplicationContext(),"Konfirmasi Password tidak boleh kosong!",Toast.LENGTH_SHORT).show();
            return;
        }

        fAuth.createUserWithEmailAndPassword(txmail, txpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intentup = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intentup);
                            Toast.makeText(getApplicationContext(),"Berhasil!",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Gagal membuat akun",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
