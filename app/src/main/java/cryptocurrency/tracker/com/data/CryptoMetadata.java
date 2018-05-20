package cryptocurrency.tracker.com.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoMetadata {
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("num_cryptocurrencies")
    @Expose
    private Integer num_cryptocurrencies;
    @SerializedName("error")
    @Expose
    private Object error;

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getNum_cryptocurrencies() {
        return num_cryptocurrencies;
    }

    public void setNum_cryptocurrencies(Integer num_cryptocurrencies) {
        this.num_cryptocurrencies = num_cryptocurrencies;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}