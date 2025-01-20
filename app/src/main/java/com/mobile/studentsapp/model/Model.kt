package com.mobile.studentsapp.model

class Model private constructor() {

    val students: MutableList<Student> = mutableListOf()

    init {
        for (i in 1..10) {
            students.add(
                Student(
                    name = "Student $i",
                    id = "20922233$i",
                    phone = "054-123456$i",
                    address = "Address $i",
                    avatarUrl = "",
                    isChecked = false
                )
            )
        }
    }

    companion object {
        val shared: Model by lazy { Model() }
        val instance: Model
            get() = shared
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }
}
