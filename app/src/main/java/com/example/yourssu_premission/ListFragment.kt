package com.example.yourssu_premission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourssu_premission.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding) { "FragmentListBinding" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val phoneList : ArrayList<ListData> = arrayListOf()

        phoneList.apply {
            add(ListData("유진","010-5573-6514"))
        }

        val listAdapter = ListAdapter(phoneList)
        binding.rvList.adapter=listAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvList.layoutManager=linearLayoutManager

        binding.rvList.setHasFixedSize(true)
        binding.rvList.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        return binding.root
    }
}