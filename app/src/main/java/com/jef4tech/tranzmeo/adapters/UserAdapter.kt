package com.jef4tech.tranzmeo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jef4tech.tranzmeo.databinding.AdapterUserBinding
import com.jef4tech.tranzmeo.models.UsersResponse
import com.jef4tech.tranzmeo.utils.Extensions

/**
 * @author jeffin
 * @date 30/01/23
 */
class UserAdapter(private val listener: (userId: Int) -> Unit): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    //    private var userList:List<UserResponse> = ArrayList()
    private var listData = ArrayList<UsersResponse.User>()
    inner class UserViewHolder(val custombind:AdapterUserBinding):RecyclerView.ViewHolder(custombind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listData[position]
        holder.custombind.apply {
            tvUserName.text = "UserName: "+user.username
            tvEmail.text = "Email : "+user.email
            tvMaiden.text = "Maiden Name: "+user.maidenName
            layout1.setOnClickListener{
                listener.invoke(user.id)
            }
        }
        Extensions.loadImagefromUrl(holder.custombind.ivUser.context,holder.custombind.ivUser,user.image)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setData(newListData: List<UsersResponse.User>, isLoading: Boolean){
        if (newListData == null) return
        if (!isLoading){
            listData.clear()
        }
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    fun getVariableValue() : Int {
        return listData.size
    }

    fun clearAllData(){
        listData.clear()
        notifyDataSetChanged()
    }
}