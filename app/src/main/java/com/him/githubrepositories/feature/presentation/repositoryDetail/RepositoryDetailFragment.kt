package com.him.githubrepositories.feature.presentation.repositoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.him.githubrepositories.core.util.hide
import com.him.githubrepositories.core.util.loadImage
import com.him.githubrepositories.databinding.FragmentRepositoryDetailBinding
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.getRepositoryDetail(safeArgs.userName, safeArgs.repositoryName)
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
                detail.repositoryName?.let { name -> binding.tvRepositoryName.text = name }
                    ?: kotlin.run { binding.llRepositoryName.hide() }
                detail.owner?.userName?.let { userName -> binding.tvUserName.text = userName }
                    ?: kotlin.run { binding.llUserName.hide() }
                detail.forkCount?.let { count -> binding.tvForkCount.text = count }
                    ?: kotlin.run { binding.llForkCount.hide() }
                detail.language?.let { lang -> binding.tvLanguage.text = lang }
                    ?: kotlin.run { binding.llLanguage.hide() }
                detail.defaultBranchName?.let { branchName ->
                    binding.tvDefaultBranchName.text =
                        branchName
                } ?: kotlin.run { binding.llDefaultBranchName.hide() }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}