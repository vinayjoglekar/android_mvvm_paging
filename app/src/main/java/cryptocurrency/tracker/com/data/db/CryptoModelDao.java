package cryptocurrency.tracker.com.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import cryptocurrency.tracker.com.data.CryptoModel;

@Dao
public interface CryptoModelDao {

    @Query("SELECT * FROM cryptomodel")
    List<CryptoModel> getAll();

    @Insert
    void insertAll(CryptoModel cryptomodels);

    @Delete
    void delete(CryptoModel cryptomodel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllList(ArrayList<CryptoModel> countries);
}
