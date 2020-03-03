package net.sokum.base.ui

import androidx.fragment.app.Fragment
import net.sokum.base.di.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
}

