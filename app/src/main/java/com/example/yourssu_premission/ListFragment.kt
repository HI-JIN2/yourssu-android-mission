package com.example.yourssu_premission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourssu_premission.databinding.FragmentListBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ListFragment : Fragment() {

    var firestore: FirebaseFirestore? = null

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding) { "FragmentListBinding" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        binding.rvList.adapter = ListAdapter()
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        return binding.root
    }

    inner class ListAdapter :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        var telephoneBook: ArrayList<User> = arrayListOf()

        init {
            firestore?.collection("telephoneBook")?.orderBy("timestamp", Query.Direction.DESCENDING)
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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name = itemView.findViewById<TextView>(R.id.tv_name)
            val number = itemView.findViewById<TextView>(R.id.tv_number)
        }
        //여기서 조금 헤맸음. 데이터바인딩의 방식을 사용하지 않음
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.name.text = telephoneBook[position].name
            holder.number.text = telephoneBook[position].phoneNumber
        }

        override fun getItemCount(): Int {
            return telephoneBook.size
        }
    }
}
