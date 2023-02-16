package com.example.carauosellnews.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carauosellnews.R
import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.databinding.FragmentNewsBinding
import com.example.carauosellnews.presentation.adapters.NewsListAdapter
import com.example.carauosellnews.presentation.viewmodels.NewsViewModel
import com.example.carauosellnews.utils.resource.Resource

class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsListAdapter
    private lateinit var newsList: MutableList<NewsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel
        newsAdapter = (activity as HomeActivity).newAdapter
        initRecyclerView()
        viewNewsList()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = inflater
        inflater.inflate(R.menu.action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.popular -> {
                showPopularList()
                true
            }
            R.id.recent -> {
                showRecentList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter

        }
    }

    private fun viewNewsList() {
        viewModel.getNewsData()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response?.data?.let {
                        newsList = it as MutableList<NewsItem>
                        newsAdapter.differ.submitList(newsList)
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                else -> {

                }
            }
        })
    }

    private fun sortNewsByRank() {
        viewModel.getNewsData()
        viewModel.newsLiveData.observe(this, Observer { response ->
            response?.data?.let {
                val mutableList =
                    it.sortedWith(compareBy<NewsItem> { it.rank }.thenBy { it.timeCreated }) as MutableList<NewsItem>
                newsAdapter.differ.submitList(mutableList)
            }
        })
    }

    private fun sortNewsByDate() {
        viewModel.getNewsData()
        viewModel.newsLiveData.observe(this, Observer { response ->
            response?.data?.let {
                var mutableList = it.sortedByDescending { it.timeCreated } as MutableList<NewsItem>
                newsAdapter.differ.submitList(mutableList)
            }
        })
    }

    private fun showPopularList() {
        sortNewsByRank()
    }

    private fun showRecentList() {
        sortNewsByDate()
    }
}