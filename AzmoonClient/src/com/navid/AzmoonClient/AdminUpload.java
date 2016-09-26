package com.navid.AzmoonClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;


public class AdminUpload extends Activity
{
    private long true_answer = 0;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.admin_upload);
        final EditText etQuestion = (EditText) findViewById(R.id.etQuestion);
        final EditText etA = (EditText) findViewById(R.id.etA);
        final EditText etB = (EditText) findViewById(R.id.etB);
        final EditText etC = (EditText) findViewById(R.id.etC);
        final EditText etD = (EditText) findViewById(R.id.etD);
        final RadioButton rBtnA = (RadioButton) findViewById(R.id.radioButtonA);
        final RadioButton rBtnB = (RadioButton) findViewById(R.id.radioButtonB);
        final RadioButton rBtnC = (RadioButton) findViewById(R.id.radioButtonC);
        final RadioButton rBtnD = (RadioButton) findViewById(R.id.radioButtonD);
        Button btnUpload = (Button) findViewById(R.id.btnUpload);

        rBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtnB.setChecked(false);
                rBtnC.setChecked(false);
                rBtnD.setChecked(false);
                true_answer = 1;
            }
        });
        rBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtnA.setChecked(false);
                rBtnC.setChecked(false);
                rBtnD.setChecked(false);
                true_answer = 2;
            }
        });
        rBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtnB.setChecked(false);
                rBtnA.setChecked(false);
                rBtnD.setChecked(false);
                true_answer = 3;
            }
        });
        rBtnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtnB.setChecked(false);
                rBtnC.setChecked(false);
                rBtnA.setChecked(false);
                true_answer = 4;
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    String question = etQuestion.getText().toString();
                    String answerA = etA.getText().toString();
                    String answerB = etB.getText().toString();
                    String answerC = etC.getText().toString();
                    String answerD = etD.getText().toString();
                    if(true_answer == 0)
                    {
                        Toast.makeText(getBaseContext(),"لطفا جوابی را انتخاب کنید!",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        String query = new SignIn().getFromSocket("/qe/set?cid="+ Data.getCat_id()+"&q="+question+"&a="+answerA+"&b="+answerB+"&c="+answerC+"&d="+answerD+"&ta="+true_answer+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
                        long tmp = -1;
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
                        }else if (tmp == 1)
                        {
                            Toast.makeText(getBaseContext(), "با موفقیت ثبت شد.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getBaseContext(),AdminQuestions.class));
                            AdminUpload.this.finish();
                        }
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
