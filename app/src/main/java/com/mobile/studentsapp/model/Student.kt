package com.mobile.studentsapp.model


data class Student(
    var name: String,
    var id: String,
    var phone: String,
    val address: String,
    val avatarUrl: String,
    var isChecked: Boolean
)