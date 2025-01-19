package com.mobile.studentsapp.model

data class Student(
    val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean
)