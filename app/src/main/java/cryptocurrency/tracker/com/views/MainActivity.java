package cryptocurrency.tracker.com.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.Navigation;
import cryptocurrency.tracker.com.R;
import cryptocurrency.tracker.com.views.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
//        View view = findViewById(R.id.my_nav_host_fragment);
//        NavController navController = Navigation.findNavController(view);
//        Button message = navController.findViewById(R.id.message);
//        message.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.destBlankFragment, null));

    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }
}
