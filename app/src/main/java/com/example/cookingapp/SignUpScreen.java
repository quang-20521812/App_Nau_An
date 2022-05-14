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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    FirebaseFirestore firestore;
    EditText editTextUsername,editTextPassword,editTextSDT,editTextFullname, editTextConfirmPassWord;
    TextView txtCheckName, txtCheckUsername,txtCheckSDT,txtCheckPassword, txtCheckConfirmPassword;
    Boolean isSuccessSignUp = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_screen);
//        getSupportActionBar().hide();

        editTextUsername = (EditText) findViewById(R.id.editTextUsernameSU);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordSU);
        editTextSDT = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextFullname = (EditText) findViewById(R.id.editTextName);
        editTextConfirmPassWord = (EditText) findViewById(R.id.editTextConfirmPass);
        txtCheckName = findViewById(R.id.txtName);
        txtCheckSDT = findViewById(R.id.txtCheckSDT);
        txtCheckUsername = findViewById(R.id.txtCheckUsername);
        txtCheckPassword = findViewById(R.id.txtCheckPass);
        txtCheckConfirmPassword = findViewById(R.id.txtCheckCFPass);

        firestore = FirebaseFirestore.getInstance();

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
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassWord.getText().toString();
                String name = editTextFullname.getText().toString();
                String sdt = editTextSDT.getText().toString();
                resetText();

                if (username.isEmpty()) {
                    txtCheckUsername.setText("Hãy nhập tên đăng nhập!");
                }
                if(password.isEmpty()){
                    txtCheckPassword.setText("Hãy nhập mật khẩu!");
                }
                if(name.isEmpty()){
                    txtCheckName.setText("Hãy nhập họ và tên!");
                }
                if(sdt.isEmpty()){
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
//
                    Map<String,Object> user = new HashMap<>();
                    user.put("Name",name);
                    user.put("Username",username);
                    user.put("Password",password);
                    user.put("Phone",sdt);
                    firestore.collection("User").document(username)
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignUpScreen.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpScreen.this, "Đăng ký thất bại!", Toast.LENGTH_LONG).show();
                                }
                            });
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                }
            }
        });
    }
    private void resetText(){
        txtCheckName.setText("");
        txtCheckUsername.setText("");
        txtCheckSDT.setText("");
        txtCheckPassword.setText("");
        txtCheckConfirmPassword.setText("");
    }

}