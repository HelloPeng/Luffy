package com.pansoft.kotlintest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.tabs.TabLayout

class MainActivity : Activity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/**/        val testView: TextView = findViewById(R.id.tv_test1) as TextView
        testView.setOnClickListener {

        }
        val button: Button = findViewById(R.id.btn_test2) as Button
        button.setOnClickListener {
            val start = 1
            var count = 3 + start
            count += 3
            Log.i("MainActivity", "onCreate: $count")
        /* */startActivity(Intent().apply {
                setClass(this@MainActivity, MainActivity2::class.java)
            })
        }
        val cbx: CheckBox = findViewById(R.id.cbx) as CheckBox
        cbx.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.i(TAG, "CBX = setOnCheckedChangeListener: ")
        }
        val group: RadioGroup = findViewById(R.id.radio_group) as RadioGroup
        group.setOnCheckedChangeListener { group, checkedId ->
            Log.i(TAG, "RDO = setOnCheckedChangeListener: ")
        }/**/
        val tablayout :TabLayout = findViewById(R.id.table_layout) as TabLayout
        tablayout.addTab(TabLayout.Tab().setText("测试文字1"))
        tablayout.addTab(TabLayout.Tab().setText("测试文字2"))
        tablayout.addTab(TabLayout.Tab().setText("测试文字3"))
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.i(TAG, "onTabSelected: ")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.i(TAG, "onTabUnselected: ")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.i(TAG, "onTabReselected: ")
            }
        })
    }
}