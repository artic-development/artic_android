package com.articrew.artic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId

fun buttonClick(id: Int) {
    onView(withId(id)).perform(click())
}