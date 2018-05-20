package cryptocurrency.tracker.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cryptomodel")
public class CryptoModel {

    @PrimaryKey()
    @SerializedName("id")
    @Expose
    @NonNull
    public Float id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    public String name;

    @ColumnInfo(name = "symbol")
    @SerializedName("symbol")
    @Expose
    public String symbol;

    @ColumnInfo(name = "website_slug")
    @SerializedName("website_slug")
    @Expose
    public String website_slug;

    @ColumnInfo(name = "rank")
    @SerializedName("rank")
    @Expose
    public Float rank;

    @ColumnInfo(name = "circulating_supply")
    @SerializedName("circulating_supply")
    @Expose
    public Float circulating_supply;

    @ColumnInfo(name = "total_supply")
    @SerializedName("total_supply")
    @Expose
    public Float total_supply;

    @ColumnInfo(name = "max_supply")
    @SerializedName("max_supply")
    @Expose
    public Float max_supply;

    @Embedded
    @SerializedName("quotes")
    @Expose
    public cryptocurrency.tracker.com.data.CryptoQuotes quotes;

    @ColumnInfo(name = "last_updated")
    @SerializedName("last_updated")
    @Expose
    public Float last_updated;

    
    public Float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    public Float getRank() {
        return rank;
    }

    public Float getCirculating_supply() {
        return circulating_supply;
    }

    public Float getTotal_supply() {
        return total_supply;
    }

    public Float getMax_supply() {
        return max_supply;
    }

    public cryptocurrency.tracker.com.data.CryptoQuotes getQuotes() {
        return quotes;
    }

    public Float getLast_updated() {
        return last_updated;
    }
}
