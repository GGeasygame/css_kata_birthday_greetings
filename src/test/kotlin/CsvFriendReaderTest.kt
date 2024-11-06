import org.example.CsvFriendReader
import org.example.domain.Friend
import org.junit.jupiter.api.Assertions.*
import java.nio.file.Path
import java.time.LocalDate
import kotlin.test.Test

class CsvFriendReaderTest {
    @Test
    fun `When csv only has header then return no friends`() {
        // arrange
        val path = Path.of("src/test/resources/only_header.csv")

        // act
        val friends = CsvFriendReader(path).readFriends()

        // assert
        kotlin.test.assertTrue { friends.isEmpty() }
    }

    @Test
    fun `When csv has friends then return friends`() {
        // arrange
        val path = Path.of("src/test/resources/with_friends.csv")
        val expected = listOf(
            Friend("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
            Friend("Mary", "Ann", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com")
        )

        // act
        val friends = CsvFriendReader(path).readFriends()

        // assert
        assertEquals(expected, friends)
    }
}