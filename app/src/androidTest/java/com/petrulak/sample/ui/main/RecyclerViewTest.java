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
public class RecyclerViewTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void recyclerViewTest() {
    ViewInteraction appCompatButton = onView(
      allOf(withId(R.id.btn1), withText("15days"), isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction linearLayout = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.recyclerView),
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            1)),
        0),
        isDisplayed()));
    linearLayout.check(matches(isDisplayed()));

    ViewInteraction linearLayout2 = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.recyclerView),
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            1)),
        1),
        isDisplayed()));
    linearLayout2.check(matches(isDisplayed()));

    ViewInteraction linearLayout3 = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.recyclerView),
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            1)),
        2),
        isDisplayed()));
    linearLayout3.check(matches(isDisplayed()));

    ViewInteraction linearLayout4 = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.recyclerView),
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            1)),
        14),
        isDisplayed()));
    linearLayout4.check(matches(isDisplayed()));

    ViewInteraction linearLayout5 = onView(
      allOf(childAtPosition(
        allOf(withId(R.id.recyclerView),
          childAtPosition(
            IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
            1)),
        14),
        isDisplayed()));
    linearLayout5.check(matches(isDisplayed()));

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
