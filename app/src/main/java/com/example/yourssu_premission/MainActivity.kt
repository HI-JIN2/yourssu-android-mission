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

        binding.bnvMain.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.action_add -> {
                        AddFragment()
                    }
                    R.id.action_list -> {
                        ListFragment()
                    }
                    else -> {
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