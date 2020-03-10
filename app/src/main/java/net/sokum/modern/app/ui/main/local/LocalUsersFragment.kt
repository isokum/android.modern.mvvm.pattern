package net.sokum.modern.app.ui.main.local

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import net.sokum.modern.app.R
import net.sokum.base.di.activityViewModelProvider
import net.sokum.base.di.viewModelProvider
import net.sokum.base.ui.BaseFragment
import net.sokum.modern.app.ui.main.UserActionViewModel

class LocalUsersFragment : BaseFragment() {

    private lateinit var userViewModel : LocalUsersViewModel

    lateinit var actionViewModel : UserActionViewModel

    lateinit var adapter : LocalUserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        actionViewModel.searchKeywordLocal.observe(this, Observer {
            onRefreshList(it)
        })

        adapter = LocalUserListAdapter(actionViewModel)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        onRefreshList("")
    }

    private fun onRefreshList(query: String) {
        userViewModel.findByLogin(query).observe(this, Observer {res ->
            adapter.submitList(res)
        })
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        userViewModel = viewModelProvider()
        actionViewModel = activityViewModelProvider()
    }
}