package net.sokum.mordern.app.ui.main.remote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import net.sokum.mordern.app.R
import net.sokum.base.di.activityViewModelProvider
import net.sokum.base.di.viewModelProvider
import net.sokum.base.network.Resource
import net.sokum.base.ui.BaseFragment
import net.sokum.mordern.app.data.UserList
import net.sokum.mordern.app.ui.main.UserActionViewModel

class RemoteUsersFragment : BaseFragment() {
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

        userViewModel.likeUsers.observe(this, Observer {
            adatper.submitLikeMap(it)
        })


        actionViewModel.searchKeywordRemote.observe(this, Observer { keyword ->
            if ( keyword.isEmpty() ) {
                adatper.submitList(mutableListOf())
            } else {
                userViewModel.searchUser(keyword).observe(this, onUpdateUI())
            }
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        userViewModel = viewModelProvider()
        actionViewModel = activityViewModelProvider()
    }

    private fun onUpdateUI() = Observer<Resource<UserList>> {
        when (it) {
            is Resource.Success -> {
                processBar.visibility = View.GONE
                adatper.submitList(it.data.items)
            }
            is Resource.Loading -> {
                processBar.visibility = View.VISIBLE
            }
            is Resource.Error -> {
                processBar.visibility = View.GONE
                Toast.makeText(activity, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}