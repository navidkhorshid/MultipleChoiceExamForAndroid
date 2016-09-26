package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;

public class AdminSearchResult extends Activity
{
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.admin_search_result);

        ListView searchResult = (ListView) findViewById(R.id.listView);
        String query = new SignIn().getFromSocket("/rs/getUserAzmoon?sp="+Data.getSearch_phrase()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
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
            final String[] list_query = query.split(";;");

            try
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_query);
                searchResult.setAdapter(adapter);
                searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        final int index = arg2;
                        String[] tmp = list_query[index].split("::");
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminSearchResult.this);
                        builder.setMessage("شماره کاربری: " + tmp[0] +"\n"+"نام: " + tmp[1] + " " + tmp[2] + "\n"+"امتیاز: " + tmp[3] + "\n"+"قبول شده؟ " + tmp[4]);
                        builder.setCancelable(false);
                        builder.setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                });


            }catch (Exception e)
            {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }
}
