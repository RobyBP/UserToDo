package com.robybp.usertodo.utils

import android.util.Log

class IdMaker() {

    companion object {

        fun generateId(username: String): Int {
            val nameCharacters = username.toCharArray()
            var id = 0
            for (letter in nameCharacters) {
                id = id * 10 + (Character.getNumericValue(letter))
                Log.d("id", id.toString())
            }
            return id
        }

    }

}