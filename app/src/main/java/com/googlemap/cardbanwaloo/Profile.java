package com.googlemap.cardbanwaloo;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.CityPOJO.CityBean;
import com.googlemap.cardbanwaloo.MyProfilePOJO.ProfileBean;
import com.googlemap.cardbanwaloo.SchoolPOJO.SchoolBean;
import com.googlemap.cardbanwaloo.StatePOJO.StateBean;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static okhttp3.MultipartBody.*;

public class Profile extends AppCompatActivity {

    TextView mobile, address;

    Spinner schooltype, city, state;

    Button submit;

    List<String> school;
    List<String> schoolid;

    List<String> cit;
    List<String> citid;

    List<String> stat;
    List<String> statid;

    CircleImageView cirlce;

    String s, c, type;

    File file;

    Uri fileUri;

    ImageView edit;

    Toolbar toolbar;

    private final int PICK_IMAGE_REQUEST = 2;

    ConnectionDetector cd;

    ProgressBar bar;

    String mo, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        school = new ArrayList<>();
        schoolid = new ArrayList<>();

        cit = new ArrayList<>();
        citid = new ArrayList<>();

        stat = new ArrayList<>();
        statid = new ArrayList<>();

        cd = new ConnectionDetector(Profile.this);


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        mobile = findViewById(R.id.mobile);

        address = findViewById(R.id.address);

        schooltype = findViewById(R.id.schooltype);

        city = findViewById(R.id.city);

        state = findViewById(R.id.state);

        submit = findViewById(R.id.submit);

        cirlce = findViewById(R.id.circle);

        edit = findViewById(R.id.edit);
        bar = findViewById(R.id.progress);


        Bundle bundle = getIntent().getExtras();
        final String mo = bundle.getString("mob");
        final String add = bundle.getString("add");

        mobile.setText(mo);
        address.setText(add);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Profile.this, android.R.layout.simple_list_item_1, cit);

        city.setAdapter(adapter2);


      /*  ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Profile.this, android.R.layout.simple_list_item_1, stat);

        state.setAdapter(adapter3);
*/


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
            }
        });


        if (cd.isConnectingToInternet()) {
            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Bean b = (Bean) getApplicationContext();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<List<StateBean>> call = cr.sta("1");

            call.enqueue(new Callback<List<StateBean>>() {
                @Override
                public void onResponse(Call<List<StateBean>> call, Response<List<StateBean>> response) {

                    for (int i = 0; i < response.body().size(); i++) {
                        stat.add(response.body().get(i).getStateName());
                        statid.add(String.valueOf(response.body().get(i).getStateId()));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Profile.this, android.R.layout.simple_spinner_item, stat);

                    bar.setVisibility(View.GONE);

                    state.setAdapter(adapter);


                }

                @Override
                public void onFailure(Call<List<StateBean>> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


        } else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                s = statid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (cd.isConnectingToInternet()) {
            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Bean b = (Bean) getApplicationContext();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<List<SchoolBean>> call = cr.sch("3");

            call.enqueue(new Callback<List<SchoolBean>>() {
                @Override
                public void onResponse(Call<List<SchoolBean>> call, Response<List<SchoolBean>> response) {

                    for (int i = 0; i < response.body().size(); i++) {
                        school.add(response.body().get(i).getSCHTYPE());
                        schoolid.add(String.valueOf(response.body().get(i).getId()));
                    }

                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Profile.this, android.R.layout.simple_list_item_1, school);

                    bar.setVisibility(View.GONE);
                    schooltype.setAdapter(adapter1);


                }

                @Override
                public void onFailure(Call<List<SchoolBean>> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


        } else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        schooltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                type = schoolid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (cd.isConnectingToInternet()) {
            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cardbanwalo.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Bean b = (Bean) getApplicationContext();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<List<CityBean>> call = cr.cityyy("2");

            call.enqueue(new Callback<List<CityBean>>() {
                @Override
                public void onResponse(Call<List<CityBean>> call, Response<List<CityBean>> response) {

                    for (int i = 0; i < response.body().size(); i++) {
                        cit.add(response.body().get(i).getCITYDESC());
                        citid.add(String.valueOf(response.body().get(i).getId()));
                    }

                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Profile.this, android.R.layout.simple_list_item_1, cit);

                    bar.setVisibility(View.GONE);
                    city.setAdapter(adapter1);


                }

                @Override
                public void onFailure(Call<List<CityBean>> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


        } else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                c = citid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (file != null) {


                    MultipartBody.Part body = null;

                    final RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    body = MultipartBody.Part.createFormData("filename1", file.getName(), reqFile);

                    bar.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://cardbanwalo.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);
                    Call<ProfileBean> call = cr.myprofile(b.AppMstid, mo, s, c, add, body);
                    call.enqueue(new Callback<ProfileBean>() {
                        @Override
                        public void onResponse(Call<ProfileBean> call, Response<ProfileBean> response) {

                            Toast.makeText(Profile.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                            mobile.setText("");
                            address.setText("");
                        }

                        @Override
                        public void onFailure(Call<ProfileBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });


                } else {

                    Toast.makeText(Profile.this, "Please select file", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            fileUri = data.getData();

            Log.d("mukul", fileUri.toString());

            String mCurrentPhotoPath = getPath(Profile.this, fileUri);

            file = new File(mCurrentPhotoPath);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            cirlce.setImageBitmap(bitmap);


        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    private static String getPath(final Context context, final Uri uri) {
        final boolean isKitKatOrAbove = true;

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}
