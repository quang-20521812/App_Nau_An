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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fireStore;
    EditText editTextEmail,editTextPassword,editTextSDT,editTextFullname, editTextConfirmPassWord;
    TextView txtCheckName, txtCheckMail,txtCheckSDT,txtCheckPassword, txtCheckConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
//        getSupportActionBar().hide();

        editTextEmail = (EditText) findViewById(R.id.editTextEmailSU);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordSU);
        editTextSDT = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextFullname = (EditText) findViewById(R.id.editTextName);
        editTextConfirmPassWord = (EditText) findViewById(R.id.editTextConfirmPass);
        txtCheckName = findViewById(R.id.txtName);
        txtCheckSDT = findViewById(R.id.txtCheckSDT);
        txtCheckMail = findViewById(R.id.txtCheckMail);
        txtCheckPassword = findViewById(R.id.txtCheckPass);
        txtCheckConfirmPassword = findViewById(R.id.txtCheckCFPass);

        firebaseAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        resetText();

        findViewById(R.id.txtSignIn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignInScreen.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnSignUp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassWord.getText().toString();
                String name = editTextFullname.getText().toString();
                String phoneNumber = editTextSDT.getText().toString();
                resetText();

                if (email.isEmpty()) {
                    txtCheckMail.setText("Hãy nhập địa chỉ email!");
                }
                if(password.isEmpty()){
                    txtCheckPassword.setText("Hãy nhập mật khẩu!");
                }
                if(name.isEmpty()){
                    txtCheckName.setText("Hãy nhập họ và tên!");
                }
                if(phoneNumber.isEmpty()){
                    txtCheckSDT.setText("Hãy nhập số điện thoại!");
                }
                if(confirmPassword.isEmpty()){
                    txtCheckConfirmPassword.setText("Hãy nhập lại mật khẩu!");
                }
                if (password.length() < 6) {
                    txtCheckPassword.setText("Mật khẩu quá ngắn! Yêu cầu mật khẩu từ 6 ký tự trở lên!");
                }
                else if (!confirmPassword.equals(password)) {
                    txtCheckConfirmPassword.setText("Mật khẩu xác nhận chưa đúng!");
                }
                else if (confirmPassword.equals(password)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpScreen.this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công !!!", Toast.LENGTH_SHORT).show();
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",name);
                            user.put("Email",email);
                            user.put("Phone",phoneNumber);
                            fireStore.collection("user")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(SignUpScreen.this, "OK", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpScreen.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Đăng ký thất bại !!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void resetText(){
        txtCheckName.setText("");
        txtCheckMail.setText("");
        txtCheckSDT.setText("");
        txtCheckPassword.setText("");
        txtCheckConfirmPassword.setText("");
    }
}