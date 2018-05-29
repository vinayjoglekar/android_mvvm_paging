package cryptocurrency.tracker.com.pagination;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import java.util.concurrent.Executor;

public class CryptoDataSourceFactory extends DataSource.Factory {


    MutableLiveData<CryptoDataSource> mutableLiveData;
    private CryptoDataSource cryptoDataSource;
    private Executor executor;

    public CryptoDataSourceFactory(Executor executor) {
        this.executor = executor;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        cryptoDataSource = new CryptoDataSource(executor);
        mutableLiveData.postValue(cryptoDataSource);
        return cryptoDataSource;
    }

    public MutableLiveData<CryptoDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
