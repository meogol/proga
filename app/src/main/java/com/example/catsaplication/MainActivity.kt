package com.example.catsaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.catsaplication.Presenter.PresenterClass.PresenterMain
import com.example.catsaplication.Presenter.PresenterInterface.Interfaces

class MainActivity : AppCompatActivity(), Interfaces.InterfaceMain  {

    private lateinit var bCategories: Button
    private lateinit var presenter: PresenterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bCategories = findViewById(R.id.bCategories)
        presenter = PresenterMain(this)

        bCategories.setOnClickListener {
            presenter.loadView()

        }
    }

    override fun showActivity() {
        startActivity(Intent(this, CategoriesActivity::class.java))
    }
}
