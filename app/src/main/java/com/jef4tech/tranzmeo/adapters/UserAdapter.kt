package com.jef4tech.tranzmeo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jef4tech.tranzmeo.databinding.AdapterUserBinding
import com.jef4tech.tranzmeo.models.UsersResponse
import com.jef4tech.tranzmeo.utils.Extensions

/**
 * @author jeffin
 * @date 30/01/23
 */
class UserAdapter(private val listener: (userId: Int) -> Unit): PagingDataAdapter<UsersResponse.User, UserAdapter.UserViewHolder>(DataDifferntiator)
    {

//    private var listData = ArrayList<UsersResponse.User>()
    inner class UserViewHolder(val custombind:AdapterUserBinding):RecyclerView.ViewHolder(custombind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

            val user = getItem(position)
            holder.custombind.apply {
                tvUserName.text = "UserName: " + user?.username
                tvEmail.text = "Email : " + user?.email
                tvMaiden.text = "Maiden Name: " + user?.maidenName
                tvPhone.text = "Phone No:"+user?.phone
                layout1.setOnClickListener {
                    listener.invoke(user!!.id)
                }
            }
            Extensions.loadImagefromUrl(
                holder.custombind.ivUser.context,
                holder.custombind.ivUser,
                user!!.image
            )
    }

        object DataDifferntiator : DiffUtil.ItemCallback<UsersResponse.User>() {

            override fun areItemsTheSame(oldItem: UsersResponse.User, newItem: UsersResponse.User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UsersResponse.User, newItem: UsersResponse.User): Boolean {
                return oldItem == newItem
            }
        }
}