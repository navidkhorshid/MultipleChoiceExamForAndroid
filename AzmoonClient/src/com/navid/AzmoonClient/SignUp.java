package com.navid.AzmoonClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class SignUp extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final EditText nameText = (EditText) findViewById(R.id.editText1);
        final EditText familyText = (EditText) findViewById(R.id.editText2);
        final EditText usernameText = (EditText) findViewById(R.id.editText3);
        final EditText passwordText = (EditText) findViewById(R.id.editText4);
        final EditText addressText = (EditText) findViewById(R.id.editText5);
        final EditText emailText = (EditText) findViewById(R.id.editText6);
        final EditText telText = (EditText) findViewById(R.id.editText7);
        final Button registerBtn = (Button) findViewById(R.id.btnReg);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                        String data = new SignIn().getFromSocket("/ur/new?n="+nameText.getText().toString()
                                +"&f="+familyText.getText().toString()
                                +"&u="+usernameText.getText().toString()
                                +"&p="+passwordText.getText().toString()
                                +"&a="+addressText.getText().toString()
                                +"&e="+emailText.getText().toString()
                                +"&t="+telText.getText().toString());
                    long tmp = -1;
                    try
                    {
                        tmp = Long.parseLong(data);
                    } catch (Exception e)
                    {
                    }
                    if (tmp == 0) {
                        Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
                    } else if (data == "")
                    {
                        Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                    }else if (tmp == 1)
                    {
                        Toast.makeText(getBaseContext(), "با موفقیت ثبت شد.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(),SignIn.class));
                    }

                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
