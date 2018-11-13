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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Card extends AppCompatActivity {

    Toolbar toolbar;

    EditText nocards, reamrks, name, des;

    TextView file1, file2;

    Button upload1, upload2;

    Button submit;

    int PICK_IMAGE_REQUEST = 1;

    int PICK_IMAGE_REQUEST1 = 2;


    String[] mimeTypes =
            {"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" // .xls & .xlsx
            };


    int REQUEST_CODE = 1;
    int REQUEST_CODE1 = 10;

    //   static final int OPEN_MEDIA_PICKER = 1;
    //  static final int OPEN_MEDIA_PICKER1 = 0;

    File file21, file22;

    Uri fileUri1, fileUri2;

    ProgressBar bar;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Bulk Card");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        name = findViewById(R.id.name);
        des = findViewById(R.id.des);

        submit = findViewById(R.id.submit);
        reamrks = findViewById(R.id.remarks);
        file1 = findViewById(R.id.file1);

        file2 = findViewById(R.id.file2);

        nocards = findViewById(R.id.nocard);

        upload2 = findViewById(R.id.upload2);

        upload1 = findViewById(R.id.upload1);
        bar = findViewById(R.id.progress);

        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*

               Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
*/


                Intent intent = new Intent(Card.this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 4); // set limit for image selection
                startActivityForResult(intent, REQUEST_CODE);

/*
                Intent intent = new Intent(Card.this , Gallery.class);                intent.putExtra("title","Select media");
                // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
                intent.putExtra("mode",1);
                intent.putExtra("maxSelection",3); // Optional
                startActivityForResult(intent,OPEN_MEDIA_PICKER);*/

            }
        });


        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // File file = new File(Environment.getExternalStorageDirectory()+ "/filepath/" + filename);


                File root = Environment.getExternalStorageDirectory();
                file21 = new File(root, "filename.xlsx");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // intent.setType("filename.xlsx");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST1);



/*
                Intent intent = new Intent(Card.this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 4); // set limit for image selection
                startActivityForResult(intent, REQUEST_CODE1);
*/




/*

                Intent intent = new Intent(Card.this , Gallery.class);
                intent.putExtra("title","Select media");
                // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
                intent.putExtra("mode",1);
                intent.putExtra("maxSelection",3); // Optional
                startActivityForResult(intent,OPEN_MEDIA_PICKER1);
*/


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = name.getText().toString();
                String n = nocards.getText().toString();
                String r = reamrks.getText().toString();
                String d = des.getText().toString();

                if (a.length() > 0) {

                    if (n.length() > 0) {


                        if (r.length() > 0) {

                            if (d.length() > 0) {

                                MultipartBody.Part body1 = null;

                                RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file21);

                                body1 = MultipartBody.Part.createFormData("", file21.getName(), reqFile1);

                                List<MultipartBody.Part> bodies = new ArrayList<>();

                                for (int i = 0; i < images.size(); i++) {
                                    Uri uri = Uri.fromFile(new File(images.get(i).path));

                                    Log.d("mukul1", uri.toString());

                                    String mCurrentPhotoPath = getPath(Card.this, uri);

                                    file1.setText(file1.getText() + ", " + mCurrentPhotoPath);

                                    file22 = new File(mCurrentPhotoPath);

                                    MultipartBody.Part body2 = null;

                                    RequestBody reqFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file22);

                                    body2 = MultipartBody.Part.createFormData("", file22.getName(), reqFile2);

                                    bodies.add(body2);


                                }


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


                                retrofit2.Call<CardBean> call = cr.c(pref.getString("mst", ""), a, "", n, d, r,  "abc.xls" ,  body1, bodies);

                                call.enqueue(new Callback<CardBean>() {
                                    @Override
                                    public void onResponse(retrofit2.Call<CardBean> call, Response<CardBean> response) {

                                               /* for (int i = 0; i <response.body(). ; i++) {

                                                }
                                                */

                                        Toast.makeText(Card.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                        bar.setVisibility(View.GONE);

                                        name.setText("");
                                        nocards.setText("");
                                        reamrks.setText("");
                                        des.setText("");
                                        file1.setText("");
                                        file2.setText("");
                                        file21 = null;
                                        file22 = null;

                                    }

                                    @Override
                                    public void onFailure(retrofit2.Call<CardBean> call, Throwable t) {

                                        bar.setVisibility(View.GONE);

                                    }
                                });


                            } else {

                                Toast.makeText(Card.this, "Please enter a Description", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(Card.this, "Please enter remarks", Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        Toast.makeText(Card.this, "Please enter a No.of Cards", Toast.LENGTH_SHORT).show();
                    }
                } else {


                    Toast.makeText(Card.this, "Please enetr a Name", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode == REQUEST_CODE && resultCode == RESULT_OK

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            images.clear();


            images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            for (int i = 0; i < images.size(); i++) {

                Uri uri = Uri.fromFile(new File(images.get(i).path));


                Log.d("mukul1", uri.toString());

                String mCurrentPhotoPath = getPath(Card.this, uri);

                file1.setText(file1.getText() + ", " + mCurrentPhotoPath);

            }
            //ArrayList<String> selectionResult=data.getStringArrayListExtra("result");


        } else if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK) {

            // ArrayList<String> selectionResult=data.getStringArrayListExtra("result");

            fileUri1 = data.getData();

            Log.d("mukul2", fileUri1.toString());

            String mCurrentPhotoPath = getPath(Card.this, fileUri1);

            file2.setText(mCurrentPhotoPath);

            file21 = new File(mCurrentPhotoPath);

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


/*
 } else {
         Toast.makeText(Card.this, "Please select an Excel", Toast.LENGTH_SHORT).show();
         }

         } else {

         Toast.makeText(Card.this, "Please select picture", Toast.LENGTH_SHORT).show();
         }*/
