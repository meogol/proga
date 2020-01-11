package com.example.catsaplication.Networking

data class Question(val id: Int,
                   val question: String,
                   val answer: String)

data class Clues(val id: Int,
                 val clues: MutableList<Question>)

data class Category(val id: Int,
                    val title:
                    String)