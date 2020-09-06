package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Db_Helper dbHelper;
    EditText getEt_name, getEt_id, getEt_password, getEt_phone;
    TextView login_page_txt;
    Button signUp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
    }

    private void init() {

        dbHelper = new Db_Helper(this);

        getEt_name = findViewById(R.id.sign_up_name);
        getEt_phone = findViewById(R.id.sign_up_phone);
        getEt_id = findViewById(R.id.sign_up_id);
        getEt_password = findViewById(R.id.sign_up_password);
        login_page_txt = findViewById(R.id.tv_login_page);
        signUp_btn = findViewById(R.id.btn_create_account);
    }

    public void createAccount(View view){
        String name = getEt_name.getText().toString();
        String phone = getEt_phone.getText().toString();
        String email = getEt_id.getText().toString();
        String password = getEt_password.getText().toString();

        if(name.isEmpty()||phone.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
        }
        else {
            boolean checkEmail = dbHelper.check_email(email);

           if(checkEmail == true){
                boolean insert = dbHelper.insertData(name,email,password,phone);
                if(insert == true){
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void login_page(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
