package org.example

import org.example.domain.Friend
import org.example.domain.Greeting
import java.time.LocalDate

class BirthdayGreetingGenerator {
    fun generateGreetings(friends: List<Friend>, today: LocalDate): List<Greeting> {
        return friends
            .filter { friend -> friend.hasBirthday(today) }
            .map { friend -> Greeting("Happy birthday, dear ${friend.firstName}!", friend) }
    }
}