package org.example

import org.example.domain.Greeting

data class Email(val to: String, val subject: String, val message: String)

class FakeEmailGreetingSender(private val notify: (Email) -> Unit = { println(it) }) : GreetingSender {
    override fun sendGreeting(greeting: Greeting) {
        val email = Email(
            to = greeting.recipientEmail,
            subject = "Happy Birthday!",
            message = greeting.greeting,
        )
        notify(email)
    }
}