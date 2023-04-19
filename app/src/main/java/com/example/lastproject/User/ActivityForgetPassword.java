package com.example.lastproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityForgetPassword extends AppCompatActivity {


    private Button buttonPwdReset;
    private EditText editTextPwdResetEmail;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        TextInputLayout resetemail = findViewById(R.id.forget_link);
        EditText resetEmailForget = resetemail.getEditText();

        buttonPwdReset = findViewById(R.id.reset_password);


        buttonPwdReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = resetEmailForget.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ActivityForgetPassword.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                    resetEmailForget.setError("Email is required");
                    resetEmailForget.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(ActivityForgetPassword.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    resetEmailForget.setError("Valid email is required");
                    resetEmailForget.requestFocus();
                }else{
                    resetPassword(email);
                }
            }

            private void resetPassword(String email) {
                authProfile = FirebaseAuth.getInstance();
                authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ActivityForgetPassword.this, "Please check your inbox for password reset link", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ActivityForgetPassword.this,Login.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();


                        }else{
                            Toast.makeText(ActivityForgetPassword.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}