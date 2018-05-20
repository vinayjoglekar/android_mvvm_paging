package cryptocurrency.tracker.com.data;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BTC implements Serializable {
    @ColumnInfo(name = "btc_price")
    @SerializedName("price")
    @Expose
    public Float price;

    @ColumnInfo(name = "btc_volume_24h")
    @SerializedName("volume_24h")
    @Expose
    public Float volume_24h;

    @ColumnInfo(name = "btc_market_cap")
    @SerializedName("market_cap")
    @Expose
    public Float market_cap;

    @ColumnInfo(name = "btc_percent_change_1h")
    @SerializedName("percent_change_1h")
    @Expose
    public Float percent_change_1h;

    @ColumnInfo(name = "btc_percent_change_24h")
    @SerializedName("percent_change_24h")
    @Expose
    public Float percent_change_24h;

    @ColumnInfo(name = "btc_percent_change_7d")
    @SerializedName("percent_change_7d")
    @Expose
    public Float percent_change_7d;

    public Float getPrice() {
        return price;
    }

    public Float getVolume_24h() {
        return volume_24h;
    }

    public Float getMarket_cap() {
        return market_cap;
    }

    public Float getPercent_change_1h() {
        return percent_change_1h;
    }

    public Float getPercent_change_24h() {
        return percent_change_24h;
    }

    public Float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setVolume_24h(Float volume_24h) {
        this.volume_24h = volume_24h;
    }

    public void setMarket_cap(Float market_cap) {
        this.market_cap = market_cap;
    }

    public void setPercent_change_1h(Float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public void setPercent_change_24h(Float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public void setPercent_change_7d(Float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }
}
