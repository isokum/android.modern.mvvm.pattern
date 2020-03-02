package net.sokum.mordern.app.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import net.sokum.mordern.app.R
import net.sokum.mordern.app.base.di.ViewModelFactory
import net.sokum.mordern.app.base.helper.hideKeyboard
import net.sokum.mordern.app.base.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    lateinit var fragmentAdapter : MainTabFragmentAdapter

    lateinit var actionViewModel : UserActionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionViewModel = ViewModelProviders.of(this, viewModelFactory)[UserActionViewModel::class.java]

        setupUI()
    }

    private fun setupUI() {
        fragmentAdapter =
            MainTabFragmentAdapter(
                supportFragmentManager
            )
        viewPager.adapter = fragmentAdapter

        tabLayout.addTab(tabLayout.newTab().setText("API"))
        tabLayout.addTab(tabLayout.newTab().setText("Local"))
        tabLayout.setupWithViewPager(viewPager)

        searchBtn.setOnClickListener {
            doSearch(searchText.text.toString())
        }
        searchText.setOnEditorActionListener { textView, i, keyEvent ->
            if ( i == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(searchText.text.toString())
            }
            true
        }
    }

    private fun doSearch(keyword : String) {
        actionViewModel.doSearch(keyword)
        searchText.hideKeyboard()
    }
}