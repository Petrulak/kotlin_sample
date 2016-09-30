package com.petrulak.sample.ui.main;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.petrulak.sample.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ButtonsExistsTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void buttonsExistsTest() {
    ViewInteraction button = onView(
      allOf(withId(R.id.btn1),
        childAtPosition(
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            0),
          0),
        isDisplayed()));
    button.check(matches(isDisplayed()));

    ViewInteraction button2 = onView(
      allOf(withId(R.id.btn2),
        childAtPosition(
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            0),
          1),
        isDisplayed()));
    button2.check(matches(isDisplayed()));

    ViewInteraction button3 = onView(
      allOf(withId(R.id.btn3),
        childAtPosition(
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            0),
          2),
        isDisplayed()));
    button3.check(matches(isDisplayed()));

    ViewInteraction button4 = onView(
      allOf(withId(R.id.btn4),
        childAtPosition(
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            0),
          3),
        isDisplayed()));
    button4.check(matches(isDisplayed()));

    ViewInteraction button5 = onView(
      allOf(withId(R.id.btn4),
        childAtPosition(
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            0),
          3),
        isDisplayed()));
    button5.check(matches(isDisplayed()));

  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
