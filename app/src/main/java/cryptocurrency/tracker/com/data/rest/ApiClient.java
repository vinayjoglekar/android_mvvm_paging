package cryptocurrency.tracker.com.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vinay.Joglekar1 on 09-03-2018.
 */

public class ApiClient {
    private static Retrofit retrofit = null;
    static String BASE_URL = "https://api.coinmarketcap.com/v2/ticker/";

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
