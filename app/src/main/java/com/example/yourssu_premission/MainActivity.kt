package com.example.yourssu_premission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.yourssu_premission.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //supportFragmentManager.beginTransaction().add(R.id.fl_con, NaviHomeFragment()).commit()

        binding.bnvMain.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.action_add -> {
//                        bnv_main.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv1)
//                        bnv_main.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                        AddFragment()
                        // Respond to navigation item 1 click
                    }
                    R.id.action_list -> {
                        //bnv_main.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                        //bnv_main.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                        ListFragment()
                        // Respond to navigation item 2 click
                    }
                    else -> {
                   //     bnv_main.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                    //    bnv_main.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                        AddFragment()
                    }
                }
            )
            true
        }
        binding.bnvMain.selectedItemId = R.id.action_add
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main, fragment)
            .commit()
    }
}