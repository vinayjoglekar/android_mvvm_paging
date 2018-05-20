package cryptocurrency.tracker.com.data.rest;

import cryptocurrency.tracker.com.data.CryptoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Vinay.Joglekar1 on 09-03-2018.
 */

public interface ApiInterface {


//    @FormUrlEncoded
//    @POST("category/index")
//    Call<VideosResponse> getVideos(@Field("action") String action,
//                                   @Field("page") String count);

//    @GET("menu/index")
//    Call<MenuListResponse> getMenuData();

    @GET(".")
    Call<CryptoResponse> getCryptoResponse
            (@Query("convert") String btc, @Query("start") int start, @Query("limit") int limit);

}
