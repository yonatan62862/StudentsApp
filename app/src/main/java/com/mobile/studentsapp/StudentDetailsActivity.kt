package com.mobile.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobile.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    companion object {
        const val EDIT_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentName: TextView = findViewById(R.id.name_text_view)
        val studentId: TextView = findViewById(R.id.id_text_view)
        val studentNumber: TextView = findViewById(R.id.number_text_view)
        val studentAddress: TextView = findViewById(R.id.address_text_view)
        val editButton: Button = findViewById(R.id.edit_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)
        val deleteButton: Button = findViewById(R.id.delete_button2)
        val checkBox: CheckBox = findViewById(R.id.details_check_box)

        val studentIdString = intent.getStringExtra("id")
        val student = Model.shared.students.find { it.id == studentIdString }

        studentName.text = student?.name
        studentId.text = student?.id
        studentNumber.text = student?.phone
        studentAddress.text = student?.address
        checkBox.isChecked = student?.isChecked ?: false

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
        }

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_name", studentName.text.toString())
            intent.putExtra("student_id", studentId.text.toString())
            intent.putExtra("student_phone", studentNumber.text.toString())
            intent.putExtra("student_address", studentAddress.text.toString())
            intent.putExtra("student_isChecked", checkBox.isChecked)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        }

        deleteButton.setOnClickListener {
            if (student != null) {
                Model.shared.students.remove(student)

                val resultIntent = Intent()
                resultIntent.putExtra("deleted_student_id", student.id)
                setResult(RESULT_OK, resultIntent)

                finish()
            }
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedName = data?.getStringExtra("updated_name")
            val updatedId = data?.getStringExtra("updated_id")
            val updatedPhone = data?.getStringExtra("updated_phone")
            val updatedAddress = data?.getStringExtra("updated_address")
            val updatedIsChecked = data?.getBooleanExtra("updated_isChecked", false)

            val student = Model.shared.students.find { it.id == intent.getStringExtra("id") }
            if (student != null) {
                student.name = updatedName ?: student.name
                student.id = updatedId ?: student.id
                student.phone = updatedPhone ?: student.phone
                student.address = updatedAddress ?: student.address
                student.isChecked = updatedIsChecked ?: student.isChecked
            }

            findViewById<TextView>(R.id.name_text_view).text = updatedName
            findViewById<TextView>(R.id.id_text_view).text = updatedId
            findViewById<TextView>(R.id.number_text_view).text = updatedPhone
            findViewById<TextView>(R.id.address_text_view).text = updatedAddress
            findViewById<CheckBox>(R.id.details_check_box).isChecked = updatedIsChecked ?: false
        }
    }
}