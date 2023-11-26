package com.him.githubrepositories.feature.presentation.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.him.githubrepositories.core.util.hide
import com.him.githubrepositories.core.util.loadImage
import com.him.githubrepositories.databinding.FragmentUserDetailBinding
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.domain.util.Constants.HYPHEN
import com.him.githubrepositories.feature.presentation.repositoryList.RepositoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private val viewModel: UserDetailViewModel by viewModels()
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: UserDetailFragmentArgs by navArgs()
    private var adapter = RepositoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        viewModel.getUserDetail(safeArgs.username)
    }

    private fun initRecyclerView() {
        binding.rvUserRepositories.layoutManager = LinearLayoutManager(activity)
        binding.rvUserRepositories.adapter = adapter
    }

    private fun initObservers() {
        viewModel.userDetailLiveData.observe(viewLifecycleOwner) {
            it?.let { user ->
                user.avatarLink?.let { url ->
                    binding.ivUserAvatar.loadImage(
                        url,
                        requireContext()
                    )
                } ?: kotlin.run { binding.ivUserAvatar.hide() }
                binding.tvOwnerUsername.text = user.username ?: HYPHEN
                binding.tvEmail.text = user.username ?: HYPHEN
            }
        }

        viewModel.userRepositoriesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.addRepositoryList(it as ArrayList<RepositoriesResponse>)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}