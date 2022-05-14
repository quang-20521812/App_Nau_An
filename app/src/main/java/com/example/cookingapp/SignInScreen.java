package com.example.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInScreen extends AppCompatActivity {

    EditText editTextUsername,editTextPassword;
    TextView txtCheckUsername, txtCheckPassword;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
//        getSupportActionBar().hide();

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        txtCheckUsername = findViewById(R.id.txtCheckUsernameSI);
        txtCheckPassword = findViewById(R.id.txtCheckPassword);

        txtCheckUsername.setText("");
        txtCheckPassword.setText("");

        firestore = FirebaseFirestore.getInstance();

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.isEmpty()) {
                    txtCheckUsername.setText("Hãy nhập email!");
                }
                if(password.isEmpty()){
                    txtCheckPassword.setText("Hãy nhập mật khẩu!");
                }
                else {
                    DocumentReference docRef = firestore.collection("User").document(username);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    signIn(password,document.get("country").toString());
                                } else {
                                    wrongUsername();
                                }
                            } else {
                                txtCheckPassword.setText(task.getException().toString());
                                Toast.makeText(SignInScreen.this, "Đã xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                            }
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
    private void signIn(String passwordInput, String password){
        if(passwordInput.compareTo(password) == 0)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        else {
            txtCheckPassword.setText("Mật khẩu bạn nhập vào chưa đúng!");
        }
    }
    private void wrongUsername(){
        txtCheckUsername.setText("Tài khoản không tồn tại!");
    }
}
