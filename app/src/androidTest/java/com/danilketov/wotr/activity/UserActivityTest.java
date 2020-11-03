package com.danilketov.wotr.activity;

import androidx.test.rule.ActivityTestRule;

import com.danilketov.wotr.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> mActivityRule = new ActivityTestRule<>(
            UserActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
        enterSearchEditText();
        clickSearchButton();
        showList();
    }

    private void showList() {
        onView(withId(R.id.recycler_view_users))
                .check(matches(isDisplayed()));
    }

    private void clickSearchButton() {
        onView(withId(R.id.search_floating_action_button))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    private void enterSearchEditText() {
        onView(withId(R.id.search_edit_text))
                .perform(typeText("west"))
                .check(matches(isDisplayed()));
    }
}