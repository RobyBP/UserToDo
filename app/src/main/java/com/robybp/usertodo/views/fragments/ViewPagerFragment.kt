package com.robybp.usertodo.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.robybp.usertodo.R
import com.robybp.usertodo.utils.ViewPagerAdapter


class ViewPagerFragment : Fragment(), ViewPagerAdapter.OnClickListener {

    private lateinit var images : List<Int>
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewPager = view.findViewById(R.id.pager)
        images = listOf(R.drawable.page_one, R.drawable.page_two, R.drawable.page_three)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(images, this)
        viewPager.adapter = adapter
    }

    override fun onOkClicked() {
        fragmentManager!!.beginTransaction().apply {
            replace(R.id.fl_activity_main, MainFragment()).commit()
        }
    }


}