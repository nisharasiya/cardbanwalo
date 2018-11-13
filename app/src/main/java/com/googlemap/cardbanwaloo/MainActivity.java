package com.googlemap.cardbanwaloo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.googlemap.cardbanwaloo.HomePOJO.HomeBean;
import com.googlemap.cardbanwaloo.RegisterPOJO.RegisterBean;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    DrawerLayout drawerLayout;

    TextView profile, card, single, sinngledetails, business , businessdetails ,  logout, changepassword, query, gallery , companydetails, name, password, mobile, userid, email, carddetails, addquery, querydetails;

    ImageView up, down, up1, down1, up2, down2, up3, down3;

    AutoScrollViewPager pager;

    CircleIndicator indicator;

    ViewAdapter adapter;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    private List<Integer> imageIdList;

    String mo , add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        edit = pref.edit();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle("Cardbanwalo");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        profile = findViewById(R.id.profile);

        logout = findViewById(R.id.logout);

        companydetails = findViewById(R.id.company);

        gallery = findViewById(R.id.gallery);

        query = findViewById(R.id.queris);

        card = findViewById(R.id.card);

        single = findViewById(R.id.single);

        sinngledetails = findViewById(R.id.singledatails);

        business = findViewById(R.id.business);

        businessdetails = findViewById(R.id.businessdetails);

        carddetails = findViewById(R.id.carddetails);

        addquery = findViewById(R.id.addquery);

        querydetails = findViewById(R.id.querydetails);

        name = findViewById(R.id.name);

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);

        mobile = findViewById(R.id.mobile);

        userid = findViewById(R.id.id);

        pager = (AutoScrollViewPager) findViewById(R.id.pager);

        pager.setOnPageChangeListener(new MyOnPageChangeListener());

        pager.setInterval(2000);
        pager.startAutoScroll();
        //pager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));


        indicator = findViewById(R.id.indicator);

        adapter = new ViewAdapter(getSupportFragmentManager(), 5);

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);






        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cardbanwalo.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Log.d("app", b.AppMstid);
        Call<List<HomeBean>> call = cr.home(pref.getString("mst", ""));
        call.enqueue(new Callback<List<HomeBean>>() {
            @Override
            public void onResponse(Call<List<HomeBean>> call, Response<List<HomeBean>> response) {

                //Bean b = (Bean)getApplicationContext();
                // b.id = response.body().get(0).getUserid();

                Log.d("asdasd", "home data");

                name.setText(response.body().get(0).getName());
                email.setText(response.body().get(0).getEmail());
                mobile.setText(response.body().get(0).getMobile());
                password.setText(response.body().get(0).getPassword());
                userid.setText(response.body().get(0).getUserid());



                mo = response.body().get(0).getMobile();
                add = response.body().get(0).getAddress();





            }

            @Override
            public void onFailure(Call<List<HomeBean>> call, Throwable t) {

                Log.d("abc", t.toString());

            }
        });


        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SingleCard.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        companydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CompanyAccount.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Gallery.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });







        querydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Querydetails.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });


        addquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Addquery.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        carddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CardDetails.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        sinngledetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SingleDetails.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, BusinessCard.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        businessdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, BusinessDetails.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });


        changepassword = findViewById(R.id.change);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ChangePassword.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });


        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        up1 = findViewById(R.id.up1);
        down1 = findViewById(R.id.down1);

        down2 = findViewById(R.id.down2);
        up2 = findViewById(R.id.up2);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down.setVisibility(View.VISIBLE);
                up.setVisibility(View.GONE);
                profile.setVisibility(View.GONE);
                changepassword.setVisibility(View.GONE);


            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down.setVisibility(View.GONE);
                up.setVisibility(View.VISIBLE);
                profile.setVisibility(View.VISIBLE);
                changepassword.setVisibility(View.VISIBLE);


            }
        });

        up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down1.setVisibility(View.VISIBLE);
                up1.setVisibility(View.GONE);
                card.setVisibility(View.GONE);
                carddetails.setVisibility(View.GONE);
                single.setVisibility(View.GONE);
                sinngledetails.setVisibility(View.GONE);
                business.setVisibility(View.GONE);
                businessdetails.setVisibility(View.GONE);


            }
        });


        down1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down1.setVisibility(View.GONE);
                up1.setVisibility(View.VISIBLE);
                card.setVisibility(View.VISIBLE);
                carddetails.setVisibility(View.VISIBLE);
                single.setVisibility(View.VISIBLE);
                sinngledetails.setVisibility(View.VISIBLE);
                business.setVisibility(View.VISIBLE);
                businessdetails.setVisibility(View.VISIBLE);


            }
        });


        up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down2.setVisibility(View.VISIBLE);
                up2.setVisibility(View.GONE);
                addquery.setVisibility(View.GONE);
                querydetails.setVisibility(View.GONE);


            }
        });


        down2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down2.setVisibility(View.GONE);
                up2.setVisibility(View.VISIBLE);
                addquery.setVisibility(View.VISIBLE);
                querydetails.setVisibility(View.VISIBLE);


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                edit.clear();
                edit.apply();


                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();


            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, Profile.class);
                Bundle bundle = new Bundle();
                bundle.putString("mob", mo);
                bundle.putString("add", add);
                i.putExtras(bundle);

                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);


            }
        });


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Card.class);
                startActivity(i);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }


    public class ViewAdapter extends FragmentStatePagerAdapter {


        public ViewAdapter(FragmentManager fm, int tab) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            if (position == 0) {
                return new Page1();

            } else { if (position == 1) {
                    return new Page2();

                } else { if (position == 2) {
                        return new Page3();

                    } else if (position == 3) {
                        return new Page4();

                    } else if (position == 4) {
                        return new Page5();
                    }
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
           /* indexText.setText(new StringBuilder().append((position) % ListUtils.getSize(imageIdList) + 1).append("/")
                    .append(ListUtils.getSize(imageIdList)));*/
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        pager.stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        pager.startAutoScroll();
    }
}
