package com.him.githubrepositories.feature.presentation.repositoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.him.githubrepositories.databinding.FragmentRepositoryListBinding
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryListFragment : Fragment(), RepositoryListAdapter.OnClickListener {
    private val viewModel: RepositoryListViewModel by viewModels()
    private var _binding: FragmentRepositoryListBinding? = null
    private val binding get() = _binding!!
    private var adapter = RepositoryListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        viewModel.getRepositories()
    }

    private fun initRecyclerView() {
        binding.rvRepositories.layoutManager = LinearLayoutManager(activity)
        binding.rvRepositories.adapter = adapter
    }

    private fun initObservers() {
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.addRepositoryList(it as ArrayList<RepositoriesResponse>)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun itemClicked(repositoryName: String, ownerName: String) {
        val action =
            RepositoryListFragmentDirections.actionRepositoryListFragmentToRepositoryDetailFragment(
                ownerName,
                repositoryName
            )
        findNavController().navigate(action)
    }

}