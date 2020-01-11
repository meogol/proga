package com.example.catsaplication.Presenter.PresenterClass

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.catsaplication.CategoriesActivity
import com.example.catsaplication.Networking.Category
import com.example.catsaplication.Networking.Clues
import com.example.catsaplication.Networking.Question
import com.example.catsaplication.Networking.RetrofitFactory
import com.example.catsaplication.Presenter.PresenterInterface.Interfaces
import com.example.catsaplication.adapters.CategoriesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.random.Random

class PresenterGame(view: Context): Presenter(view){
    override val mView = (view as  Interfaces.InterfaceGame)
    private lateinit var clues : Clues
    private var id: Int = 0

    private var count: Int = 0
    private var trueAnswer = 0
    private var minTrueAnswer : Int = 0


    fun importQuestion(){

        if (!isOnline())
        {
            mView.showActivity()
            mView.toastMessage("no Internet")
            return
        }

        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getQuestion(mView.id.toString())
            try {
                withContext(Dispatchers.Main) {
                    if (!response.isSuccessful || response.body() == null) {
                        return@withContext
                    }

                    try {
                        clues=response.body()!!
                        minTrueAnswer = clues.clues.size - clues.clues.size/3
                        nextQuestion(false)

                    } catch (err: HttpException) {
                        Log.d(ContentValues.TAG, err.message!!)
                    }


                }
            } catch (err: HttpException) {
                Log.d(ContentValues.TAG, err.message())
            }
        }
    }



    fun checkAnswer(){
        val answer = mView.etvAnswer.text.toString()

        val userAnswer = answer.toLowerCase().trim()
        val result = clues.clues[count].answer.toLowerCase().trim()
        if (userAnswer == result)
            {
                nextQuestion(true)
                trueAnswer++
            }
        else
            mView.toastMessage("Wrong answer")


        if(trueAnswer>=minTrueAnswer)
        {
            mView.toastMessage("Nice!")
            mView.showActivity()
        }
    }

    fun nextQuestion(nextOrLast: Boolean) {

        if (nextOrLast) {
            if (count + 1 < clues.clues.size)
                count++
            else
                mView.toastMessage("answer previous questions")
        } else {
            if (count - 1 > -1)
                count--
            else
                mView.toastMessage("can not be returned back")
        }

        mView.tvQuestion.text = clues.clues[count].question

    }


}