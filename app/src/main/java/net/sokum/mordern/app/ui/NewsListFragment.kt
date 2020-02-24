package net.sokum.mordern.app.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import net.sokum.mordern.app.App
import net.sokum.mordern.app.R
import net.sokum.mordern.app.data.NewsListViewModel
import net.sokum.mordern.app.data.TopHeadLines
import javax.inject.Inject

class NewsListFragment : Fragment() {
    @Inject
    lateinit var viewModel : NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_list_fragment, container)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loadData()
    }

    private fun loadData() {
        viewModel.headLines.observe(this, Observer {
            showHeadLines(it)
        })
    }

    private fun showHeadLines(headLines: TopHeadLines) {
        Log.d("SOKUM", "headLine items ="+headLines.totalResults)
    }
}