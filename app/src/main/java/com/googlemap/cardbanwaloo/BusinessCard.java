package com.googlemap.cardbanwaloo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.BulkPOJO.CardBean;
import com.googlemap.cardbanwaloo.BusinessCardPOJO.BusinessCardBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BusinessCard extends AppCompatActivity {


    Toolbar toolbar;

    EditText remark, des;

    TextView file1, file2;

    Button upload1, upload2;

    Button submit;

    int PICK_IMAGE_REQUEST = 1;

    int PICK_IMAGE_REQUEST1 = 2;

    File file21, file22;

    Uri fileUri1, fileUri2;

    ProgressBar bar;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Business Card");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        des = findViewById(R.id.des);

        submit = findViewById(R.id.submit);

        remark = findViewById(R.id.remark);

        file1 = findViewById(R.id.file1);

        file2 = findViewById(R.id.file2);


        upload2 = findViewById(R.id.upload2);

        upload1 = findViewById(R.id.upload1);
        bar = findViewById(R.id.progress);

        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);


            }
        });


        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST1);


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d = des.getText().toString();
                String r = remark.getText().toString();

                if (d.length() > 0) {

                    MultipartBody.Part body1 = null;

                    RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file21);

                    long imagename = System.currentTimeMillis();

                    String strName = imagename + file21.getName();

                    Log.d("asdasdasd", strName);

                    body1 = MultipartBody.Part.createFormData("photo", file21.getName(), reqFile1);

                    MultipartBody.Part body2 = null;

                    RequestBody reqFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file22);

                    long imagenames = System.currentTimeMillis();

                    String strNames = imagenames + file22.getName();

                    Log.d("asdasdasd", strNames);

                    body2 = MultipartBody.Part.createFormData("sign", file22.getName(), reqFile2);

                    bar.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://cardbanwalo.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Log.d("mukul3", String.valueOf(file21.getAbsolutePath()));
                    // Log.d("file" , body1);


                    retrofit2.Call<BusinessCardBean> call = cr.bu(pref.getString("mst", ""), d, r, strName, strNames, body1, body2);

                    call.enqueue(new Callback<BusinessCardBean>() {
                        @Override
                        public void onResponse(retrofit2.Call<BusinessCardBean> call, Response<BusinessCardBean> response) {

                            Toast.makeText(BusinessCard.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                            bar.setVisibility(View.GONE);

                            des.setText("");
                            remark.setText("");
                            file1.setText("");
                            file2.setText("");
                            file21 = null;
                            file22 = null;

                        }

                        @Override
                        public void onFailure(retrofit2.Call<BusinessCardBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });


                } else {

                    Toast.makeText(BusinessCard.this, "Please enter a Description", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    //ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode == REQUEST_CODE && resultCode == RESULT_OK

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {


            fileUri1 = data.getData();

            Log.d("mukul1", fileUri1.toString());

            String mCurrentPhotoPath = getPath(BusinessCard.this, fileUri1);

            file1.setText(mCurrentPhotoPath);

            file21 = new File(mCurrentPhotoPath);


        } else if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK) {

            // ArrayList<String> selectionResult=data.getStringArrayListExtra("result");

            fileUri2 = data.getData();

            Log.d("mukul2", fileUri2.toString());

            String mCurrentPhotoPath = getPath(BusinessCard.this, fileUri2);

            file2.setText(mCurrentPhotoPath);

            file22 = new File(mCurrentPhotoPath);

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

