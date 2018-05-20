package cryptocurrency.tracker.com.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class CryptoResponse implements Serializable {

    @SerializedName("data")
    @Expose
    Map<String, CryptoModel> cryptoData;
    @SerializedName("metadata")
    @Expose
    private CryptoMetadata metadata;

    public ArrayList<CryptoModel> getCryptoData() {
        ArrayList<CryptoModel> cryptoModelArrayList = new ArrayList<>();
        for (String key : cryptoData.keySet()) {
            System.out.println("key : " + key);
            System.out.println("value : " + cryptoData.get(key));
            cryptoModelArrayList.add(cryptoData.get(key));
        }
        return cryptoModelArrayList;
    }

    public CryptoMetadata getMetadata() {
        return metadata;
    }

}
