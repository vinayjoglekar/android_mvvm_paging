package cryptocurrency.tracker.com.pagination;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import cryptocurrency.tracker.com.data.CryptoResponse;
import cryptocurrency.tracker.com.data.rest.ApiClient;
import cryptocurrency.tracker.com.data.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cryptocurrency.tracker.com.views.CryptoListingActivity.TAG;

public class CryptoDataSource extends PageKeyedDataSource {

    ApiInterface apiInterface;
    LoadInitialParams initialParams;
    LoadParams afterParams;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;
    private Executor retryExecutor;
    int count = 1;
    int start = 1;

    public CryptoDataSource(Executor retryExecutor) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        this.retryExecutor = retryExecutor;
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Log.i(TAG, "Loading Page " + 1 + " Load Size " + params.requestedLoadSize);
        final List postList = new ArrayList<>();
        initialParams = params;
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        apiInterface.getCryptoResponse("BTC", start, 20).enqueue(new Callback<CryptoResponse>() {
            @Override
            public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().getMetadata().getTimestamp());
                    postList.addAll(response.body().getCryptoData());
                    callback.onResult(postList, null, count);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                    initialParams = null;
                } else {
                    Log.e("WP API CALL", response.message());
                    initialLoading.postValue(new NetworkState(Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: fail" + t.getLocalizedMessage());
                String errorMessage;
                errorMessage = t.getMessage();
                if (t == null) {
                    errorMessage = "error";
                }
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        count++;
        if (count > 1) {
            start = (count * 20 + 1) - 20;
        }
        List postList = new ArrayList<>();
        afterParams = params;
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        apiInterface.getCryptoResponse("BTC", start, 20).enqueue(new Callback<CryptoResponse>() {
            @Override
            public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().getMetadata().getTimestamp());
                    postList.addAll(response.body().getCryptoData());
                    callback.onResult(postList, count);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                    initialParams = null;
                } else {
                    Log.e("WP API CALL", response.message());
                    initialLoading.postValue(new NetworkState(Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: fail" + t.getLocalizedMessage());
                String errorMessage;
                errorMessage = t.getMessage();
                if (t == null) {
                    errorMessage = "error";
                }
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }
}
