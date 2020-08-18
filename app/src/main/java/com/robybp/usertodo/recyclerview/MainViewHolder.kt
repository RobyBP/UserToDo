package com.robybp.usertodo.recyclerview

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robybp.usertodo.R

class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    val taskCompleteCheckBox: CheckBox = itemView.findViewById(R.id.task_complete_cb)
    val taskName: TextView = itemView.findViewById(R.id.task_name)
    val deleteTaskButton: ImageButton = itemView.findViewById(R.id.delete_task_btn)
    val checkMark : ImageView = itemView.findViewById(R.id.check_mark)
}