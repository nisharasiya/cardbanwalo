package com.googlemap.cardbanwaloo;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.BusinessReportPOJO.BusinessReportBean;
import com.googlemap.cardbanwaloo.SingleCarddetailsPOJO.SingleCardDetailsBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BusinessDetails extends AppCompatActivity {


    TextView fromdate, todate;

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    Gridadapter1 adapter;

    String fd = "2018/9/2", td = "2018/9/20", status = "0";

    ConnectionDetector cd;

    RadioButton all, active, inactive;

    RadioGroup group;

    List<BusinessReportBean> list;

    ProgressBar bar;

    Button submit;

    SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        cd = new ConnectionDetector(BusinessDetails.this);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Business Details");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        all = findViewById(R.id.all);
        active = findViewById(R.id.active);
        inactive = findViewById(R.id.inactive);

        group = findViewById(R.id.group);

        grid = findViewById(R.id.grid);
        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();

        adapter = new Gridadapter1(this, list);
        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);

        todate = findViewById(R.id.todate);
        fromdate = findViewById(R.id.fromdate);
        bar = findViewById(R.id.progress);
        submit = findViewById(R.id.submit);

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(BusinessDetails.this);
                dialog.setContentView(R.layout.dialogg);
                dialog.setCancelable(false);
                dialog.show();

                final DatePicker picker = (DatePicker) dialog.findViewById(R.id.picker);
                Button ok = (Button) dialog.findViewById(R.id.ok);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());



                        String f = year + "/" + month + "/" + day;

                        Log.d("fromdate" , f);

                        //fromdate.setText(year + "-" + month + "-" + day);
                        fromdate.setText(f);

                        fd = f;

                        dialog.dismiss();


                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                    }
                });


            }
        });

        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(BusinessDetails.this);

                dialog.setContentView(R.layout.dialogg);

                dialog.setCancelable(false);

                dialog.show();

                final DatePicker picker = (DatePicker) dialog.findViewById(R.id.picker);

                Button ok = (Button) dialog.findViewById(R.id.ok);

                Button cancel = (Button) dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String day = String.valueOf(picker.getDayOfMonth());

                        String month = String.valueOf(picker.getMonth() + 1);

                        String year = String.valueOf(picker.getYear());

                        String f = year + "/" + month + "/" + day;


                        Log.d("todate" , f);
                        //fromdate.setText(year + "-" + month + "-" + day);
                        todate.setText(f);

                        td = f;

                        dialog.dismiss();




                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                    }
                });


            }
        });



        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.all) {

                    status = "";

                    loadData();
                } else if (checkedId == R.id.active) {

                    status = "0";
                    loadData();

                } else if (checkedId == R.id.inactive) {


                    status = "1";
                    loadData();




                }

            }
        });


        loadData();


    }


    public void loadData() {
        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            //Call<List<SingleCardDetailsBean>> call = cr.singlecarddetails("", pref.getString("mst" , ""), "2018/09/02", "2018/09/20", status);
            Call<List<BusinessReportBean>> call = cr.businessreport("", pref.getString("mst" , ""), fd, td, status);




            call.enqueue(new Callback<List<BusinessReportBean>>() {
                @Override
                public void onResponse(Call<List<BusinessReportBean>> call, Response<List<BusinessReportBean>> response) {

                    Log.d("response" , String.valueOf(response.body().size()));

                    adapter.setgrid(response.body());

                    Log.d("response", "response");

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<List<BusinessReportBean>> call, Throwable t) {


                    Log.d("Failure", t.toString());
                    bar.setVisibility(View.GONE);


                }
            });


        } else {

            Toast.makeText(BusinessDetails.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    public class Gridadapter1 extends RecyclerView.Adapter<Gridadapter1.MyViewholder> {


        Context context;

        List<BusinessReportBean> list = new ArrayList<>();

        public Gridadapter1(Context context, List<BusinessReportBean>list) {

            this.context = context;
            this.list = list;
        }


        @NonNull
        @Override
        public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.bus_list_model, parent, false);
            return new MyViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

            BusinessReportBean item = list.get(position);


            holder.detail.setText(item.getDetail());
            holder.remark.setText(item.getRemarks());
            holder.status.setText(item.getStatus1());
            //holder.status.setText(String.valueOf(item.getStatus()));




        }

        public void setgrid(List<BusinessReportBean> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewholder extends RecyclerView.ViewHolder {


            TextView remark , detail , status;


            public MyViewholder(View itemView) {
                super(itemView);

                remark = itemView.findViewById(R.id.remark);
                detail = itemView.findViewById(R.id.details);
                status = itemView.findViewById(R.id.status);


            }
        }
    }

}
