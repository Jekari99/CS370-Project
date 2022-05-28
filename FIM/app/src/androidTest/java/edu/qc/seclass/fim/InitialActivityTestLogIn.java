package edu.qc.seclass.fim;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InitialActivityTestLogIn {

    @Rule
    public ActivityTestRule<InitialActivity> mActivityTestRule = new ActivityTestRule<>(InitialActivity.class);

    // test only work if the right activity start. Already changed manifest but InitialActivity is not starting every time
    @Test
    public void checkingLoginSuccess(){
        onView(withId(R.id.loginEmail)).perform(typeText("test@gmail.com"),closeSoftKeyboard());

        onView(withId(R.id.loginPassword)).perform(typeText("test370"), closeSoftKeyboard());

        onView(withId(R.id.loginBtn)).perform(click());

    }

    @Test
    public void initialActivityTest3() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.loginEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardView),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.loginPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cardView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("test370"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_add),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.laminate), withText("Laminate"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroupCategory),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                0)));
        materialRadioButton.perform(scrollTo(), click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.waterResistant), withText("Water resistant"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroupType),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                6)),
                                10)));
        materialRadioButton2.perform(scrollTo(), click());

        ViewInteraction materialRadioButton3 = onView(
                allOf(withId(R.id.white), withText("White"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroupColor),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                1)));
        materialRadioButton3.perform(scrollTo(), click());

        ViewInteraction materialRadioButton4 = onView(
                allOf(withId(R.id.b2), withText("Armstrong"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroupBrand),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                1)));
        materialRadioButton4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.floor_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        appCompatEditText3.perform(scrollTo(), replaceText("25"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.floor_price),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                15)));
        appCompatEditText4.perform(scrollTo(), replaceText("12"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.floor_quantity),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                16)));
        appCompatEditText5.perform(scrollTo(), replaceText("4"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.store_ID),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                20)));
        appCompatEditText6.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.addBtn), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                21)));
        materialButton2.perform(scrollTo(), click());
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
