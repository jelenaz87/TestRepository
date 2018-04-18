package com.example.android.sunshine.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.sunshine.R;
import com.example.android.sunshine.ui_component.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by jelena.zivanovic on 12/15/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestViewMainActivity {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextViewToday() {
        onView(withText("Tomorrow")).perform(click());
        onView(withText("Humidity")).check(matches(isDisplayed()));
    }

    @Test
    public void onClick() {
        onView(withId(R.id.recyclerview_forecast)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));

    }
    @Test
    public void ensureSwipe () {
        onView(withId(R.id.recyclerview_forecast)).perform(RecyclerViewActions.scrollToPosition(mActivityRule.getActivity().mRecyclerView.getAdapter().getItemCount()-1));
        onView(withId(R.id.recyclerview_forecast)).perform(RecyclerViewActions.actionOnItemAtPosition(mActivityRule.getActivity().mRecyclerView.getAdapter().getItemCount()-1, click()));
    }

    @Test
    public void clickOnSettings () {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(click());
        onView(withText("Weather Notifications")).perform(click());

    }

    @Test
    public void checkSettingsItem () {

    }






}
