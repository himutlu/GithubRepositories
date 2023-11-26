package com.him.githubrepositories.feature.presentation.repositoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.him.githubrepositories.core.util.loadImage
import com.him.githubrepositories.databinding.ItemRepositoryBinding
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse

class RepositoryListAdapter(
    private var repositoryList: ArrayList<RepositoriesResponse>,
    var listener: OnClickListener? = null
) :
    RecyclerView.Adapter<RepositoryListAdapter.RepositoryListViewHolder>() {

    fun interface OnClickListener {
        fun itemClicked(repositoryName: String, ownerName: String)
    }

    class RepositoryListViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        repositoryList[position].name?.let { holder.binding.tvRepoName.text = it }
        repositoryList[position].owner?.login?.let { holder.binding.tvOwnerName.text = it }
        repositoryList[position].owner?.avatarUrl?.let {
            holder.binding.ivAvatar.loadImage(
                it,
                holder.binding.root.context
            )
        }
        listener?.let { listener ->
            holder.binding.cvItemRepository.setOnClickListener {
                listener.itemClicked(
                    repositoryList[position].name ?: "",
                    repositoryList[position].owner?.login ?: ""
                )
            }
        }
    }

    override fun getItemCount(): Int = repositoryList.size

    fun addRepositoryList(list: ArrayList<RepositoriesResponse>) {
        this.repositoryList = list
        notifyDataSetChanged()
    }
}