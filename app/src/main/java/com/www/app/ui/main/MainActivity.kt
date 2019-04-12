package com.www.app.ui.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.www.app.BR
import com.www.app.R
import com.www.app.data.model.movies.MoviesResponse
import com.www.app.databinding.ActivityMainBinding
import com.www.app.ui.base.BaseActivity
import com.www.app.utils.Constant.GRID_SPAN_COUNT
import com.www.app.utils.Constant.START_PAGE
import com.www.app.utils.EndlessRecyclerViewListener
import com.www.app.utils.gone
import com.www.app.utils.toast
import com.www.app.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(), MainActivityNavigator,
    SwipeRefreshLayout.OnRefreshListener {

    private val mMainActivityViewModel: MainActivityViewModel by viewModel()
    private val mMainAdapter: MainAdapter by inject { parametersOf(this@MainActivity) }

    private lateinit var mLayoutManager: GridLayoutManager
    private lateinit var mScrollListener: EndlessRecyclerViewListener

    private var currentPage = START_PAGE
    private var totalPages = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        mMainActivityViewModel.setNavigator(this)
        swipe_refresh.setOnRefreshListener(this)

        mLayoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        rv_movies.adapter = mMainAdapter

        mScrollListener = object : EndlessRecyclerViewListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                currentPage += 1

                if (currentPage <= totalPages) {
                    mMainActivityViewModel.getMovies(currentPage)
                } else {
                    toast(getString(R.string.info_all_loaded))
                }
            }
        }

        setManager()

        mMainActivityViewModel.getMovies(START_PAGE)
    }

    override fun getBindingVariable(): Int = BR.mainActivityViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainActivityViewModel = mMainActivityViewModel

    override fun onRefresh() {
        mMainAdapter.clear()
        setManager()
        currentPage = START_PAGE
        mMainActivityViewModel.getMovies(START_PAGE)
    }

    override fun showHideLoading(visible: Boolean) {
        progress_bar.apply {
            if (visible) {
                visible()
            } else {
                gone()
                swipe_refresh.isRefreshing = false
            }
        }
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun showSuccessGetMovies(response: MoviesResponse) {
        mMainAdapter.addAll(response.results)
        totalPages = response.total_pages
    }

    private fun setManager() {
        rv_movies.layoutManager = mLayoutManager
        rv_movies.addOnScrollListener(mScrollListener)
    }

}
