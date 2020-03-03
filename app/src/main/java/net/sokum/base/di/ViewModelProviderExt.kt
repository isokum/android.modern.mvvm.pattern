package net.sokum.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import net.sokum.base.ui.BaseActivity
import net.sokum.base.ui.BaseFragment

inline fun <reified VM : ViewModel> BaseActivity.viewModelProvider()
        = ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

inline fun <reified VM : ViewModel> BaseFragment.viewModelProvider()
        = ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

inline fun <reified VM : ViewModel> BaseFragment.activityViewModelProvider()
        = ViewModelProviders.of(requireActivity(), viewModelFactory).get(VM::class.java)