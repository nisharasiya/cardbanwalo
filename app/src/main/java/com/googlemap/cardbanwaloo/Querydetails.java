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

import com.googlemap.cardbanwaloo.BulkCardDetailsPOJO.BulkCardDetailsBean;
import com.googlemap.cardbanwaloo.QueryDetailsPOJO.QueryDeatailsBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Querydetails extends AppCompatActivity {

    TextView fromdate, todate;

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    Gridadapter2 adapter;

    RadioButton repliyed , notreplied , all;

    RadioGroup group;

    ConnectionDetector cd;

    ProgressBar bar;

    List<QueryDeatailsBean> list;

    String fd = "2018/9/2", td = "2018/9/20", status = "1";

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querydetails);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        cd = new ConnectionDetector(Querydetails.this);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Queries Details");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        repliyed = findViewById(R.id.replied);
        notreplied = findViewById(R.id.notreplied);
        all = findViewById(R.id.all);
        group = findViewById(R.id.group);

        grid = findViewById(R.id.grid);
        manager = new GridLayoutManager(getApplicationContext() , 1);

        list = new ArrayList<>();
        adapter = new Gridadapter2(this , list);
        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);

        todate = findViewById(R.id.todate);
        fromdate = findViewById(R.id.fromdate);
        bar = findViewById(R.id.progress);

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(Querydetails.this);
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


                final Dialog dialog = new Dialog(Querydetails.this);

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
                } else if (checkedId == R.id.replied) {

                    status = "1";
                    loadData();

                } else if (checkedId == R.id.notreplied) {

                    status = "0";
                    loadData();

                }





            }
        });


        loadData();



    }


    public void loadData(){


        if (cd.isConnectingToInternet()){

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<List<QueryDeatailsBean>> call = cr.qyery( pref.getString("userId" , ""), fd, td ,  status);


            call.enqueue(new Callback<List<QueryDeatailsBean>>() {
                @Override
                public void onResponse(Call<List<QueryDeatailsBean>> call, Response<List<QueryDeatailsBean>> response) {


                    adapter.setgrid(response.body());
                    Log.d("response", "response");
                    bar.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<List<QueryDeatailsBean>> call, Throwable t) {


                    Log.d("Failure123", t.toString());
                    bar.setVisibility(View.GONE);

                }
            });





        }
        else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public class Gridadapter2 extends RecyclerView.Adapter<Gridadapter2.MyViewholder>{



        Context context;

        List<QueryDeatailsBean>list = new ArrayList<>();


        public Gridadapter2(Context context , List<QueryDeatailsBean>list){

            this.context = context;

            this.list = list;
        }


        @NonNull
        @Override
        public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid_list_model2 , parent , false);
            return new MyViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

            QueryDeatailsBean item = list.get(position);
            holder.subject.setText(item.getSubject());
            holder.cdate.setText(item.getCdate());
            holder.rdate.setText(item.getRdate());
            holder.reply.setText(item.getReply());
            holder.complain.setText(item.getComplain());

        }

        public void setgrid(List<QueryDeatailsBean>list){

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();


        }

        public class MyViewholder extends RecyclerView.ViewHolder{


            TextView subject , complain , cdate , rdate , reply;

            public MyViewholder(View itemView) {
                super(itemView);



                subject = itemView.findViewById(R.id.subject);
                complain = itemView.findViewById(R.id.complain);
                cdate = itemView.findViewById(R.id.cdate);
                rdate = itemView.findViewById(R.id.rdate);
                reply = itemView.findViewById(R.id.reply);
            }
        }
    }
}
