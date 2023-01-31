package com.jef4tech.tranzmeo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jef4tech.tranzmeo.R
import com.jef4tech.tranzmeo.adapters.UserAdapter
import com.jef4tech.tranzmeo.databinding.FragmentHomeBinding
import com.jef4tech.tranzmeo.utils.Extensions

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var homeViewModel:HomeViewModel
    private lateinit var userAdapter: UserAdapter
    private var page = 1
    private  var searchKeyword =""
    private var isLoading = false
    private var limit = 10
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel.getUserList(10,0)
        homeViewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.setData(it.users,isLoading)
            Log.i("important", "onCreateView: $it")
        }
        setupRecyclerView()
        return root
    }

    private fun setupRecyclerView()=binding.userRecyclerView.apply {
        userAdapter= UserAdapter{position -> onClick(position)}
        adapter=userAdapter
        layoutManager= LinearLayoutManager(context)
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                val totalItemCount = (layoutManager as LinearLayoutManager).itemCount
                val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                    Extensions.showToast("load more",requireContext())
                    // make API call to get the next set of users
                    if (userAdapter.getVariableValue()==limit){
                        // Extensions.showToast("load more",requireContext())
                        apiCall()
                    }
                }
            }
        })
    }

    private fun apiCall() {
        page++
        limit += 10
        isLoading = true
        Log.i("scrolling end", "onScrolled: $page")
        homeViewModel.getUserList(10, 0)
    }
    private fun onClick(position: Int) {

        val bundle= Bundle()
        bundle.putInt("Id", position)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_navigation_dashboard,bundle) }
    }

    override fun onResume() {
        super.onResume()
        limit = 10
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}