package com.example.catsaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catsaplication.Presenter.PresenterClass.PresenterCategories
import com.example.catsaplication.Presenter.PresenterClass.PresenterMain
import com.example.catsaplication.Presenter.PresenterInterface.Interfaces
import com.example.catsaplication.adapters.CategoriesAdapter

class CategoriesActivity : AppCompatActivity(), Interfaces.InterfaceCategories {

    private lateinit var bStartGame: Button
    lateinit var presenter: PresenterCategories
    override lateinit var rvCategories: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        rvCategories = findViewById(R.id.rvCategories)
        presenter = PresenterCategories(this)

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.setHasFixedSize(true)

        presenter.addAdapter()
    }

    override fun showActivity(id: Int) {
        val intent = Intent(this, GameActivity().javaClass)
        intent.putExtra("ID", id)

        startActivity(intent)
    }

    override fun showActivity(){
        onBackPressed()
        Toast.makeText(this, "no Internet", Toast.LENGTH_SHORT).show()

    }
}

