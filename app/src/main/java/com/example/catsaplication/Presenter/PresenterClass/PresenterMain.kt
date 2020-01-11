package com.example.catsaplication.Presenter.PresenterClass

import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.catsaplication.Networking.Category
import com.example.catsaplication.Networking.RetrofitFactory
import com.example.catsaplication.Presenter.PresenterInterface.Interfaces
import com.example.catsaplication.adapters.CategoriesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class PresenterCategories(view: Context): Presenter(view){
    override val mView = (view as Interfaces.InterfaceCategories)

    fun loadView(id: Int){
        mView.showActivity(id)
    }


    fun addAdapter() {
        if (!isOnline())
            {
                mView.showActivity()
                return
            }

        val service = RetrofitFactory.makeRetrofitService()
        val categories: MutableList<Category> = arrayListOf()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getCategories()
            try {
                withContext(Dispatchers.Main) {
                    if (!response.isSuccessful || response.body() == null) {
                        return@withContext
                    }

                    try {
                        for (a in response.body()!!)
                            categories.add(a)

                        val adapter = CategoriesAdapter(categories)
                        (mView as Interfaces.InterfaceCategories).rvCategories.adapter = adapter

                    } catch (err: HttpException) {
                        Log.d(ContentValues.TAG, err.message!!)
                    }

                }
            } catch (err: HttpException) {
                Log.d(ContentValues.TAG, err.message())
            }
        }
    }

}

class PresenterMain(view: Context): Presenter(view) {

    fun loadView() {
        mView.showActivity()
    }
}

open class Presenter(view: Context){
    protected open val mView = (view as Interfaces.InterfaceMain)

    open fun isOnline(): Boolean {
        val cm =
            (mView as Context).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}
