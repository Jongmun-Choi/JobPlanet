package com.jobplanet.test

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jobplanet.test.adapter.DataListAdapter
import com.jobplanet.test.databinding.ActivityMainBinding
import com.jobplanet.test.viewmodel.DataListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : DataListViewModel

    val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
    }

    lateinit var mainDataAdapter : DataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DataListViewModel::class.java)

        mainDataAdapter = DataListAdapter()

        binding.item = mainDataAdapter


        viewModel.mListData.observe(this) {
            it.let(mainDataAdapter::submitList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView = menu?.findItem(R.id.menuSearch)?.actionView as SearchView
        (searchView.findViewById(R.id.search_src_text)
                as SearchView.SearchAutoComplete).hint = getString(R.string.search_hint)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.isIconified = false
        return true
    }
}