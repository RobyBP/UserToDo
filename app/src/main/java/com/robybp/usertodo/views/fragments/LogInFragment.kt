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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.robybp.usertodo.R
import com.robybp.usertodo.models.data_models.User
import com.robybp.usertodo.models.view_model.MainViewModel
import com.robybp.usertodo.utils.IdMaker


class LogInFragment : Fragment() {

    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var logInButton: Button
    private lateinit var sharedViewModel: MainViewModel

    private lateinit var listOfAllUsers: List<User>
    private var listOfAllUserNames = emptyList<String>().toMutableList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        userNameEditText = view.findViewById(R.id.username_et)
        passwordEditText = view.findViewById(R.id.password_et)
        signUpButton = view.findViewById(R.id.sign_up_button)
        logInButton = view.findViewById(R.id.log_in_button)

        sharedViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(MainViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.allUsers.observe(this, Observer {users ->
            users.let { listOfAllUsers = it }
            for (user in listOfAllUsers){
                listOfAllUserNames.add(user.userName)
            }
        })

        signUpButton.setOnClickListener {

            if (TextUtils.isEmpty(userNameEditText.text) || TextUtils.isEmpty(passwordEditText.text)) {
                Toast.makeText(requireContext(), "No empty fields dummy", Toast.LENGTH_LONG).show()
            } else {
                val userName: String = userNameEditText.text.toString()
                val password: String = passwordEditText.text.toString()
                val id: Int = IdMaker.generateId(userName)
                Log.d("id", "$userName id is $id")
                val user = User(id, userName, password)

                if(listOfAllUserNames.contains(userName)){
                    Toast.makeText(requireContext(), "The user already exists", Toast.LENGTH_LONG).show()
                }else{

                    sharedViewModel.setUserId(id)

                    sharedViewModel.saveUser(user)

                    fragmentManager!!.beginTransaction().apply {
                        replace(R.id.fl_activity_main, ViewPagerFragment())
                        commit()
                    }
                }

            }
        }

        logInButton.setOnClickListener {
            val userName: String = userNameEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            val id: Int = IdMaker.generateId(userName)
            val user = User(id, userName, password)

            if(listOfAllUsers.contains(user)){

                sharedViewModel.setUserId(id)

                Log.d("id", "After log in the id is ${sharedViewModel.getUserId()}")

                Toast.makeText(requireContext(), "Welcome $userName", Toast.LENGTH_LONG).show()
                fragmentManager!!.beginTransaction().apply {
                    replace(R.id.fl_activity_main, MainFragment())
                    commit()
                }
            }else{
                Toast.makeText(requireContext(), "Wrong password or username", Toast.LENGTH_LONG).show()
            }
        }
    }
}
