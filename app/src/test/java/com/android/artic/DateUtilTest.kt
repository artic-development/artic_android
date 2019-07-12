package com.articrew.artic

import com.articrew.artic.util.howMuchPreviousFrom
import junit.framework.Assert.assertEquals
import khronos.*
import org.junit.Test

// TODO 영어로 바꾸면? 다른 언어로 바꾸면?!
class DateUtilTest {
    private val logDate = Dates.of(year = 2019, month = 12, day = 21, hour = 22, minute = 10)

    @Test
    fun testMinutes() {
        // 10분 차이!
        val getDate = Dates.of(year = 2019, month = 12, day = 21, hour = 22, minute = 20)
        assertEquals("10분 전", getDate.howMuchPreviousFrom(logDate))
        assertEquals("14분 전", (getDate + 4.minutes).howMuchPreviousFrom(logDate))
    }

    @Test
    fun testHours() {
        // 23시간 차이!
        val getDate = logDate + 23.hours
        assertEquals("23시간 전", getDate.howMuchPreviousFrom(logDate))
    }

    @Test
    fun testDays() {
        // 10일 차이!
        val getDate = Dates.of(year = 2019, month = 12, day = 31, hour = 22, minute = 20)
        assertEquals("10일 전", getDate.howMuchPreviousFrom(logDate))
    }

    @Test
    fun testMonths() {
        // 10달 차이!
        val getDate = Dates.of(year = 2020, month = 10, day = 21, hour = 22, minute = 20)
        assertEquals("10달 전", getDate.howMuchPreviousFrom(logDate))
    }

    @Test
    fun testYears() {
        // 10년 차이!
        val getDate = Dates.of(year = 2029, month = 12, day = 21, hour = 22, minute = 20)
        assertEquals("10년 전", getDate.howMuchPreviousFrom(logDate))
    }
}