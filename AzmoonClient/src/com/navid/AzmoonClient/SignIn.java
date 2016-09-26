package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.navid.AzmoonClient.data.Data;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class SignIn extends Activity
{
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (  Integer.valueOf(android.os.Build.VERSION.SDK) < 7 //Instead use android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            // Take care of calling this method on earlier versions of
            // the platform where it doesn't exist.
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        // This will be called either automatically for you on 2.0
        // or later, or by the code above on earlier versions of the
        // platform.
        return;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText userText = (EditText) findViewById(R.id.editText);
        final EditText passText = (EditText) findViewById(R.id.editText1);
        final Button SignInBtn = (Button) findViewById(R.id.btnSignIn);
        final Button signUpBtn = (Button) findViewById(R.id.btnSignUp);
        final Button endBtn = (Button) findViewById(R.id.endBtn);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = userText.getText().toString();
                String pw = passText.getText().toString();
                if (un.length() > 50 || pw.length() > 50) {
                    Toast.makeText(SignIn.this, "نام کاربری و رمزعبور طولانی میباشند.", Toast.LENGTH_LONG).show();
                } else {
                    String uid = getFromSocket("/authentication/login?user=" + un + "&pass=" + pw);
                    long tmp = 1;
                    try {
                        tmp = Long.parseLong(uid);
                    } catch (Exception e) {
                    }
                    if (tmp == 0) {
                        Toast.makeText(SignIn.this, "نام کاربری و رمز عبور اشتباه است.", Toast.LENGTH_LONG).show();
                    } else if (uid == "") {
                        Toast.makeText(SignIn.this, "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                    } else if (tmp != -2) {
                        Toast.makeText(SignIn.this, "کاربر با موفقیت وارد سیستم شد.", Toast.LENGTH_LONG).show();
                        Data.setUser_id(Long.parseLong(uid));
                        Data.setUsername(un);
                        Data.setPassword(pw);
                        startActivity(new Intent(SignIn.this, UserMain.class));
                        SignIn.this.finish();
                    } else if (tmp == -2) {
                        Toast.makeText(SignIn.this, "مدیر با موفقیت وارد سیستم شد.", Toast.LENGTH_LONG).show();
                        Data.setUsername(un);
                        Data.setPassword(pw);
                        startActivity(new Intent(SignIn.this, AdminMain.class));
                    }

                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                builder.setMessage("آیا قصد خروج را دارید؟");
                builder.setCancelable(false);
                builder.setPositiveButton("آری", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SignIn.this.finish();
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
            }
        });

    }


    public String getFromSocket(String fullUrl) {
        String hostAddress = "http://192.168.43.47:9888";
        InputStreamReader input = null;
        String content = "";
        int reader;
        URL url = null;
        fullUrl = fullUrl.replaceAll(" ","%20");
        try
        {
            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            url = new URL(hostAddress + fullUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Charset charset = Charset.forName("UTF8");
            input = new InputStreamReader(urlConnection.getInputStream(), charset);
            reader = input.read();
            while (reader != -1)
            {
                content += (char) reader;
                reader = input.read();
            }
            input.close();
        } catch (Exception e)
        {
            Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return content;
    }

}

