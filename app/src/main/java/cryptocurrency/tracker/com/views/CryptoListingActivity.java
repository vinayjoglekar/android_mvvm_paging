package cryptocurrency.tracker.com.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import cryptocurrency.tracker.com.R;
import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.data.db.AppDataBase;
import cryptocurrency.tracker.com.viewmodels.CryptoListingViewModel;

public class CryptoListingActivity extends AppCompatActivity {
    public static String TAG = "CRYPTOAPP";
    RecyclerView crypto_list;
    ArrayList<CryptoModel> cryptoModelArrayList;
    int counter = 1;
    CryptoListingAdapter cryptoListingAdapter;
    AppDataBase db = AppDataBase.getAppDatabase();
    CryptoListingViewModel viewModel;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_listing);
        cryptoModelArrayList = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(CryptoListingViewModel.class);
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
        crypto_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isLastItemVisible()) {
                    counter++;
                    observeViewModel(viewModel);
                }
            }
        });
    }

    private void observeViewModel(CryptoListingViewModel viewModel) {
        viewModel.getCryptoListObservable(counter).observe(this, cryptoModels -> {
            cryptoModelArrayList.addAll(cryptoModels);
            if (counter == 1) {
                cryptoListingAdapter = new CryptoListingAdapter();
                cryptoListingAdapter.submitList(cryptoModelArrayList);
                crypto_list.setAdapter(cryptoListingAdapter);
            } else {
                cryptoListingAdapter.notifyItemRangeInserted((counter * 20 + 1) - 20, 20);
            }
//            startDBInserting(cryptoModels);
        });
    }

    public boolean isLastItemVisible() {
        LinearLayoutManager lm = (LinearLayoutManager) crypto_list.getLayoutManager();
        int visibleItemCount = lm.getChildCount();
        int totalItemCount = lm.getItemCount();
        int pastVisiblesItems = lm.findFirstVisibleItemPosition();
        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount)
            return true;
        else return false;
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
