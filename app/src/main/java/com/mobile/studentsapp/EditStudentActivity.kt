package com.mobile.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.mobile.studentsapp.model.Model


class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentId = intent.getStringExtra("student_id")
        val studentName = intent.getStringExtra("student_name")
        val studentPhone = intent.getStringExtra("student_phone")
        val studentAddress = intent.getStringExtra("student_address")
        val studentIsChecked = intent.getBooleanExtra("student_isChecked", false)

        val nameEditText: TextInputEditText = findViewById(R.id.NameEditText)
        val idEditText: TextInputEditText = findViewById(R.id.IDEditText)
        val phoneEditText: TextInputEditText = findViewById(R.id.PhoneEditText)
        val addressEditText: TextInputEditText = findViewById(R.id.addressEditText)
        val checkBox: MaterialCheckBox = findViewById(R.id.checkBox)
        val updateButton: Button = findViewById(R.id.UpdateStudentBTN)
        val cancelButton: Button = findViewById(R.id.CancelBTN)

        nameEditText.setText(studentName)
        idEditText.setText(studentId)
        phoneEditText.setText(studentPhone)
        addressEditText.setText(studentAddress)
        checkBox.isChecked = studentIsChecked

        updateButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()
            val updatedPhone = phoneEditText.text.toString()
            val updatedAddress = addressEditText.text.toString()
            val isChecked = checkBox.isChecked

            if (updatedName.isEmpty() || updatedId.isEmpty() || updatedPhone.isEmpty() || updatedAddress.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = Model.shared.students.find { it.id == studentId }
            if (student != null) {
                student.name = updatedName
                student.id = updatedId
                student.phone = updatedPhone
                student.address = updatedAddress
                student.isChecked = isChecked
            }

            val resultIntent = Intent()
            resultIntent.putExtra("updated_name", updatedName)
            resultIntent.putExtra("updated_id", updatedId)
            resultIntent.putExtra("updated_phone", updatedPhone)
            resultIntent.putExtra("updated_address", updatedAddress)
            resultIntent.putExtra("updated_isChecked", isChecked)
            setResult(RESULT_OK, resultIntent)

            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}