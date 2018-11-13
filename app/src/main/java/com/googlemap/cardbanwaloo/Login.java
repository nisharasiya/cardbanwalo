package com.googlemap.cardbanwaloo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.ForgetPOJO.ForgotBean;
import com.googlemap.cardbanwaloo.LoginPOJO.LoginBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    EditText userid, password;

    Button login;

    TextView sign, forgot;

    ProgressBar bar;

    ConnectionDetector cd;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cd = new ConnectionDetector(Login.this);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();

        userid = findViewById(R.id.userid);

        password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        sign = findViewById(R.id.signup);

        bar = findViewById(R.id.progress);

        forgot = findViewById(R.id.forget);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(Login.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(true);


                final EditText em = (EditText) dialog.findViewById(R.id.edit);
                Button submit = (Button) dialog.findViewById(R.id.submit);
                dialog.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (cd.isConnectingToInternet()) {

                            String email = em.getText().toString();

                            if (Utils.isValidMail(email)) {

                                bar.setVisibility(View.VISIBLE);

                                Bean b = (Bean) getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://cardbanwalo.com")
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                Call<ForgotBean> call = cr.forget(b.id);
                                call.enqueue(new Callback<ForgotBean>() {
                                    @Override
                                    public void onResponse(Call<ForgotBean> call, Response<ForgotBean> response) {


                                        Toast.makeText(Login.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                        bar.setVisibility(View.GONE);

                                        dialog.dismiss();


                                    }

                                    @Override
                                    public void onFailure(Call<ForgotBean> call, Throwable t) {


                                        bar.setVisibility(View.GONE);
                                        dialog.dismiss();

                                    }
                                });

                            } else {
                                em.setError("Invalid Details");
                                Toast.makeText(Login.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cd.isConnectingToInternet()) {

                    final String u = userid.getText().toString();
                    final String p = password.getText().toString();

                    if (u.length() > 0) {

                        if (p.length() > 0) {

                            bar.setVisibility(View.VISIBLE);

                            Bean b = (Bean) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://cardbanwalo.com/")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);
                            Call<LoginBean> call = cr.login(u, p);

                            call.enqueue(new Callback<LoginBean>() {
                                @Override
                                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

                                    try {
                                        Bean b = (Bean) getApplicationContext();

                                        b.id = response.body().getResult().get(0).getUserid();
                                        b.AppMstid = response.body().getResult().get(0).getAppMstid();


                                        Log.d("login" , response.body().getResult().get(0).getUserid());
                                        Log.d("login" , response.body().getResult().get(0).getAppMstid());


                                        edit.putString("userId" , response.body().getResult().get(0).getUserid());
                                        edit.putString("mst" , response.body().getResult().get(0).getAppMstid());


                                        edit.putString("userid", u);
                                        edit.putString("password", p);
                                        edit.apply();


                                        Intent i = new Intent(Login.this, MainActivity.class);
                                        startActivity(i);
                                        finish();

                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }


                                    bar.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<LoginBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);
                                }
                            });


                        } else {

                            Toast.makeText(Login.this, "Please enter a Password", Toast.LENGTH_SHORT).show();
                            password.setError("Invalid Password");
                        }


                    } else {

                        Toast.makeText(Login.this, "Please enter a UserId", Toast.LENGTH_SHORT).show();
                        userid.setError("Invalid UserId");
                    }


                } else {
                    Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
