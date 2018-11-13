package com.googlemap.cardbanwaloo;

import com.googlemap.cardbanwaloo.AddqueryPOJO.AddqueryBean;
import com.googlemap.cardbanwaloo.BulkCardDetailsPOJO.BulkCardDetailsBean;
import com.googlemap.cardbanwaloo.BulkPOJO.CardBean;
import com.googlemap.cardbanwaloo.BusinessCardPOJO.BusinessCardBean;
import com.googlemap.cardbanwaloo.BusinessReportPOJO.BusinessReportBean;
import com.googlemap.cardbanwaloo.ChangePAsswordPOJO.ChangeBean;
import com.googlemap.cardbanwaloo.CityPOJO.CityBean;
import com.googlemap.cardbanwaloo.ForgetPOJO.ForgotBean;
import com.googlemap.cardbanwaloo.GalleryPOJO.GalleryBean;
import com.googlemap.cardbanwaloo.GalleryidPOJO.GalleryidBean;
import com.googlemap.cardbanwaloo.HomePOJO.HomeBean;
import com.googlemap.cardbanwaloo.LoginPOJO.LoginBean;
import com.googlemap.cardbanwaloo.MyProfilePOJO.ProfileBean;
import com.googlemap.cardbanwaloo.QueryDetailsPOJO.QueryDeatailsBean;
import com.googlemap.cardbanwaloo.RegisterPOJO.RegisterBean;
import com.googlemap.cardbanwaloo.SchoolPOJO.SchoolBean;
import com.googlemap.cardbanwaloo.SingleCardPOJO.SingleCardBean;
import com.googlemap.cardbanwaloo.SingleCarddetailsPOJO.SingleCardDetailsBean;
import com.googlemap.cardbanwaloo.StatePOJO.StateBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AllApiInterface {


    @GET("mobile/login.aspx")
    Call<LoginBean> login
            (@Query("userid") String user,
             @Query("pwd") String password);


    @GET("mobile/signup.aspx")
    Call<RegisterBean> register
            (@Query("name") String name,
             @Query("email") String email,
             @Query("mob") String mob,
             @Query("school") String school,
             @Query("add") String address);


    @GET("mobile/changepswd.aspx")
    Call<ChangeBean> chnagepass
            (@Query("id") String id,
             @Query("Password") String password,
             @Query("newpswd") String newpass);


    @GET("mobile/forgetpswd.aspx")
    Call<ForgotBean> forget
            (@Query("id") String id);


    @GET("mobile/getdetail.aspx")
    Call<List<HomeBean>> home
            (@Query("AppMstid") String id);


    @Multipart
    @POST("mobile/singlecard.aspx")
    Call<SingleCardBean> singlecard
            (@Query("appmstid") String id,
             @Query("mob") String from,
             @Query("name") String to,
             @Query("admno") String name,
             @Query("cls") String cls,
             @Query("address") String addr,
             @Query("father") String father,
             @Query("mother") String mother,
             @Query("filename1") String filename,
             @Part MultipartBody.Part file,
             @Query("dob") String dob
            );

    @Multipart
    @POST("/mobile/myprofile.aspx")
    Call<ProfileBean> myprofile
            (@Query("appmstid") String id,
             @Query("mob") String from,
             @Query("state") String to,
             @Query("city") String name,
             @Query("address") String cls,
             @Part MultipartBody.Part file
            );


    @GET("/mobile/state.aspx")
    Call<List<StateBean>> sta
            (@Query("id") String id
            );


    @GET("/mobile/state.aspx")
    Call<List<SchoolBean>> sch
            (@Query("id") String id
            );


    @GET("/mobile/state.aspx")
    Call<List<CityBean>> cityyy
            (@Query("id") String id
            );


    @GET("mobile/singlecarddetail.aspx")
    Call<List<SingleCardDetailsBean>> singlecarddetails
            (@Query("name") String name,
             @Query("userId") String from,
             @Query("from") String to,
             @Query("to") String togf,
             @Query("status") String cls
            );


    @GET("mobile/Bulkcarddetail.aspx")
    Call<List<BulkCardDetailsBean>> bulkcarddetails
            (@Query("userId") String user,
             @Query("from") String fromm,
             @Query("to") String too,
             @Query("name") String namee,
             @Query("status") String cls
            );

    @GET("mobile/QueryReport.aspx")
    Call<List<QueryDeatailsBean>> qyery
            (@Query("userId") String user,
             @Query("from") String fromm,
             @Query("to") String too,
             @Query("status") String cls
            );


    @GET("mobile/Query.aspx")
    Call<AddqueryBean> addqueryy
            (@Query("userId") String user,
             @Query("subject") String fromm,
             @Query("sg") String too
            );


    @Multipart
    @POST("mobile/bulkcard.aspx")
    Call<CardBean> c
            (@Query("appmstid") String user,
             @Query("name") String fromm,
             @Query("other") String too ,
             @Query("noofcard") String bno ,
             @Query("desc") String des  ,
             @Query("remarks") String remark  ,
             @Query("filename1") String filename  ,
             @Part MultipartBody.Part file1,
             @Part List<MultipartBody.Part> file2
             );

    @Multipart
    @POST("mobile/Businesscard.aspx")
    Call<BusinessCardBean> bu
            (@Query("appmstid") String user,
             @Query("detail") String fromm,
             @Query("remarks") String too ,
             @Query("photo") String filename,
             @Query("sign") String filename1,
             @Part MultipartBody.Part file1,
             @Part MultipartBody.Part file2
            );


    @GET("mobile/Businessreport.aspx")
    Call<List<BusinessReportBean>> businessreport
            (@Query("name") String name,
             @Query("userId") String from,
             @Query("from") String to,
             @Query("to") String togf,
             @Query("status") String cls
            );



    @GET("mobile/Galleryid.aspx")
    Call<List<GalleryBean>> galler();


    @GET("mobile/Galleryid.aspx")
    Call<List<GalleryidBean>> galleryid
            (@Query("id") String id
            );


}
