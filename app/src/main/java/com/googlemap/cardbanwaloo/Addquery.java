package com.googlemap.cardbanwaloo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.AddqueryPOJO.AddqueryBean;
import com.googlemap.cardbanwaloo.QueryDetailsPOJO.QueryDeatailsBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Addquery extends AppCompatActivity {

    Toolbar toolbar;

    EditText sub, msg;

    Button submit;

    ConnectionDetector cd;

    SharedPreferences pref;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquery);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        cd = new ConnectionDetector(Addquery.this);

        submit = findViewById(R.id.submit);

        sub = findViewById(R.id.subject);

        msg = findViewById(R.id.message);

        bar = findViewById(R.id.progress);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Add Queries");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cd.isConnectingToInternet()) {

                    String s = sub.getText().toString();
                    String ms = msg.getText().toString();

                    if (s.length() > 0) {

                        if (ms.length() > 0) {

                            bar.setVisibility(View.VISIBLE);

                            Bean b = (Bean) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://cardbanwalo.com/")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);

                            //Call<AddqueryBean> call = cr.addqueryy(pref.getString("userId", ""),s , m );
                            Call<AddqueryBean> call = cr.addqueryy(pref.getString("userId", ""), s, ms);

                            call.enqueue(new Callback<AddqueryBean>() {
                                @Override
                                public void onResponse(Call<AddqueryBean> call, Response<AddqueryBean> response) {

                                    Toast.makeText(Addquery.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                    bar.setVisibility(View.GONE);

                                    sub.setText("");

                                    msg.setText("");

                                }

                                @Override
                                public void onFailure(Call<AddqueryBean> call, Throwable t) {

                                    Log.d("Failure123", t.toString());
                                    bar.setVisibility(View.GONE);

                                }
                            });

                        } else {

                            Toast.makeText(Addquery.this, "Please enter a Massage", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(Addquery.this, "Please enter a Subject", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(Addquery.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
