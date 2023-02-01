package com.jef4tech.tranzmeo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jef4tech.tranzmeo.R
import com.jef4tech.tranzmeo.adapters.UserAdapter
import com.jef4tech.tranzmeo.adapters.UsersLoadStateAdapter
import com.jef4tech.tranzmeo.databinding.FragmentHomeBinding
import com.jef4tech.tranzmeo.network.RetrofitClientFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var homeViewModel:HomeViewModel
    private lateinit var userAdapter: UserAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         homeViewModel =
             ViewModelProvider(this,MainViewModelFactory(RetrofitClientFactory))[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerView()
        binding.userRecyclerView.adapter = userAdapter.withLoadStateHeaderAndFooter(
            header = UsersLoadStateAdapter { userAdapter.retry() },
            footer = UsersLoadStateAdapter { userAdapter.retry() }
        )
        lifecycleScope.launch {
            homeViewModel.listData.observe(viewLifecycleOwner){ pagedData ->
                userAdapter.submitData(lifecycle,pagedData)
            }
        }

        return root
    }

    private fun setupRecyclerView()=binding.userRecyclerView.apply {
        userAdapter= UserAdapter{position -> onClick(position)}
        adapter=userAdapter
        layoutManager= LinearLayoutManager(context)
    }


    private fun onClick(position: Int) {

        val bundle= Bundle()
        bundle.putInt("Id", position)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_navigation_dashboard,bundle) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}