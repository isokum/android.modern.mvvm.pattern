package net.sokum.mordern.app.ui.main.remote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import net.sokum.mordern.app.R
import net.sokum.mordern.app.base.di.ViewModelFactory
import net.sokum.mordern.app.base.network.Resource
import net.sokum.mordern.app.base.ui.BaseFragment
import net.sokum.mordern.app.data.UserList
import net.sokum.mordern.app.ui.main.UserActionViewModel
import net.sokum.mordern.app.ui.main.UserListAdapter
import javax.inject.Inject

class RemoteUsersFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    lateinit var userViewModel: RemoteUsersViewModel

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

        adatper = UserListAdapter(
            context!!,
            actionViewModel
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adatper

        actionViewModel.uxEvent.observe(this, Observer {
            if ( it.type === UserActionViewModel.EVENT_LIKE_CHANGE) {
                adatper.notifyDataSetChanged()
            }
        })

        actionViewModel.searchKeyword.observe(this, Observer { keyword ->
            if ( keyword.isEmpty() ) {
                adatper.submitList(mutableListOf())
            } else {
                userViewModel.searchUser(keyword).observe(this, Observer {
                    onUpdateUI(it)
                })
            }
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        userViewModel = ViewModelProviders.of(this, viewModelFactory)[RemoteUsersViewModel::class.java]
        actionViewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UserActionViewModel::class.java]
    }

    private fun onUpdateUI(res : Resource<UserList>) {
        when (res) {
            is Resource.Success -> {
                processBar.visibility = View.GONE
                adatper.submitList(res.data.items)
            }
            is Resource.Loading -> {
                processBar.visibility = View.VISIBLE
            }
            is Resource.Error -> {
                processBar.visibility = View.GONE
                Toast.makeText(activity, res.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}