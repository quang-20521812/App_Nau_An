package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText editTextEmail,editTextPassword;
    TextView txtCheckEmail, txtCheckPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        getSupportActionBar().hide();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        txtCheckEmail = findViewById(R.id.txtCheckEmail);
        txtCheckPassword = findViewById(R.id.txtCheckPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        txtCheckEmail.setText("");
        txtCheckPassword.setText("");

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (email.isEmpty()) {
                    txtCheckEmail.setText("Hãy nhập email!");
                }
                if(password.isEmpty()){
                    txtCheckPassword.setText("Hãy nhập mật khẩu!");
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                }
            }
        });
        findViewById(R.id.txtSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpScreen.class);
                startActivity(intent);
            }
        });
    }
}