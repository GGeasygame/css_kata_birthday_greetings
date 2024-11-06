import org.example.Email
import org.example.FakeEmailGreetingSender
import org.example.domain.Friend
import org.example.domain.Greeting
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class FakeEmailGreetingSenderTest {

    @Test
    fun `Given a greeting then create an email and print it`() {
        // arrange
        var actualEmail: Email? = null
        val testee = FakeEmailGreetingSender { email -> actualEmail = email }
        val emailAdress = "jon@doe.com"
        val message = "Hello"
        val greeting = Greeting(
            greeting = message,
            friend = Friend(
                firstName = "Jon",
                lastName = "Doe",
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = emailAdress
            )
        )
        val expectedEmail = Email(
            to = emailAdress,
            subject = "Happy Birthday!",
            message = message
        )

        // act
        testee.sendGreeting(greeting)

        // assert
        assertEquals(expectedEmail, actualEmail)
    }
}