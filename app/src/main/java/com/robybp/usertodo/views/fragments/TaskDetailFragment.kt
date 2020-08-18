package com.robybp.usertodo.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.robybp.usertodo.R
import com.robybp.usertodo.models.view_model.MainViewModel


class TaskDetailFragment : Fragment() {

    private lateinit var sharedViewModel : MainViewModel

    private lateinit var taskName : TextView
    private lateinit var taskDescription : TextView
    private lateinit var taskPriority : TextView
    private lateinit var okButton : Button
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)
        taskName = view.findViewById(R.id.task_name_tv)
        taskDescription = view.findViewById(R.id.task_description_tv)
        taskPriority = view.findViewById(R.id.task_Priority_tv)
        okButton = view.findViewById(R.id.ok_button)
        sharedViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(MainViewModel::class.java)
        toolbar = view.findViewById(R.id.main_toolbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = sharedViewModel.getTask()
        taskName.text = task.taskName
        taskDescription.text = task.taskDescription
        taskPriority.text = task.taskPriority.toString()

        okButton.setOnClickListener {
            fragmentManager!!.beginTransaction().apply {
                replace(R.id.fl_activity_main, MainFragment())
                commit()
            }
        }
    }
}