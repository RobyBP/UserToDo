package com.robybp.usertodo.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.robybp.usertodo.R
import com.robybp.usertodo.models.data_models.Task
import com.robybp.usertodo.models.view_model.MainViewModel


class NewTaskFragment() : Fragment() {


    private lateinit var taskNameEditText: EditText
    private lateinit var taskDescriptionEditText: EditText
    private lateinit var taskPriorityEditText: EditText
    private lateinit var saveTaskButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_task, container, false)
        taskNameEditText = view.findViewById(R.id.task_name_et)
        taskDescriptionEditText = view.findViewById(R.id.task_description_et)
        taskPriorityEditText = view.findViewById(R.id.task_priority_et)
        saveTaskButton = view.findViewById(R.id.save_task_button)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(MainViewModel::class.java)

        saveTaskButton.setOnClickListener {

            if(TextUtils.isEmpty(taskNameEditText.text) || TextUtils.isEmpty(taskDescriptionEditText.text) || TextUtils.isEmpty(taskPriorityEditText.text)){
                Toast.makeText(requireContext(), "No empty fields dummy", Toast.LENGTH_LONG).show()
            }else{
                val taskName : String = taskNameEditText.text.toString()
                val taskDescription : String = taskDescriptionEditText.text.toString()
                val taskPriority : Int = taskPriorityEditText.text.toString().toInt()

                val userId : Int = sharedViewModel.getUserId()

                sharedViewModel.setUserId(userId)

                Log.d("id", "New task fragment id : $userId")
                val task = Task(0, userId, taskName, taskDescription, taskPriority)

                Log.d("id", "$taskName id is $userId")

                sharedViewModel.saveTask(task)



                fragmentManager!!.beginTransaction().apply {
                    replace(R.id.fl_activity_main, MainFragment())
                    commit()
                }


            }

        }

    }
}