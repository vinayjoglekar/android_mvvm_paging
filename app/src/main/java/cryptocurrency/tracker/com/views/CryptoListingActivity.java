package cryptocurrency.tracker.com.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cryptocurrency.tracker.com.R;
import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.data.db.AppDataBase;
import cryptocurrency.tracker.com.viewmodels.CryptoPagingViewModel;

public class CryptoListingActivity extends AppCompatActivity {
    public static String TAG = "CRYPTOAPP";
    RecyclerView crypto_list;
    ArrayList<CryptoModel> cryptoModelArrayList;
    int counter = 1;
    AppDataBase db = AppDataBase.getAppDatabase();
    CryptoPagingViewModel viewModel;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_listing);
        cryptoModelArrayList = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(CryptoPagingViewModel.class);
        initList();
        observeViewModel(viewModel);
    }

    private void initList() {
        crypto_list = findViewById(R.id.crypto_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        crypto_list.setLayoutManager(llm);
        crypto_list.setItemViewCacheSize(20);
        crypto_list.setDrawingCacheEnabled(true);
        crypto_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
        );
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_divider_margin));
        crypto_list.addItemDecoration(divider);
    }

    private void observeViewModel(CryptoPagingViewModel viewModel) {
        CryptoPagingAdapter postAdapter = new CryptoPagingAdapter();
        viewModel.pagedListLiveData.observe(this, pagedList -> {
            postAdapter.setListener(item -> {
                Toast.makeText(this, item.name, Toast.LENGTH_LONG).show();
            });
            postAdapter.submitList(pagedList);
        });

        viewModel.networkState.observe(this, networkState -> {
            postAdapter.setNetworkState(networkState);
            Log.d(TAG, "Network Changed");
        });
        crypto_list.setAdapter(postAdapter);
    }


    private void startDBInserting(final ArrayList<CryptoModel> arrayList) {
        new Thread(() -> {
//            db.userDao().insertAllList(arrayList);
//                Log.d(TAG, "run: " + db.userDao().getAll().size());
//                List<CryptoModel> list = db.userDao().getAll();
//                Log.d(TAG, "run: " + list.size());
        }).start();
    }

}
