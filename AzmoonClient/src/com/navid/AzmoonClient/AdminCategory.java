package com.navid.AzmoonClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;

import java.util.ArrayList;

public class AdminCategory extends Activity
{
    private ArrayList<String> tmp_cat;
    private Spinner spinner;
    private boolean flag = false;
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.admin_category);
        String cat = new SignIn().getFromSocket("/qe/getCategories"+"?un="+Data.getUsername()+"&pw="+Data.getPassword());
        long tmp = 1;
        try
        {
            tmp = Long.parseLong(cat);
        } catch (Exception e)
        {
        }
        if (tmp == 0) {
            Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
        } else if (cat == "")
        {
            Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
        }else
        {
            String[] list_cat = cat.split(";;");
            spinner = (Spinner) findViewById(R.id.spinner);
            tmp_cat = new ArrayList<String>();
            try
            {
                for(String category : list_cat)
                {
                    String[] temp = category.split("::");
                    tmp_cat.add(temp[1]);
                }

                //---Spinner View---
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.select_dialog_item, tmp_cat);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        final int index = spinner.getSelectedItemPosition();
                        Data.setCat_id(index + 1);
                        if(flag==true)
                        {
                            startActivity(new Intent(getBaseContext(), AdminQuestions.class));
                        }else
                        {
                            flag = true;
                        }
                    }

                    public void onNothingSelected(AdapterView<?> arg0)
                    {
                    }
                });



            }catch (Exception e)
            {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

}