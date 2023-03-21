package com.example.yourssu_premission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yourssu_premission.databinding.ItemListBinding
import com.google.firebase.firestore.FirebaseFirestore


class ListAdapter(private val dataList: ArrayList<User>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvName.text = dataList[position].name
            binding.tvNumber.text = dataList[position].phoneNumber
        }
    }
    var firestore : FirebaseFirestore? = null
    var telephoneBook : ArrayList<User> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {}
    }

    override fun getItemCount(): Int = dataList.size

    init {  // telephoneBook의 문서를 불러온 뒤 Person으로 변환해 ArrayList에 담음
        firestore?.collection("telephoneBook")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                telephoneBook.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(User::class.java)
                    telephoneBook.add(item!!)
                }
                notifyDataSetChanged()
            }
    }
}