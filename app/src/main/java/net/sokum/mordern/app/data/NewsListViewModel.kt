package net.sokum.mordern.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewsListViewModel @Inject constructor(repository: NewsListRepository) : ViewModel() {
    var country : String = "kr"

    val headLines : LiveData<TopHeadLines> = repository.getHeadLines()
}