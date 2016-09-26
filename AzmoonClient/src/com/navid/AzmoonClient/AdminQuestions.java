package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;

import java.util.ArrayList;

public class AdminQuestions extends Activity
{
    private ListView ll;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.admin_questions);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AdminUpload.class));
                AdminQuestions.this.finish();
            }
        });
        String questions = new SignIn().getFromSocket("/qe/getQuestions?cid="+ Data.getCat_id()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
        long tmp = 1;
        try
        {
            tmp = Long.parseLong(questions);
        } catch (Exception e)
        {
        }
        if (tmp == 0) {
            Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
        } else if (questions == "")
        {
            Toast.makeText(getBaseContext(),"داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
        }else
        {
            final String[] list_questions = questions.split(";;");
            ll = (ListView) findViewById(R.id.listlevel);
            try
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_questions);

                ll.setAdapter(adapter);
                ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        try
                        {
                            //list[arg2]
                            final int index = arg2;
                            final String[] tmp = list_questions[index].split("::");
                            AlertDialog.Builder builder = new AlertDialog.Builder(AdminQuestions.this);
                            builder.setMessage("آیا مایل هستید سوال زیر پاک شود؟"+"\n\n"+"سوال:"+"\n" +
                                    tmp[1]);
                            builder.setCancelable(false);
                            builder.setPositiveButton("آری", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    long questionId = Long.parseLong(tmp[0]);
                                    String string = new SignIn().getFromSocket("/qe/remove?qid="+questionId+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
                                    long tmp = 1;
                                    try
                                    {
                                        tmp = Long.parseLong(string);
                                    } catch (Exception e)
                                    {}
                                    if (tmp == 0) {
                                        Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
                                    } else if (string == "")
                                    {
                                        Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                                    } else if (tmp==1)
                                    {
                                        Toast.makeText(getBaseContext(), "با موفقیت پاک شد.", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getBaseContext(),AdminCategory.class));
                                        AdminQuestions.this.finish();
                                    }
                                }
                            });
                            builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            ////////////////////
                            builder.show();
                        }catch (Exception ee)
                        {
                            Toast.makeText(getBaseContext(), ee.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }catch (Exception e)
            {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

}