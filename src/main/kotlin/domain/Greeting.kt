package org.example.domain

data class Greeting(val greeting: String, val friend: Friend) {

    val recipientEmail: String
        get() {
            return friend.email
        }
}