package net.sokum.mordern.app.ui.main.local

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import net.sokum.mordern.app.R
import net.sokum.base.di.ViewModelFactory
import net.sokum.base.ui.BaseFragment
import net.sokum.mordern.app.ui.main.UserActionViewModel
import net.sokum.mordern.app.ui.main.remote.UserListAdapter
import javax.inject.Inject

class LocalUsersFragment : BaseFragment {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    private lateinit var userViewModel : LocalUsersViewModel

    lateinit var actionViewModel : UserActionViewModel

    lateinit var adatper : UserListAdapter

    constructor() : super()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel.users.observe(this, Observer {
            adatper.submitList(it)
        })

        adatper =
            LocalUserListAdapter(
                context!!,
                actionViewModel
            )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adatper
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        userViewModel = ViewModelProviders.of(this, viewModelFactory)[LocalUsersViewModel::class.java]
        actionViewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UserActionViewModel::class.java]
    }
}