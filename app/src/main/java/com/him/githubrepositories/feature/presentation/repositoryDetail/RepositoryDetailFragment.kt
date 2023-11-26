package com.him.githubrepositories.feature.presentation.repositoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.him.githubrepositories.R
import com.him.githubrepositories.core.util.hide
import com.him.githubrepositories.core.util.loadImage
import com.him.githubrepositories.databinding.FragmentRepositoryDetailBinding
import com.him.githubrepositories.feature.domain.util.Constants.HYPHEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryDetailFragment : Fragment() {
    private val viewModel: RepositoryDetailViewModel by viewModels()
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: RepositoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(layoutInflater)
        activity?.setTitle(R.string.repository_detail)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
        viewModel.getRepositoryDetail(safeArgs.username, safeArgs.repositoryName)
    }

    private fun initViews() {
        binding.ivDetailAvatar.setOnClickListener {
            val action =
                RepositoryDetailFragmentDirections.actionRepositoryDetailFragmentToUserDetailFragment(
                    safeArgs.username
                )
            findNavController().navigate(action)
        }
    }

    private fun initObservers() {
        viewModel.repositoryDetailLiveData.observe(viewLifecycleOwner) {
            it?.let { detail ->
                detail.owner?.avatarUrl?.let { url ->
                    binding.ivDetailAvatar.loadImage(
                        url,
                        requireContext()
                    )
                } ?: kotlin.run { binding.ivDetailAvatar.hide() }
                binding.tvRepositoryName.text = detail.repositoryName?: HYPHEN
                binding.tvUsername.text = detail.owner?.username?: HYPHEN
                binding.tvForkCount.text = detail.forkCount?: HYPHEN
                binding.tvLanguage.text = detail.language?: HYPHEN
                binding.tvDefaultBranchName.text = detail.defaultBranchName?: HYPHEN
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}