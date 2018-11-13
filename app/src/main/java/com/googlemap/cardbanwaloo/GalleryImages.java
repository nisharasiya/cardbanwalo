package com.googlemap.cardbanwaloo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.GalleryidPOJO.GalleryidBean;
import com.jsibbold.zoomage.ZoomageView;
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

public class GalleryImages extends AppCompatActivity {


    Toolbar toolbar;

    ViewPager pager;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_images);

        id = getIntent().getStringExtra("id");

        toolbar = findViewById(R.id.toolbar);
        pager = findViewById(R.id.pager);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle("Gallery Images");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cardbanwalo.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Log.d("iidd", String.valueOf(id));

        Call<List<GalleryidBean>> call = cr.galleryid(id);
        call.enqueue(new Callback<List<GalleryidBean>>() {
            @Override
            public void onResponse(@NonNull Call<List<GalleryidBean>> call, @NonNull Response<List<GalleryidBean>> response) {

                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        List<String> ll = new ArrayList<>();

                        for (int i = 0; i < response.body().size(); i++) {
                            ll.add(response.body().get(i).getImageurl());
                        }

                        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), ll);

                        pager.setAdapter(adapter);

                    } else {
                        Toast.makeText(GalleryImages.this, "No Image Found", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<List<GalleryidBean>> call, @NonNull Throwable t) {

            }
        });

    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        List<String> images = new ArrayList<>();

        PagerAdapter(FragmentManager fm, List<String> images) {
            super(fm);
            this.images = images;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle b = new Bundle();
            b.putString("url", images.get(position));
            page frag = new page();
            frag.setArguments(b);
            return frag;
        }

        @Override
        public int getCount() {
            return images.size();
        }
    }


    public static class page extends Fragment {


        ZoomageView image;
        ProgressBar progress;
        String url;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.page_layout, container, false);

            url = getArguments().getString("url");

            image = view.findViewById(R.id.image);
            progress = view.findViewById(R.id.progress);

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            Log.d("iimmaaggee", "http://cardbanwalo.com/" + url);

            loader.displayImage("http://cardbanwalo.com/" + url, image, options);

            return view;
        }
    }


}
