package com.googlemap.cardbanwaloo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.ChangePAsswordPOJO.ChangeBean;
import com.googlemap.cardbanwaloo.RegisterPOJO.RegisterBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChangePassword extends AppCompatActivity {


    EditText cp , confirm , np;

    Button submit;

    Toolbar toolbar;

    ConnectionDetector cd;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        cd = new ConnectionDetector(ChangePassword.this);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Change Password");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        cp = findViewById(R.id.cp);

        confirm = findViewById(R.id.confirm);

        np = findViewById(R.id.np);

        submit = findViewById(R.id.submit);

        bar = findViewById(R.id.progress);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cd.isConnectingToInternet()){


                    String currentpass = cp.getText().toString();
                    String newpass = np.getText().toString();
                    String c = confirm.getText().toString();

                    bar.setVisibility(View.VISIBLE);

                    Bean b = (Bean)getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://cardbanwalo.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<ChangeBean> call = cr.chnagepass(b.id , currentpass , newpass);
                    call.enqueue(new Callback<ChangeBean>() {
                        @Override
                        public void onResponse(Call<ChangeBean> call, Response<ChangeBean> response) {


                            Toast.makeText(ChangePassword.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<ChangeBean> call, Throwable t) {


                            bar.setVisibility(View.GONE);
                        }
                    });








                }
                else {

                    Toast.makeText(ChangePassword.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }




                finish();

            }
        });
    }
}
