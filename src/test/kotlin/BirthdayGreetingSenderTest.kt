import org.example.BirthdayGreetingGenerator
import org.example.BirthdayGreetingSender
import org.example.FriendReader
import org.example.GreetingSender
import org.example.domain.Friend
import org.example.domain.Greeting
import org.mockito.kotlin.*
import java.time.LocalDate
import kotlin.test.Test

class BirthdayGreetingSenderTest {

    @Test
    fun `When no friends then send no greetings`() {
        // arrange
        val friendReader = mock<FriendReader> {
            on { readFriends() } doReturn emptyList()
        }
        val greetingSender = mock<GreetingSender>()
        val generator = mock<BirthdayGreetingGenerator>()
        val testee = BirthdayGreetingSender(friendReader, greetingSender, generator)

        // act
        testee.sendGreetingsToFriendsWithBirthdayToday()

        // assert
        verify(generator, never()).generateGreeting(any(), any())
        verify(greetingSender, never()).sendGreeting(any())
    }

    @Test
    fun `When 1 friend with birthday today then send 1 greetings`() {
        // arrange
        val today = LocalDate.now()
        val friend = createFriend("Jon", today.minusYears(2))
        val friendReader = mock<FriendReader> {
            on { readFriends() } doReturn listOf(friend)
        }
        val greeting = Greeting(friend = friend, greeting = "Hello")
        val generator = mock<BirthdayGreetingGenerator> {
            on { generateGreeting(friend, today) } doReturn greeting
        }
        val greetingSender = mock<GreetingSender>()
        val testee = BirthdayGreetingSender(friendReader, greetingSender, generator)

        // act
        testee.sendGreetingsToFriendsWithBirthdayToday()

        // assert
        verify(greetingSender).sendGreeting(greeting)
    }

    @Test
    fun `When 2 friend with birthday today and 2 with another birthday then send 2 greetings`() {
        // arrange
        val today = LocalDate.now()
        val friend1 = createFriend("Jon", today.minusYears(2))
        val friend2 = createFriend("Jane", today.minusYears(20))
        val friend3 = createFriend("Joe", today.minusYears(2).minusDays(1))
        val friend4 = createFriend("Jon", today.minusYears(2).plusDays(1))
        val friendReader = mock<FriendReader> {
            on { readFriends() } doReturn listOf(friend1, friend2, friend3, friend4)
        }
        val greeting1 = Greeting(friend = friend1, greeting = "Hello")
        val greeting2 = Greeting(friend = friend2, greeting = "Hello")
        val generator = mock<BirthdayGreetingGenerator> {
            on { generateGreeting(friend1, today) } doReturn greeting1
            on { generateGreeting(friend2, today) } doReturn greeting2
        }
        val greetingSender = mock<GreetingSender>()
        val testee = BirthdayGreetingSender(friendReader, greetingSender, generator)

        // act
        testee.sendGreetingsToFriendsWithBirthdayToday()

        // assert
        verify(greetingSender).sendGreeting(greeting1)
        verify(greetingSender).sendGreeting(greeting2)
        verifyNoMoreInteractions(greetingSender)
    }

    private fun createFriend(firstName: String, dateOfBirth: LocalDate) = Friend(
        firstName = firstName,
        lastName = "Doe",
        dateOfBirth = dateOfBirth,
        email = "jon@doe.com"
    )
}