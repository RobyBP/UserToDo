package com.robybp.usertodo.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.robybp.usertodo.R
import com.robybp.usertodo.models.view_model.MainViewModel
import com.robybp.usertodo.recyclerview.MainRecyclerViewAdapter

private lateinit var recyclerView: RecyclerView
private lateinit var fab: FloatingActionButton
private lateinit var adapter : MainRecyclerViewAdapter
private lateinit var sharedViewModel: MainViewModel
private lateinit var mainToolbar: Toolbar


class MainFragment() : Fragment(), MainRecyclerViewAdapter.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.main_recyclerview)
        fab = view.findViewById(R.id.fab)
        adapter = MainRecyclerViewAdapter(this)
        mainToolbar = view.findViewById(R.id.main_toolbar)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as AppCompatActivity).setSupportActionBar(mainToolbar)

        sharedViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            fragmentManager!!.beginTransaction().apply {
                replace(R.id.fl_activity_main, NewTaskFragment())
                commit()
            }
        }

        Log.d("id", "Main fragment id : ${sharedViewModel.getUserId()}")

        sharedViewModel.getAllTasksById().observe(this, Observer {tasks ->
            tasks.let { adapter.setTasks(it) }
        })



    }

    override fun onItemClicked(position: Int) {
        val task = sharedViewModel.getAllTasksById().value!![position]
        sharedViewModel.setTask(task)
        fragmentManager!!.beginTransaction().apply{
            replace(R.id.fl_activity_main, TaskDetailFragment())
            commit()
        }
    }

    override fun onItemChecked(position: Int) {
        val task = sharedViewModel.getAllTasksById().value!![position]
        task.taskPriority = 0
        sharedViewModel.updateTask(task)
    }

    override fun onTrashCanClicked(position: Int) {
        val task = sharedViewModel.getAllTasksById().value!![position]
        sharedViewModel.deleteTask(task)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_btn -> fragmentManager!!.beginTransaction().apply { replace(R.id.fl_activity_main, LogInFragment())
            commit()}
        }
        return super.onOptionsItemSelected(item)
    }
}