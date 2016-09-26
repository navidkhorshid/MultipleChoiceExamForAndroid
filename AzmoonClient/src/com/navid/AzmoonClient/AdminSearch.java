package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;

public class AdminSearch extends Activity
{
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.admin_search);

        final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        final Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setSearch_phrase(etSearch.getText().toString());
                startActivity(new Intent(getBaseContext(),AdminSearchResult.class));
            }
        });
    }
}
