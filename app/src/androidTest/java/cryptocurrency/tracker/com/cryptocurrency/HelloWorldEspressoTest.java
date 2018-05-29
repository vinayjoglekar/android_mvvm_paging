package cryptocurrency.tracker.com.cryptocurrency;


import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cryptocurrency.tracker.com.R;
import cryptocurrency.tracker.com.views.CryptoListingActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {

    @Rule
    public ActivityTestRule<CryptoListingActivity> mActivityRule =
            new ActivityTestRule(CryptoListingActivity.class);

//    @Test
//    public void checkhelloworld() {
//        onView(withText("Hello world!")).check(matches(isDisplayed()));

//    }

    @Test
    public void testSample() {
        SystemClock.sleep(3000);
        if (getRVcount() > 0) {

//            onView(withId(R.id.crypto_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//            onView(withId(R.id.crypto_list)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Stellar"))));
//            SystemClock.sleep(3000);
            onView(withId(R.id.crypto_list)).perform(RecyclerViewActions.scrollToPosition(getRVcount() - 1));
            SystemClock.sleep(10000);
        }

    }

    private int getRVcount() {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.crypto_list);
        return recyclerView.getAdapter().getItemCount();
    }

}
