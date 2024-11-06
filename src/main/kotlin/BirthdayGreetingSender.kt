package org.example

import java.time.LocalDate

class BirthdayGreetingSender(
    private val friendReader: FriendReader,
    private val greetingSender: GreetingSender,
    private val generator: BirthdayGreetingGenerator
) {
    fun sendGreetingsToFriendsWithBirthdayToday() {
        val friends = friendReader.readFriends()
        val today = LocalDate.now()
        friends.forEach { friend ->
            val greeting = generator.generateGreeting(friend, today)
            if (greeting != null) {
                greetingSender.sendGreeting(greeting)
            }
        }
    }
}
