package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Db_Helper dbHelper;
    EditText getEt_login_id, getEt_login_password;
    Button login_button;
    TextView tv_signUp_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }

    private void init() {

        dbHelper = new Db_Helper(this);

        getEt_login_id = findViewById(R.id.login_id);
        getEt_login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.btn_login);
        tv_signUp_page = findViewById(R.id.sign_up_page);

    }

    public void onClick_SignUp(View view){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
    public void onClick_login(View view){

        String email = getEt_login_id.getText().toString();
        String password = getEt_login_password.getText().toString();

        boolean check_login = dbHelper.email_password(email,password);

        if(check_login == true){

            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_LONG).show();
        }




    }
}
