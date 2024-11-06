package org.example

import org.example.domain.Friend

interface FriendReader {
    fun readFriends() : List<Friend>
}