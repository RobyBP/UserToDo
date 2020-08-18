package com.robybp.usertodo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.robybp.usertodo.R

class ViewPagerAdapter(val images : List<Int>, val clickListener : OnClickListener) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image_items)
        val okButton: Button = itemView.findViewById(R.id.exit_viewpager_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = images[position]
        holder.imageView.setImageResource(currentImage)
        if (currentImage == R.drawable.page_three){
            holder.okButton.visibility = View.VISIBLE
        }

        holder.okButton.setOnClickListener {
            clickListener.onOkClicked()
        }
    }

    interface OnClickListener{
        fun onOkClicked()
    }
}