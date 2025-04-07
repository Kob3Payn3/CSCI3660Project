package school.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.not;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testStartGameButtonClick() {
        onView(withId(R.id.start_game_button)).perform(click());
        onView(withId(R.id.start_game_button)).check(matches(not(isEnabled())));
        onView(withId(R.id.timer_options_button)).check(matches(not(isEnabled())));
    }

    @Test
    public void testMoleHoleButtons() {
        for (int i=1;i<=25;i++) {
            String moleHoleButtonId = "moleHole" + i;
            int testId = ApplicationProvider.getApplicationContext().getResources().getIdentifier(
                    moleHoleButtonId, "id", ApplicationProvider.getApplicationContext().getPackageName());

            onView(withId(testId)).check(matches(isDisplayed()));
            onView(withId(testId)).perform(click());
            onView(withId(testId)).check(matches(not(isEnabled())));
        }

    }
    @Test
    public void testHelpButton() {
        onView(withId(R.id.helpButton)).perform(click());
    }
    @Test
    public void testTimerOptions() {

        onView(withId(R.id.timer_options_button)).perform(click());


    }

}
