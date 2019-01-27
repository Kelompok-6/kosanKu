package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button WelcomeLogin;
    Button WelcomeRegiseter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        WelcomeLogin = (Button) findViewById(R.id.btnWelcomeLogin);
        WelcomeLogin.setOnClickListener(this);

        WelcomeRegiseter = (Button) findViewById(R.id.btnWelcomeRegister);
        WelcomeRegiseter.setOnClickListener(this);
    }


    private void Login(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Register(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(),"Halo Halo",Toast.LENGTH_SHORT).show();
        if(v == WelcomeLogin){
            Login();
        } else if (v == WelcomeRegiseter){
            Register();
        }
    }

}
