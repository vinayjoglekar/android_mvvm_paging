package cryptocurrency.tracker.com.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.pagination.CryptoDataSource;
import cryptocurrency.tracker.com.pagination.CryptoDataSourceFactory;
import cryptocurrency.tracker.com.pagination.NetworkState;

public class CryptoPagingViewModel extends ViewModel {

    public LiveData<PagedList<CryptoModel>> pagedListLiveData;
    public LiveData<NetworkState> networkState;
    Executor executor;
    LiveData<CryptoDataSource> myDataSource;

    public CryptoPagingViewModel() {
        executor = Executors.newFixedThreadPool(5);
        CryptoDataSourceFactory wpDataSourceFactory = new CryptoDataSourceFactory(executor);
        myDataSource = wpDataSourceFactory.getMutableLiveData();
        networkState = Transformations.switchMap(myDataSource,
                (Function<CryptoDataSource, LiveData<NetworkState>>) CryptoDataSource::getNetworkState);
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20).build();
        pagedListLiveData = (new LivePagedListBuilder(wpDataSourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

}
