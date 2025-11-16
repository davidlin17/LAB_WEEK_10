package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10.Total
import com.example.lab_week_10.TotalDatabase
import com.example.lab_week_10.TotalObject
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        ).allowMainThreadQueries()
            .build()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initValueFromDb()
    }

    override fun onStart() {
        super.onStart()

        val data = db.totalDao().getTotal(ID)
        if (data != null) {
            Toast.makeText(this, "Last updated: ${data.total.date}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()

        val newTotal = Total(
            id = ID,
            total = TotalObject(
                value = viewModel.total.value ?: 0,
                date = Date().toString()
            )
        )

        db.totalDao().update(newTotal)
    }

    private fun initValueFromDb() {
        val data = db.totalDao().getTotal(ID)

        if (data == null) {
            db.totalDao().insert(
                Total(
                    id = ID,
                    total = TotalObject(0, Date().toString())
                )
            )
            viewModel.setTotal(0)
        } else {
            viewModel.setTotal(data.total.value)
        }
    }

    companion object {
        const val ID: Long = 1
    }
}
