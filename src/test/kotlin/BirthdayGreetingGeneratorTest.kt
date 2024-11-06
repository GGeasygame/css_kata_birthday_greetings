import org.example.BirthdayGreetingGenerator
import org.example.domain.Friend
import org.example.domain.Greeting
import java.time.LocalDate
import kotlin.test.*

class BirthdayGreetingGeneratorTest {

    private val testee = BirthdayGreetingGenerator()
    private val today = LocalDate.of(2024, 12, 12)

    @Test
    fun `When 1 friend with birthday not today then generate no greeting`() {
        // arrange
        val friend = generateFriend(
            firstName = "John",
            dateOfBirth = LocalDate.of(2000, 1, 1),
        )

        // act
        val greeting = testee.generateGreeting(friend, today)

        // assert
        assertNull(greeting)
    }

    @Test
    fun `When 1 friend with date of birth today then generate greetings`() {
        // arrange
        val friend = generateFriend(
            firstName = "John",
            dateOfBirth = today,
        )

        // act
        val greeting = testee.generateGreeting(friend, today)

        // assert
        assertNotNull(greeting)
    }

    @Test
    fun `When 1 friend with birthday today then generate greetings`() {
        // arrange
        val friend = generateFriend(
            firstName = "John",
            dateOfBirth = today.minusYears(30),
        )

        // act
        val greeting = testee.generateGreeting(friend, today)

        // assert
        assertNotNull(greeting)
    }

    @Test
    fun `When 1 friend with birthday today then generate fitting greeting`() {
        // arrange
        val friend = generateFriend(
            firstName = "John",
            dateOfBirth = today,
        )
        val expected = Greeting("Happy birthday, dear John!", friend)

        // act
        val greeting = testee.generateGreeting(friend, today)

        // assert
        assertEquals(expected, greeting)
    }

    private fun generateFriend(firstName: String, dateOfBirth: LocalDate) : Friend {
        return Friend(firstName, "Doe", dateOfBirth, "john.doe@email.com")
    }
}