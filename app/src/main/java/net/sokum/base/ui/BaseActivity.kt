package net.sokum.base.ui

import android.app.Activity
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import net.sokum.base.di.ViewModelFactory
import net.sokum.modern.app.ui.main.UserActionViewModel
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
}