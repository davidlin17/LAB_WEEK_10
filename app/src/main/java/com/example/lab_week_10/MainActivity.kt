package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10.database.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        ).allowMainThreadQueries().build()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeValue()
    }

    override fun onStart() {
        super.onStart()

        val data = db.totalDao().getTotal()
        if (data != null) {
            Toast.makeText(this, "Last updated: ${data.total.date}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()

        val updated = Total(
            id = 1,
            total = TotalObject(
                value = viewModel.total.value ?: 0,
                date = Date().toString()
            )
        )

        db.totalDao().update(updated)
    }

    private fun initializeValue() {
        val data = db.totalDao().getTotal()

        if (data == null) {
            val new = Total(
                id = 1,
                total = TotalObject(
                    value = 0,
                    date = Date().toString()
                )
            )
            db.totalDao().insert(new)
            viewModel.setTotal(0)
        } else {
            viewModel.setTotal(data.total.value)
        }
    }
}
