package com.example.lastproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    private EditText editTextLoginEmail, editTextLoginPwd;
    private FirebaseAuth authProfile;

    private static final String TAG ="Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);


        TextInputLayout email = findViewById(R.id.login_email);
        EditText editemail = email.getEditText();

        TextInputLayout pwd = findViewById(R.id.login_password);
        EditText pwdedit = pwd.getEditText();

      /*  editTextLoginEmail = findViewById(R.id.login_email);
        editTextLoginPwd = findViewById(R.id.login_password);*/

        authProfile = FirebaseAuth.getInstance();

        Button buttonLogin = findViewById(R.id.login_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = editemail.getText().toString();
                String textPwd = pwdedit.getText().toString();


                if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    editemail.setError("Email is required");
                    editemail.requestFocus();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(Login.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    editemail.setError("Valid email is required");
                    editemail.requestFocus();
                }else if (TextUtils.isEmpty(textPwd)){
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    pwdedit.setError("Password is required");
                    pwdedit.requestFocus();
                }else {
                    loginUser(textEmail, textPwd );
                }
            }

            private void loginUser(String textEmail, String textPwd) {

                authProfile.signInWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), UserDashboard.class));

                            FirebaseUser firebaseUser = authProfile.getCurrentUser();

                            if(firebaseUser.isEmailVerified()){
                                Toast.makeText(Login.this, "You are logged in now", Toast.LENGTH_SHORT).show();
                            }else{
                                firebaseUser.sendEmailVerification();
                                authProfile.signOut();
                                showAlertDialog();
                            }


                        }else{

                            try {
                                throw task.getException();
                            }catch (FirebaseAuthInvalidUserException e){
                                editemail.setError("User does not exists or is no longer valid.Please register again");
                                editemail.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                editemail.setError("User does not exists or is no longer valid.Please register again");
                                editemail.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                            Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });












    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email now. You can not login without email verification");


        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    public void callCreateAccount(View View) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
    public void callForgetPassword(View View) {
        startActivity(new Intent(getApplicationContext(), ActivityForgetPassword.class));
    }
}