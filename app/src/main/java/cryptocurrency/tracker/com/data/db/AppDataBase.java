package cryptocurrency.tracker.com.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.util.Log;

import cryptocurrency.tracker.com.CryptoApp;
import cryptocurrency.tracker.com.data.CryptoModel;

import static cryptocurrency.tracker.com.views.CryptoListingActivity.TAG;

@Database(entities = {CryptoModel.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;

    public abstract CryptoModelDao userDao();

    public static AppDataBase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(CryptoApp.instance.getApplicationContext(), AppDataBase.class, "crypto-database")
                            .allowMainThreadQueries()
                            .build();
        }

        String currentDBPath = CryptoApp.instance.getApplicationContext().getDatabasePath("crypto-database").getAbsolutePath();
        Log.d(TAG, "getAppDatabase: " + currentDBPath);

        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }
}
