package com.example.yourssu_premission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourssu_premission.databinding.FragmentListBinding
import com.example.yourssu_premission.databinding.ItemListBinding
import com.google.firebase.firestore.FirebaseFirestore

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

        //setContentView(R.layout.activity_main)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        binding.rvList.adapter = ListAdapter()
        binding.rvList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    inner class ListAdapter :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        // Person 클래스 ArrayList 생성성
        var telephoneBook: ArrayList<User> = arrayListOf()

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

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding =
                ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(viewBinding)
        }

        inner class ViewHolder(private val binding: ItemListBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(position: Int) {
                binding.tvName.text = telephoneBook[position].name
                binding.tvNumber.text = telephoneBook[position].phoneNumber
            }
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return telephoneBook.size
        }

    }

}
