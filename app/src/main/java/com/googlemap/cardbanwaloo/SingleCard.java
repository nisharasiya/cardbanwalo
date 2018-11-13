package com.googlemap.cardbanwaloo;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.googlemap.cardbanwaloo.LoginPOJO.LoginBean;
import com.googlemap.cardbanwaloo.SingleCardPOJO.SingleCardBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SingleCard extends AppCompatActivity {

    Toolbar toolbar;

    EditText name, fathername, mothername, classs, mob, address, admissionno;

    TextView dob;

    Button buttonLoadImage, upload;

    ProgressBar bar;

    ImageView imageView;

    String mCurrentPhotoPath = "";

    static final int RC_TAKE_PHOTO = 1;

    private final int PICK_IMAGE_REQUEST = 2;

    File file;

    Uri fileUri;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_card);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Student Card");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        name = findViewById(R.id.name);

        fathername = findViewById(R.id.fathername);

        mothername = findViewById(R.id.mothername);

        classs = findViewById(R.id.classs);

        mob = findViewById(R.id.mobile);

        address = findViewById(R.id.address);

        imageView = findViewById(R.id.image);

        dob = findViewById(R.id.dob);

        admissionno = findViewById(R.id.admision);

        bar = findViewById(R.id.progress);

        upload = (Button) findViewById(R.id.upload);

        buttonLoadImage = (Button) findViewById(R.id.choose);

        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                final Dialog dialog = new Dialog(SingleCard.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialoggallery);
                dialog.setCancelable(true);
                dialog.show();

                TextView profile = (TextView) dialog.findViewById(R.id.profile);

                TextView cap = (TextView) dialog.findViewById(R.id.capture);

                profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
                        dialog.dismiss();


                    }
                });

                cap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        file = new File(getExternalCacheDir(),
                                String.valueOf(System.currentTimeMillis()) + ".jpg");
                        //fileUri = Uri.fromFile(file);
                        fileUri = FileProvider.getUriForFile(
                                SingleCard.this,
                                SingleCard.this.getApplicationContext()
                                        .getPackageName() + ".fileprovider", file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        startActivityForResult(intent, RC_TAKE_PHOTO);


                        dialog.dismiss();

                    }
                });


                // Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // startActivityForResult(i, 100);



            }

        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString();
                String f = fathername.getText().toString();
                final String moth = mothername.getText().toString();
                String c = classs.getText().toString();
                String m = mob.getText().toString();
                String d = dob.getText().toString();
                String add = address.getText().toString();
                String adm = admissionno.getText().toString();


                if (n.length() > 0) {

                    if (f.length() > 0) {

                        if (moth.length() > 0) {

                            if (c.length() > 0) {

                                if (Utils.isValidMobile(m)) {

                                    if (d.length() > 0) {

                                        if (add.length() > 0) {

                                            if (adm.length() > 0) {

                                                if (file != null)
                                                {
                                                    MultipartBody.Part body = null;

                                                    RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                                                    long imagename = System.currentTimeMillis();

                                                    String strName = imagename + ".jpg";

                                                    Log.d("asdasdasd" , strName);

                                                    body = MultipartBody.Part.createFormData("filename1", file.getName(), reqFile);

                                                    bar.setVisibility(View.VISIBLE);

                                                    Bean b = (Bean) getApplicationContext();

                                                    Retrofit retrofit = new Retrofit.Builder()
                                                            .baseUrl("http://cardbanwalo.com/")
                                                            .addConverterFactory(ScalarsConverterFactory.create())
                                                            .addConverterFactory(GsonConverterFactory.create())
                                                            .build();


                                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                                    Call<SingleCardBean> call = cr.singlecard(pref.getString("mst" , ""), m, n, adm, c, add, f, moth,strName ,  body, d);

                                                    call.enqueue(new Callback<SingleCardBean>() {
                                                        @Override
                                                        public void onResponse(Call<SingleCardBean> call, Response<SingleCardBean> response) {

                                                            Log.d("asdasd" , "response");

                                                            bar.setVisibility(View.GONE);

                                                            imageView.setImageResource(0);
                                                            name.setText("");
                                                            fathername.setText("");
                                                            mothername.setText("");
                                                            classs.setText("");
                                                            mob.setText("");
                                                            dob.setText("");
                                                            address.setText("");
                                                            admissionno.setText("");



                                                            ////Toast.makeText(SingleCard.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();



                                                        }

                                                        @Override
                                                        public void onFailure(Call<SingleCardBean> call, Throwable t) {

                                                            Log.d("asdasd" , t.toString());
                                                            bar.setVisibility(View.GONE);

                                                        }
                                                    });


                                                }
                                                else
                                                {
                                                    Toast.makeText(SingleCard.this, "Please select a file", Toast.LENGTH_SHORT).show();
                                                }


                                            } else {

                                                Toast.makeText(SingleCard.this, "Please enter a Admission no.", Toast.LENGTH_SHORT).show();

                                            }

                                        } else {

                                            Toast.makeText(SingleCard.this, "Please enter a Address", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {

                                        Toast.makeText(SingleCard.this, "Please enter a DOB", Toast.LENGTH_SHORT).show();

                                    }


                                } else {

                                    Toast.makeText(SingleCard.this, "Please enter a Mobileno.", Toast.LENGTH_SHORT).show();

                                }

                            } else {

                                Toast.makeText(SingleCard.this, "Please enter a Class", Toast.LENGTH_SHORT).show();

                            }


                        } else {

                            Toast.makeText(SingleCard.this, "Please enter a Mothername", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(SingleCard.this, "Please enter a Fathername", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(SingleCard.this, "Plesae enter a name", Toast.LENGTH_SHORT).show();
                }






            }
        });


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(SingleCard.this);

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

                        String f = year + "-" + month + "-" + day;

                        dob.setText(f);

                        // fd = f;

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


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_TAKE_PHOTO && resultCode == RESULT_OK) {

            try {

                Log.d("asdasasd", fileUri.getPath());
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);

                imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                Log.d("asdasasd", e.toString());
                e.printStackTrace();
            }


        }
        else if (requestCode == PICK_IMAGE_REQUEST  && resultCode == RESULT_OK){


            fileUri = data.getData();

            Log.d( "mukul", fileUri.toString());

            String mCurrentPhotoPath = getPath(SingleCard.this, fileUri);

            file = new File(mCurrentPhotoPath);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(bitmap);



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
