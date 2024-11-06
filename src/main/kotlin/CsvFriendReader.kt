package org.example

import org.example.domain.Friend
import java.nio.file.Path
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.readLines

class CsvFriendReader(private val filePath : Path) {
    fun readFriends() : List<Friend> {
        return filePath.readLines()
            .drop(1)
            .map(this::parseFriend)
    }

    private fun parseFriend(line: String) : Friend {
        val fields = line.split(", ")
        return Friend(
            lastName = fields[0],
            firstName = fields[1],
            dateOfBirth = parseDate(fields[2]),
            email = fields[3]
        )
    }

    private fun parseDate(date: String): LocalDate {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
    }
}