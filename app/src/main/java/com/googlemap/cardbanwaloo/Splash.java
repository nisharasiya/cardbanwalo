package com.googlemap.cardbanwaloo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.LoginPOJO.LoginBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {

    Timer time;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    ConnectionDetector cd;

    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CAMERA};

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        cd = new ConnectionDetector(this);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(Splash.this));

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();

        bar = (ProgressBar) findViewById(R.id.progress);

        if (hasPermissions(this, PERMISSIONS)) {
            startApp();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
        }


    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                startApp();

            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    Toast.makeText(getApplicationContext(), "Permissions are required for this app", Toast.LENGTH_SHORT).show();
                    finish();

                }
                //permission is denied (and never ask again is  checked)
                //shouldShowRequestPermissionRationale will return false
                else {
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                    finish();
                    //                            //proceed with logic by disabling the related features or quit the app.
                }
            }

        }


    }


    public void startApp() {

        if (cd.isConnectingToInternet()) {

            String u = pref.getString("userid", "");
            String p = pref.getString("password", "");


            if (u.length() > 0 && p.length() > 0) {

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

                            if (response.body().getResult().size() > 0) {


                                Bean b = (Bean) getApplicationContext();


                                b.id = response.body().getResult().get(0).getUserid();
                                b.AppMstid = response.body().getResult().get(0).getAppMstid();


                                edit.putString("userId", response.body().getResult().get(0).getUserid());
                                edit.putString("mst", response.body().getResult().get(0).getAppMstid());

                                edit.apply();


                                Log.d("splash", response.body().getResult().get(0).getUserid());
                                Log.d("splash", response.body().getResult().get(0).getAppMstid());


                                Intent i = new Intent(Splash.this, MainActivity.class);

                                startActivity(i);
                                finish();


                            } else {
                                Toast.makeText(Splash.this, "Login detail invalid", Toast.LENGTH_SHORT).show();
                                //bar.setVisibility(View.GONE);
                            }

                            bar.setVisibility(View.GONE);

                        } catch (Exception e) {

                            e.printStackTrace();

                        }


                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                        Log.d("failure", "failed");

                    }
                });

            } else {

                time = new Timer();
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        Intent i = new Intent(Splash.this, Login.class);
                        startActivity(i);
                        finish();

                    }
                }, 1500);

            }


        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


}

