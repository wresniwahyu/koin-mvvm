package com.www.app.ui.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.www.app.BR
import com.www.app.R
import com.www.app.data.model.movies.ResultsItem
import com.www.app.databinding.ActivityMainBinding
import com.www.app.ui.base.BaseActivity
import com.www.app.utils.gone
import com.www.app.utils.toast
import com.www.app.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(), MainActivityNavigator {

    private val mMainActivityViewModel: MainActivityViewModel by viewModel()
    private val mMainAdapter: MainAdapter by inject { parametersOf(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainActivityViewModel.setNavigator(this)

        setSupportActionBar(toolbar)

        rv_movies.layoutManager = GridLayoutManager(this, 2)
        rv_movies.adapter = mMainAdapter

        mMainActivityViewModel.getMovies()
    }

    override fun getBindingVariable(): Int = BR.mainActivityViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainActivityViewModel = mMainActivityViewModel

    override fun showHideLoading(visible: Boolean) {
        progress_bar.apply { if (visible) visible() else gone() }
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun showSuccessGetMovies(response: ArrayList<ResultsItem>) {
        mMainAdapter.addAll(response)
    }
}
