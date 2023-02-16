package com.example.carauosellnews.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carauosellnews.R
import com.example.carauosellnews.databinding.ActivityHomeBinding
import com.example.carauosellnews.presentation.adapters.NewsListAdapter
import com.example.carauosellnews.presentation.viewmodels.NewsViewModel
import com.example.carauosellnews.presentation.viewmodels.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navGraph: NavGraph

    @Inject
    lateinit var newAdapter: NewsListAdapter

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home) as NavHostFragment
        appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        val inflater = navHostFragment.navController.navInflater
        navGraph = inflater.inflate(R.navigation.nav_graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}