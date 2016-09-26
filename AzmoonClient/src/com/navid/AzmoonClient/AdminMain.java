package com.navid.AzmoonClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMain extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        final Button btnEditQuestion = (Button) findViewById(R.id.btnEditQuestion);
        final Button btnSearchUser = (Button) findViewById(R.id.btnSearchUser);
        final Button btnAcceptedUsers = (Button) findViewById(R.id.btnAcceptedUsers);

        btnEditQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AdminCategory.class));
            }
        });

        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AdminSearch.class));
            }
        });

        btnAcceptedUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AdminAcceptedUsers.class));
            }
        });


    }
}
