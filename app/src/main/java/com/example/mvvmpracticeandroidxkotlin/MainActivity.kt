package com.example.mvvmpracticeandroidxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java);
        viewModel.init()
        setOnClickListener()
        setObserver()


    }

    private fun setObserver() {
        viewModel.seconds().observe(this, Observer {
            txvNum.text = it.toString().format(4)
        })
        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setOnClickListener() {

        btnStart.setOnClickListener() {
            if (edtNum.text.isEmpty()) {
                Toast.makeText(this, "Invalid Num", Toast.LENGTH_SHORT)
            } else {
                viewModel.timerValue.value = edtNum.text.toString().toLong()*1000
                viewModel.startTimer()
            }
        }
        btnStop.setOnClickListener() {
            txvNum.text = "0"
            viewModel.stopTimer()
        }
    }
}