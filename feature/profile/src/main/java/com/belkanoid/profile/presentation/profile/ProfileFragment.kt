package com.belkanoid.profile.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.core.util.SpaceItemDecoration
import com.belkanoid.profile.R
import com.belkanoid.profile.databinding.FragmentProfileBinding
import com.belkanoid.profile.di.componentHolder.ProfileComponentHolder
import com.belkanoid.profile.presentation.profile.adapter.ProfileAdapterDelegate
import com.belkanoid.profile.presentation.profile.viewModel.ProfileViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>{factory}

    private val profileAdapter = ListDelegationAdapter(
        ProfileAdapterDelegate.defaultAdapterDelegate(),
        ProfileAdapterDelegate.subtitledAdapterDelegate()
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProfileComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileRvItems.adapter = profileAdapter
        binding.profileRvItems.addItemDecoration(SpaceItemDecoration(bottom = 16, bottomAfterFirstItem = 80))
        binding.profileBtnLogout.setOnClickListener{ viewModel.logout() }
        profileAdapter.items = viewModel.profileItemList()

    }


    override fun onDetach() {
        super.onDetach()
        ProfileComponentHolder.clear()
    }

}