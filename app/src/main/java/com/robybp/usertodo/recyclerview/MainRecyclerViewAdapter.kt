package com.robybp.usertodo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robybp.usertodo.R
import com.robybp.usertodo.models.data_models.Task

class MainRecyclerViewAdapter(val clickListener : OnClickListener) : RecyclerView.Adapter<MainViewHolder>() {

    private var tasks = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.taskName.text = tasks[position].taskName



        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(position)
        }

        holder.deleteTaskButton.setOnClickListener {
            clickListener.onTrashCanClicked(position)
        }

        holder.taskCompleteCheckBox.setOnClickListener {

            if(holder.taskCompleteCheckBox.isChecked){
                clickListener.onItemChecked(position)
                holder.taskCompleteCheckBox.isChecked = false
            }
        }

        if(tasks[position].taskPriority == 0){
            holder.taskCompleteCheckBox.isChecked = true
            holder.checkMark.visibility = View.VISIBLE
        }

    }

    fun setTasks(tasks : List<Task>){
        this.tasks = tasks
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onItemClicked(position : Int)
        fun onItemChecked(position : Int)
        fun onTrashCanClicked(position : Int)
    }
}