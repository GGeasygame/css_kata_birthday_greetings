package org.example

import java.nio.file.Path

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    val path = Path.of(args[0])
    val greetingSender = BirthdayGreetingSender(
        greetingSender = FakeEmailGreetingSender(),
        generator = BirthdayGreetingGenerator(),
        friendReader = CsvFriendReader(path)
    )
    greetingSender.sendGreetingsToFriendsWithBirthdayToday()
}