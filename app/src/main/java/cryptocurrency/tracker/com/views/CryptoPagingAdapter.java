package cryptocurrency.tracker.com.views;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cryptocurrency.tracker.com.R;
import cryptocurrency.tracker.com.data.CryptoModel;
import cryptocurrency.tracker.com.databinding.NetworkStateItemBinding;
import cryptocurrency.tracker.com.databinding.RowCryptoListingBinding;
import cryptocurrency.tracker.com.pagination.NetworkState;

public class CryptoPagingAdapter extends PagedListAdapter<CryptoModel, RecyclerView.ViewHolder> {
    private static final String TAG = "PostAdapter";
    OnItemClickListener listener;
    private NetworkState networkState;

    protected CryptoPagingAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final RowCryptoListingBinding binding;

        public MyViewHolder(RowCryptoListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CryptoModel item, final OnItemClickListener listener) {
            binding.setCrypto(item);
            binding.executePendingBindings();
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {
        private final NetworkStateItemBinding binding;

        public NetworkStateItemViewHolder(NetworkStateItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NetworkState item) {
            binding.setNetworkState(item);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.row_crypto_listing) {
            RowCryptoListingBinding itemBinding =
                    RowCryptoListingBinding.inflate(layoutInflater, parent, false);
            return new MyViewHolder(itemBinding);
        } else if (viewType == R.layout.network_state_item) {
            NetworkStateItemBinding itemBinding =
                    NetworkStateItemBinding.inflate(layoutInflater, parent, false);
            return new NetworkStateItemViewHolder(itemBinding);
        } else {
            throw new IllegalArgumentException("unknown type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).bind(getItem(position), listener);
        } else if (holder instanceof NetworkStateItemViewHolder) {
            ((NetworkStateItemViewHolder) holder).bind(networkState);
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

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.row_crypto_listing;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED && networkState != NetworkState.MAXPAGE) {
            return true;
        } else {
            return false;
        }
    }


}
