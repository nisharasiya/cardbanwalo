package com.googlemap.cardbanwaloo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.BusinessReportPOJO.BusinessReportBean;
import com.googlemap.cardbanwaloo.GalleryPOJO.GalleryBean;
import com.googlemap.cardbanwaloo.GalleryidPOJO.GalleryidBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Gallery extends AppCompatActivity {


    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    GalleryAdapter adapter;

    List<GalleryBean> list;

    ConnectionDetector cd;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        cd = new ConnectionDetector(Gallery.this);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Gallery");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        bar = findViewById(R.id.progress);

        grid = findViewById(R.id.grid);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();

        adapter = new GalleryAdapter(this, list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<List<GalleryBean>> call = cr.galler();

            call.enqueue(new Callback<List<GalleryBean>>() {
                @Override
                public void onResponse(Call<List<GalleryBean>> call, Response<List<GalleryBean>> response) {

                    adapter.setgrid(response.body());

                    Log.d("gallery", "gallery");

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<List<GalleryBean>> call, Throwable t) {


                    Log.d("Failure", t.toString());
                    bar.setVisibility(View.GONE);


                }
            });


        } else {

            Toast.makeText(Gallery.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }


    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

        Context context;
        List<GalleryBean> list = new ArrayList<>();


        public GalleryAdapter(Context context, List<GalleryBean> list) {

            this.context = context;
            this.list = list;
        }


        @NonNull
        @Override
        public GalleryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View vi = LayoutInflater.from(context).inflate(R.layout.gallery_list_model, parent, false);
            return new MyViewHolder(vi);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryAdapter.MyViewHolder holder, int position) {


            final GalleryBean item = list.get(position);

            holder.imageheader.setText(item.getImageHeader());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context , GalleryImages.class);
                    intent.putExtra("id" , String.valueOf(item.getId()));
                    context.startActivity(intent);

                }
            });

        }


        public void setgrid(List<GalleryBean> list) {

            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView imageheader;

            public MyViewHolder(View itemView) {
                super(itemView);

                imageheader = itemView.findViewById(R.id.imageheader);


            }
        }


    }


}
