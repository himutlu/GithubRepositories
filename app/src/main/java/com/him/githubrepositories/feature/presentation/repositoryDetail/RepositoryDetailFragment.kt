package com.him.githubrepositories.feature.presentation.repositoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.him.githubrepositories.databinding.FragmentRepositoryDetailBinding

class RepositoryDetailFragment : Fragment() {
    private val viewModel: RepositoryDetailViewModel by viewModels()
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}