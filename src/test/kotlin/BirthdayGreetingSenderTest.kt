import org.example.BirthdayGreetingGenerator
import org.example.domain.Friend
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BirthdayGreetingSenderTest {

    private val testee = BirthdayGreetingGenerator()
    private val today = LocalDate.of(2024, 12, 12)

    @Test
    fun `When no friends then send no greetings`() {
        // act
        val greetings = testee.generateGreetings(emptyList(), today)

        // assert
        assertTrue { greetings.isEmpty() }
    }

    @Test
    fun `When 1 friend with birthday not today`() {
        // arrange
        val friends = listOf(Friend(
            firstName = "John",
            lastName = "Doe",
            dateOfBirth = LocalDate.of(2000, 1, 1),
            email = "john.doe@email.com"
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertTrue { greetings.isEmpty() }
    }

    @Test
    fun `When 1 friend with date of birth today then generate greetings`() {
        // arrange
        val friends = listOf(Friend(
            firstName = "John",
            lastName = "Doe",
            dateOfBirth = today,
            email = "john.doe@email.com"
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertFalse { greetings.isEmpty() }
    }

    @Test
    fun `When 1 friend with birthday today then generate greetings`() {
        // arrange
        val friends = listOf(Friend(
            firstName = "John",
            lastName = "Doe",
            dateOfBirth = today.minusYears(30),
            email = "john.doe@email.com"
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertFalse { greetings.isEmpty() }
    }
}