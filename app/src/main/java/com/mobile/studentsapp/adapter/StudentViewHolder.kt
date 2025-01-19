package com.mobile.studentsapp.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.studentsapp.OnItemClickListener
import com.mobile.studentsapp.R
import com.mobile.studentsapp.model.Student

class StudentViewHolder(
    itemView: View,
    listener: OnItemClickListener?
): RecyclerView.ViewHolder(itemView) {

    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var checkBox: CheckBox? = null
    private var student: Student? = null
    private var numberTextView: TextView? = null

    init {
        nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
        idTextView = itemView.findViewById(R.id.student_row_id_text_view)
        checkBox = itemView.findViewById(R.id.student_row_check_box)
        numberTextView = itemView.findViewById(R.id.student_row_number_text_view)

        checkBox?.apply {
            setOnClickListener { view ->
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.setOnClickListener {
            listener?.onItemClick(adapterPosition)
            listener?.onItemClick(student)
        }
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id
        numberTextView?.text = student?.phone

        checkBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position

            setOnCheckedChangeListener { _, isChecked ->
                student?.isChecked = isChecked
            }
        }
    }


}