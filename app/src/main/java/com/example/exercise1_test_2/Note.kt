package com.example.exercise1_test_2

import java.io.Serializable
//import java.time.LocalDateTime

 data class Note(
        var title: String,
        var content: String,
        var date: String
        //var date: String = LocalDateTime.now().toString()
) : Serializable
