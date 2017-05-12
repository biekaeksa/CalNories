package com.spiderman.calnories.data.source.remote;

import com.spiderman.calnories.data.FoodModel;
import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.util.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public interface APIService {
    String BASE_URL = AppConstants.APIUrl.BASE_API;


    @FormUrlEncoded
    @POST("user/login")
    Observable<UserModel.UserDataModel> postSignInUp(@Field("id") String id, @Field("displayName") String displayName,
                                                   @Field("email") String email, @Field("photoUrl") String photoUrl);

    @FormUrlEncoded
    @PUT("user/updatedata")
    Observable<UserModel.UserDataModel> putCalories(@Field("id") String id, @Field("weight") Float weight,
                                                    @Field("height") Float height, @Field("age") int age,
                                                    @Field("calories_target") int target);

    @GET("food/{category}")
    Observable<FoodModel.FoodDataModel> loadFoodCategory(@Path("category") String categori);


    class factory {
        public static APIService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }


}
