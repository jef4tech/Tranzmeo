package com.jef4tech.tranzmeo.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jef4tech.tranzmeo.databinding.FragmentDashboardBinding
import com.jef4tech.tranzmeo.utils.Extensions

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val userId= arguments?.getInt("Id")
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        dashboardViewModel.getUserData(userId!!)
        dashboardViewModel.userData.observe(viewLifecycleOwner) {
            binding.apply {
                tvUserName.text = "User Name"+it.username
                tvEmail.text = "Email"+it.email
                tvFirstName.text = "First Name:"+it.firstName
                tvLastName.text = "Last Name:"+it.lastName
                tvAge.text = "Age:"+it.age.toString()
                tvMaiden.text = "Maiden Name:"+it.maidenName
                tvPhone.text = "Phone No:"+it.phone

            }
            Extensions.loadImagefromUrl(binding.ivUser.context,binding.ivUser,it.image)
            Log.i("important", "onCreateView: $it")
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}