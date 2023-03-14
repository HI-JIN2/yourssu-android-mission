package com.example.yourssu_premission

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yourssu_premission.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = requireNotNull(_binding) { "FragmentAddBinding" }
    var name : String =""
    var phoneNumber : String=""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.etName.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //값 변경 시 실행되는 함수
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //입력값 담기
                name = binding.etName.text.toString() }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etNumber.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //값 변경 시 실행되는 함수
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //입력값 담기
                phoneNumber = binding.etName.text.toString() }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnAdd.setOnClickListener(){
            if(name.isEmpty() || phoneNumber.isEmpty())
                Toast.makeText(context, "이름과 전화번호를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            else{
                //등록과정....
                Toast.makeText(context, "등록되었습니다", Toast.LENGTH_SHORT).show()
//
//                val phoneList : ArrayList<ListData> = arrayListOf()
//
//                phoneList.apply {
//                    add(ListData(name,phoneNumber))
//                }
            }
        }

        return binding.root
    }
}