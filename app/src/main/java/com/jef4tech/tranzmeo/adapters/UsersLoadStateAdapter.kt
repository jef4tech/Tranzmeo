package com.jef4tech.tranzmeo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jef4tech.tranzmeo.databinding.ItemLoadingStateBinding

/**
 * @author jeffin
 * @date 31/01/23
 */
class UsersLoadStateAdapter(
    private val retry: () -> Unit): LoadStateAdapter<UsersLoadStateAdapter.UserLoadViewHolder>() {
    inner class UserLoadViewHolder(val custombind: ItemLoadingStateBinding): RecyclerView.ViewHolder(custombind.root)

    override fun onBindViewHolder(holder: UserLoadViewHolder, loadState: LoadState) {
        if (loadState is LoadState.Error) {
            holder.custombind.textViewError.text = loadState.error.localizedMessage
        }
        holder.custombind.progressbar.isVisible=(loadState is LoadState.Loading)
        holder.custombind.buttonRetry.isVisible=(loadState is LoadState.Error)
        holder.custombind.textViewError.isVisible=(loadState is LoadState.Error)
        holder.custombind.buttonRetry.setOnClickListener {
            retry()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): UserLoadViewHolder {
        val itemBinding = ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserLoadViewHolder(itemBinding)
    }
}