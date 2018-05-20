package cryptocurrency.tracker.com.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import java.util.ArrayList;

import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.data.db.AppDataBase;
import cryptocurrency.tracker.com.data.rest.CryptoRepository;

public class CryptoListingViewModel extends ViewModel {

    private LiveData<ArrayList<CryptoModel>> arrCryptoData;
    CryptoRepository cryptoRepository;
    AppDataBase dataBase = AppDataBase.getAppDatabase();

    LiveData<PagedList<CryptoModel>> arr;
    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = 5;

    public CryptoListingViewModel() {
        arrCryptoData = new MutableLiveData<>();
        cryptoRepository = new CryptoRepository();
    }

    public LiveData<ArrayList<CryptoModel>> getCryptoListObservable(int count) {
        int start = 1;
        if (count > 1) {
            start = (count * 20 + 1) - 20;
        }
        arrCryptoData = cryptoRepository.getCryptoData(start);
        return arrCryptoData;
    }


}
