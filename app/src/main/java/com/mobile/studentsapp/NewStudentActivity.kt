package com.mobile.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mobile.studentsapp.model.Model
import com.mobile.studentsapp.model.Student

class NewStudentActivity : AppCompatActivity() {

    private val repository = Model.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameEditText: EditText = findViewById(R.id.newStudentName)
        val idEditText: EditText = findViewById(R.id.newStudentId)
        val phoneEditText: EditText = findViewById(R.id.newStudentPhone)
        val addressEditText: EditText = findViewById(R.id.newStudentAddress)
        val addButton: Button = findViewById(R.id.addStudentButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                repository.addStudent(
                    Student(
                        name = name,
                        id = id,
                        avatarUrl =  "",
                        phone = phone,
                        address = address,
                        isChecked = false
                    )
                )
                finish()
            }
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}
