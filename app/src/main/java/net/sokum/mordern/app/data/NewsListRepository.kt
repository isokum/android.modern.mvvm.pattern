package net.sokum.mordern.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.sokum.mordern.app.api.NewsApiService
import javax.inject.Inject

class NewsListRepository @Inject constructor(
    private val remoteService : NewsApiService
) {
    fun getHeadLines() : LiveData<TopHeadLines> {
        var results = MutableLiveData<TopHeadLines>()

        GlobalScope.launch(Dispatchers.Main) {
            val response = async(Dispatchers.IO) {
                remoteService.topHeadLines("kr")
            }.await()

            results.value = response.body()
        }

        return results
    }

}