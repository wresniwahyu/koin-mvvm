package com.www.app.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.www.app.BR
import com.www.app.R
import com.www.app.data.model.movie.MovieResponse
import com.www.app.databinding.ActivityDetailBinding
import com.www.app.ui.base.BaseActivity
import com.www.app.utils.gone
import com.www.app.utils.toast
import com.www.app.utils.visible
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailNavigator {

    companion object {
        const val EXTRA_ID = "extra_id"

        fun getActivityIntent(context: Context, id: Int): Intent {
            return Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_ID, id)
        }
    }

    private val mDetailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDetailViewModel.setNavigator(this)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        if (intent.hasExtra(EXTRA_ID)) {
            val id = intent.getIntExtra(EXTRA_ID, 0)
            if (id != 0) mDetailViewModel.getMovieDetail(id)
        }

    }

    override fun getBindingVariable(): Int = BR.detailViewModel

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel = mDetailViewModel

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun showHideLoading(visible: Boolean) {
        progress_bar.apply { if (visible) visible() else gone() }
    }

    override fun showError(message: String) { toast(message) }

    override fun showSuccessDetail(response: MovieResponse) {
        getViewDataBinding().invalidateAll()
    }

}
