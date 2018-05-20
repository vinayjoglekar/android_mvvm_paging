package cryptocurrency.tracker.com.data.rest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;

import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.data.CryptoResponse;
import cryptocurrency.tracker.com.data.db.AppDataBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cryptocurrency.tracker.com.views.CryptoListingActivity.TAG;

public class CryptoRepository {

    ApiInterface apiInterface;

    public LiveData<ArrayList<CryptoModel>> getData(int start, AppDataBase dataBase) {
        final MutableLiveData<ArrayList<CryptoModel>> data = new MutableLiveData<>();
//        if (dataBase.userDao().getAll() != null && dataBase.userDao().getAll().size() > 0)
//            data.setValue((ArrayList<CryptoModel>) dataBase.userDao().getAll());
//        else
            getCryptoData(start);
        return data;
    }



    public LiveData<ArrayList<CryptoModel>> getCryptoData(int start) {
        final MutableLiveData<ArrayList<CryptoModel>> data = new MutableLiveData<>();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCryptoResponse("INR", start, 20).enqueue(new Callback<CryptoResponse>() {
            @Override
            public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getMetadata().getTimestamp());
                data.setValue(response.body().getCryptoData());
            }

            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: fail" + t.getLocalizedMessage());

            }
        });

        return data;
    }

}
