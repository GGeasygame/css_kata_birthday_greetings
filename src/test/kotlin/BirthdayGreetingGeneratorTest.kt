import org.example.BirthdayGreetingGenerator
import org.example.domain.Friend
import org.example.domain.Greeting
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BirthdayGreetingGeneratorTest {

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
        val friends = listOf(generateFriend(
            firstName = "John",
            dateOfBirth = LocalDate.of(2000, 1, 1),
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertTrue { greetings.isEmpty() }
    }

    @Test
    fun `When 1 friend with date of birth today then generate greetings`() {
        // arrange
        val friends = listOf(generateFriend(
            firstName = "John",
            dateOfBirth = today,
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertFalse { greetings.isEmpty() }
    }

    @Test
    fun `When 1 friend with birthday today then generate greetings`() {
        // arrange
        val friends = listOf(generateFriend(
            firstName = "John",
            dateOfBirth = today.minusYears(30),
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertFalse { greetings.isEmpty() }
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
        val greetings = testee.generateGreetings(listOf(friend), today)

        // assert
        assertFalse { greetings.isEmpty() }
        assertEquals(expected, greetings[0])
    }

    @Test
    fun `When 2 friends with birthday today then generate 2 greetings`() {
        // arrange
        val friends = listOf(generateFriend(
            firstName = "John",
            dateOfBirth = today.minusYears(30),
        ), generateFriend(
            firstName = "Jane",
            dateOfBirth = today.minusYears(30),
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertEquals(2, greetings.size)
    }

    @Test
    fun `When 2 friends with birthday today and 2 friends on different day then generate 2 greetings`() {
        // arrange
        val friends = listOf(generateFriend(
            firstName = "John",
            dateOfBirth = today.minusYears(30),
        ), generateFriend(
            firstName = "Jane",
            dateOfBirth = today.minusYears(30),
        ), generateFriend(
            firstName = "Justin",
            dateOfBirth = today.minusDays(3),
        ), generateFriend(
            firstName = "Juda",
            dateOfBirth = today.minusDays(3),
        ))

        // act
        val greetings = testee.generateGreetings(friends, today)

        // assert
        assertEquals(2, greetings.size)
    }

    private fun generateFriend(firstName: String, dateOfBirth: LocalDate) : Friend {
        return Friend(firstName, "Doe", dateOfBirth, "john.doe@email.com")
    }
}