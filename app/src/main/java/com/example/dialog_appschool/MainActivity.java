package com.example.dialog_appschool;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    SharedPreferences sp;
    Dialog d;

    EditText etUserName;
    EditText etPass;

    Button btnCustomLogin;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);


        sp = getSharedPreferences("details1",0);


    }

    public void createLoginDialog()
    {
        String stringUser = sp.getString("username", null);
        String stringPass = sp.getString("password", null);


        d = new Dialog(this);
        d.setContentView(R.layout.custom_layout);

        d.setTitle("Login");
        d.setCancelable(true); // exit on touch outside

        etUserName = d.findViewById(R.id.etUserName);
        etPass = d.findViewById(R.id.etPassword);

        if ((stringUser != null) && (stringPass != null))
        {
            etUserName.setText(stringUser);
            etPass.setText(stringPass);

        }



        btnCustomLogin = d.findViewById(R.id.btnDialogLogin);
        btnCustomLogin.setOnClickListener(this);

        d.show();

    }

    public void onClick(View v)
    {
        if(v == btnLogin)
        {
            createLoginDialog();
        }

        else if(v == btnCustomLogin)
        {
            Toast.makeText(this,"username password saved",Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", etUserName.getText().toString());
            editor.putString("password", etPass.getText().toString());
            editor.commit();
            Toast.makeText(this, "Saved",Toast.LENGTH_SHORT).show();


            d.dismiss();



        }

    }
}

