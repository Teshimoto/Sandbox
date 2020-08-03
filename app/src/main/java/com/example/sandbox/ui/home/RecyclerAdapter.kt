package com.example.sandbox.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox.model.database.Person

private const val TAG = "RecyclerAdapter"

class RecyclerAdapter(
    private var data: List<Person>
) : RecyclerView.Adapter<RecyclerAdapter.MyVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(
            LayoutInflater
                .from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<Person>) {
        Log.d(TAG, "setData: ${data}")
        this.data = data
        notifyDataSetChanged()
    }

    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(person: Person) {
            text.text = person.toString()
        }
    }
}