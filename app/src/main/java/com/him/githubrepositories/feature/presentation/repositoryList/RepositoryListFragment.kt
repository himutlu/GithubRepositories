package com.him.githubrepositories.feature.presentation.repositoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.him.githubrepositories.R
import com.him.githubrepositories.databinding.FragmentRepositoryListBinding
import com.him.githubrepositories.feature.MainActivity
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
        activity?.setTitle(R.string.repository_list)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        if (!viewModel.repositoriesLiveData.isInitialized)
            viewModel.getRepositories()
    }

    private fun initRecyclerView() {
        binding.rvRepositories.layoutManager = LinearLayoutManager(activity)
        binding.rvRepositories.adapter = adapter
    }

    private fun initObservers() {
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                (activity as MainActivity).showFragmentContainer()
                adapter.addRepositoryList(it as ArrayList<RepositoriesResponse>)
            }
        }

        viewModel.dataLoading.observe(viewLifecycleOwner) {
            if (it)
                (activity as MainActivity).showProgressbarAndHideContainer()
            else
                (activity as MainActivity).hideProgressbar()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                (activity as MainActivity).showFetchingErrorPopup(it, {
                    viewModel.getRepositories()
                }, {
                    findNavController().navigateUp()
                })
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