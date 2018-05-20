package cryptocurrency.tracker.com.views;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.databinding.RowCryptoListingBinding;

public class CryptoListingAdapter extends ListAdapter<CryptoModel, CryptoListingAdapter.MyViewHolder> {


    protected CryptoListingAdapter() {
        super(DIFF_CALLBACK);

    }

    @NonNull
    @Override
    public CryptoListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        RowCryptoListingBinding itemBinding =
                RowCryptoListingBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoListingAdapter.MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final RowCryptoListingBinding binding;

        public MyViewHolder(RowCryptoListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CryptoModel item) {
            binding.setCrypto(item);
            binding.executePendingBindings();
        }
    }

    public static final DiffUtil.ItemCallback<CryptoModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CryptoModel>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull CryptoModel oldUser, @NonNull CryptoModel newUser) {
                    return oldUser.getId() == newUser.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull CryptoModel oldUser, @NonNull CryptoModel newUser) {
                    return oldUser.equals(newUser);
                }
            };
}
