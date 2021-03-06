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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
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
        getSupportActionBar().hide();

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
                    txtCheckUsername.setText("H??y nh???p t??n ????ng nh???p!");
                }
                if(password.isEmpty()){
                    txtCheckPassword.setText("H??y nh???p m???t kh???u!");
                }
                if(name.isEmpty()){
                    txtCheckName.setText("H??y nh???p h??? v?? t??n!");
                }
                if(sdt.isEmpty()){
                    txtCheckSDT.setText("H??y nh???p s??? ??i???n tho???i!");
                }
                if(confirmPassword.isEmpty()){
                    txtCheckConfirmPassword.setText("H??y nh???p l???i m???t kh???u!");
                }
                if (password.length() < 6) {
                    txtCheckPassword.setText("M???t kh???u qu?? ng???n! Y??u c???u m???t kh???u t??? 6 k?? t??? tr??? l??n!");
                }
                else if (!confirmPassword.equals(password)) {
                    txtCheckConfirmPassword.setText("M???t kh???u x??c nh???n ch??a ????ng!");
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
                                    Toast.makeText(SignUpScreen.this, "????ng k?? th??nh c??ng!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpScreen.this, "????ng k?? th???t b???i!", Toast.LENGTH_LONG).show();
                                }
                            });

                    String[] days = new String[]{"hom_nay", "ngay_mai", "ngay_kia"};
                    Map<String, Object> Menu = new HashMap<>();
                    Menu.put("sang", Arrays.asList("null"));
                    Menu.put("trua", Arrays.asList("null"));
                    Menu.put("toi", Arrays.asList("null"));

                    for (String day : days) {
                        firestore
                                .collection("User")
                                .document(username)
                                .collection("MenuFood")
                                .document(day)
                                .set(Menu);
                    }
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