package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.navid.AzmoonClient.data.Data;

public class UserMain extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        final Button btnTakeTest = (Button) findViewById(R.id.btnTakeTest);
        final Button btnMyResult = (Button) findViewById(R.id.btnMyResult);
        final Button btnEditMPD = (Button) findViewById(R.id.btnEditMPD);

        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),UserTest.class));
                UserMain.this.finish();
            }
        });

        btnMyResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String query = new SignIn().getFromSocket("/ures/info?uid="+ Data.getUser_id()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
                long tmp = 1;
                try
                {
                    tmp = Long.parseLong(query);
                } catch (Exception e)
                {
                }
                if (tmp == 0) {
                    Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
                } else if (query == "")
                {
                    Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                }else
                {
                    try
                    {
                        final String[] list_query = query.split("::");
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserMain.this);
                        builder.setMessage("امتیاز: " + list_query[0] + "\n"+"قبول شده؟ " + list_query[1]);
                        builder.setCancelable(false);
                        builder.setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        btnEditMPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),UserEdit.class));
            }
        });


    }
}
