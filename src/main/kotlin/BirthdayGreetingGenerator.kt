package org.example

import org.example.domain.Friend
import org.example.domain.Greeting
import java.time.LocalDate

class BirthdayGreetingGenerator {
    fun generateGreeting(friend: Friend, today: LocalDate): Greeting? {
        return if (friend.hasBirthday(today)) {
            createGreeting(friend)
        } else {
            null
        }
    }

    private fun createGreeting(friend: Friend) = Greeting("Happy birthday, dear ${friend.firstName}!", friend)
}