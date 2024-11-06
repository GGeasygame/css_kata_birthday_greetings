package org.example

import org.example.domain.Greeting

interface GreetingSender {
    fun sendGreeting(greeting: Greeting)
}