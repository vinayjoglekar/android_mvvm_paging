package cryptocurrency.tracker.com.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CryptoQuotes implements Serializable {
    @Embedded
    @SerializedName("USD")
    @Expose
    public USD uSD;

    @Ignore
    @SerializedName("BTC")
    @Expose
    public BTC bTC;

    public USD getUSD() {
        return uSD;
    }

    public BTC getBTC() {
        return bTC;
    }

    public void setuSD(USD uSD) {
        this.uSD = uSD;
    }

    public void setbTC(BTC bTC) {
        this.bTC = bTC;
    }
}
