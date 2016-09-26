package com.navid.AzmoonClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.navid.AzmoonClient.data.Data;

public class UserEdit extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);
        final EditText nameText = (EditText) findViewById(R.id.editText1);
        final EditText familyText = (EditText) findViewById(R.id.editText2);
        final EditText usernameText = (EditText) findViewById(R.id.editText3);
        final EditText passwordText = (EditText) findViewById(R.id.editText4);
        final EditText addressText = (EditText) findViewById(R.id.editText5);
        final EditText emailText = (EditText) findViewById(R.id.editText6);
        final EditText telText = (EditText) findViewById(R.id.editText7);
        final Button registerBtn = (Button) findViewById(R.id.btnReg);

        try
        {
            String data = new SignIn().getFromSocket("/ue/getUser?uid="+ Data.getUser_id()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
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
            }else
            {
                String[] split_data = data.split("::");
                nameText.setText(split_data[0]);
                familyText.setText(split_data[1]);
                usernameText.setText(split_data[2]);
                passwordText.setText(split_data[3]);
                addressText.setText(split_data[4]);
                emailText.setText(split_data[5]);
                telText.setText(split_data[6]);
            }

        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                        String data = new SignIn().getFromSocket("/ue/updateUser?uid="+ Data.getUser_id()
                                +"&n="+nameText.getText().toString()
                                +"&f="+familyText.getText().toString()
                                +"&u="+usernameText.getText().toString()
                                +"&p="+passwordText.getText().toString()
                                +"&a="+addressText.getText().toString()
                                +"&e="+emailText.getText().toString()
                                +"&t="+telText.getText().toString()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
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
                        Toast.makeText(getBaseContext(), "با موفقیت به روز رسانی شد.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(),UserMain.class));
                        UserEdit.this.finish();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
