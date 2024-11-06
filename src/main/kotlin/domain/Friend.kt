package org.example.domain

import java.time.LocalDate
import java.time.MonthDay

data class Friend(val firstName: String, val lastName: String, val dateOfBirth: LocalDate, val email: String) {
    fun hasBirthday(today: LocalDate): Boolean {
        return MonthDay.from(dateOfBirth) == MonthDay.from(today)
    }

}