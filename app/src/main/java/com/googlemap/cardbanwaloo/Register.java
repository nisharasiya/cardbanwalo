package com.googlemap.cardbanwaloo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.LoginPOJO.LoginBean;
import com.googlemap.cardbanwaloo.RegisterPOJO.RegisterBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register extends AppCompatActivity {

    EditText name, mobile, email, address, school;

    Button submit;

    TextView login;

    ProgressBar bar;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cd = new ConnectionDetector(Register.this);

        name = findViewById(R.id.name);

        email = findViewById(R.id.email);

        mobile = findViewById(R.id.mobile);

        school = findViewById(R.id.school);

        address = findViewById(R.id.address);

        login = findViewById(R.id.login);

        bar = findViewById(R.id.progress);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);

            }
        });

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cd.isConnectingToInternet()){

                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String m = mobile.getText().toString();
                    String s = school.getText().toString();
                    String a = address.getText().toString();


                    if (n.length() > 0) {

                        if (Utils.isValidMail(e)) {

                            if (Utils.isValidMobile(m)) {

                                if (s.length() > 0) {

                                    if (a.length() > 0) {


                                        bar.setVisibility(View.VISIBLE);

                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl("http://cardbanwalo.com/")
                                                .addConverterFactory(ScalarsConverterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                        Call<RegisterBean> call = cr.register(n, e, m, s, a);

                                        call.enqueue(new Callback<RegisterBean>() {
                                            @Override
                                            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {


                                                Log.d("response" , response.body().getStatus());

                                                if (Objects.equals(response.body().getErrorMessage() , "Registration Successfully.")){

                                                    Toast.makeText(Register.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(Register.this, Login.class);
                                                    startActivity(i);



                                                }
                                                else {

                                                    Toast.makeText(Register.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                                    bar.setVisibility(View.GONE);
                                                }


                                            }

                                            @Override
                                            public void onFailure(Call<RegisterBean> call, Throwable t) {


                                                Log.d("failure" , t.toString());
                                                bar.setVisibility(View.GONE);

                                            }
                                        });


                                    } else {
                                        Toast.makeText(Register.this, "Please enter a Address", Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    Toast.makeText(Register.this, "Please enter a School/College", Toast.LENGTH_SHORT).show();
                                }

                            } else {

                                Toast.makeText(Register.this, "Please enter a Mobile no.", Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            Toast.makeText(Register.this, "Please enter a Emailid", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(Register.this, "Please enter a Name", Toast.LENGTH_SHORT).show();
                    }



                }
                else {
                    Toast.makeText(Register.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}
