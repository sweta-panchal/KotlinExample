package com.example.kotlinexample.adapter

import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample.PageItem
import com.example.kotlinexample.R
import com.squareup.picasso.Picasso

class MyAdapter(private var arraypagelist: List<PageItem>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(com.example.kotlinexample.inflate(parent.context, R.layout.single_layout, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(arraypagelist[position], listener)
    }

    override fun getItemCount(): Int {
        return arraypagelist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pageItem: PageItem, listener: (Int) -> Unit) = with(itemView) {

            var title = itemView.findViewById(R.id.txt_title)as TextView
            var description = itemView.findViewById(R.id.txt_description)as TextView
            var image = itemView.findViewById(R.id.imv)as ImageView

            title.text = pageItem.email
            description.text = pageItem.first_name


            Picasso.get().load(pageItem.avatar).into(image);

            itemView.setOnClickListener{listener(adapterPosition)}
        }
    }

}