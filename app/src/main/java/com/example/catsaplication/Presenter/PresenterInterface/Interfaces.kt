package com.example.catsaplication.Presenter.PresenterInterface

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface Interfaces {

    interface InterfaceMain{
        fun showActivity()
    }

    interface InterfaceCategories: InterfaceMain{
        var rvCategories: RecyclerView

        fun showActivity(id: Int)
    }

    interface InterfaceGame: InterfaceMain{
        var id: Int
        var tvQuestion: TextView
        var bResult: Button
        var etvAnswer: EditText
        var bSkip: Button
        var bLast: Button

        fun toastMessage(text: String)

    }

}