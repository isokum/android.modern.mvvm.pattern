package net.sokum.modern.app.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.SparseArray
import android.view.inputmethod.EditorInfo
import androidx.core.util.set
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import net.sokum.modern.app.R
import net.sokum.base.helper.hideKeyboard
import net.sokum.base.di.viewModelProvider
import net.sokum.base.ui.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var adapter : MainTabAdapter

    private lateinit var actionViewModel : UserActionViewModel
    private lateinit var keywordList : SparseArray<String>
    private var selectTab : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionViewModel = viewModelProvider()

        setupUI()
    }

    private fun setupUI() {
        adapter =
            MainTabAdapter(
                supportFragmentManager
            )
        viewPager.adapter = adapter

        keywordList = SparseArray(adapter.count)

        tabLayout.addTab(tabLayout.newTab().setText("API"))
        tabLayout.addTab(tabLayout.newTab().setText("Local"))
        tabLayout.setupWithViewPager(viewPager)

        searchBtn.setOnClickListener {
            doSearch(searchText.text.toString())
        }
        searchText.setOnEditorActionListener { _, i, _ ->
            if ( i == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(searchText.text.toString())
            }
            true
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                var keyword = keywordList[position] ?: ""

                searchText.setText(keyword)
                searchText.setSelection(keyword.length)

                selectTab = position
            }
        })
    }

    private fun doSearch(keyword : String) {
        keywordList[selectTab] = keyword

        actionViewModel.doSearch(selectTab, keyword)
        searchText.hideKeyboard()
    }
}