package com.example.catsaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catsaplication.Networking.Question
import com.example.catsaplication.Presenter.PresenterClass.PresenterGame
import com.example.catsaplication.Presenter.PresenterClass.PresenterMain
import com.example.catsaplication.Presenter.PresenterInterface.Interfaces



class GameActivity() : AppCompatActivity(), Interfaces.InterfaceGame {

    override lateinit var tvQuestion: TextView
    override lateinit var bResult: Button
    override lateinit var etvAnswer: EditText
    override lateinit var bSkip: Button
    override lateinit var bLast: Button
    override var id: Int = 0
    private lateinit var presenterGame: PresenterGame


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        id = intent.getIntExtra("ID", -1)

        tvQuestion = findViewById(R.id.tvQuestion)
        etvAnswer = findViewById(R.id.etAnswer)
        bResult = findViewById(R.id.bOK)
        bSkip = findViewById(R.id.bSkipQuestion)
        bLast = findViewById(R.id.bLastQuestion)

        presenterGame = PresenterGame(this)
        presenterGame.importQuestion()

        bResult.setOnClickListener {
            presenterGame.checkAnswer()
        }
        bSkip.setOnClickListener {
            presenterGame.nextQuestion(true)
        }

        bLast.setOnClickListener {
            presenterGame.nextQuestion(false)
        }
    }

    override fun toastMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showActivity() {
        onBackPressed()
    }

}
