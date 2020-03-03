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
import net.sokum.mordern.app.ui.main.UserListAdapter
import javax.inject.Inject

class LocalUsersFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    lateinit var userViewModel : LocalUsersViewModel

    lateinit var actionViewModel : UserActionViewModel

    lateinit var adatper : UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel.result.observe(this, Observer {
            adatper.submitList(it)
        })

        actionViewModel.searchKeyword.observe(this, Observer {
            userViewModel.searchUser(it)
        })

        actionViewModel.uxEvent.observe(this, Observer {
            if ( it.type === UserActionViewModel.EVENT_LIKE_CHANGE ) {
                userViewModel.searchUser(actionViewModel.searchKeyword.value ?: "")
            }
        })

        adatper =
            UserListAdapter(
                context!!,
                actionViewModel
            )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adatper

        userViewModel.searchUser("")
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        userViewModel = ViewModelProviders.of(this, viewModelFactory)[LocalUsersViewModel::class.java]
        actionViewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UserActionViewModel::class.java]
    }
}