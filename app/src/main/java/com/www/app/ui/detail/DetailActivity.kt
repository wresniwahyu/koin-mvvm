package com.www.app.ui.detail

import android.os.Bundle
import com.www.app.BR
import com.www.app.R
import com.www.app.databinding.ContentDetailBinding
import com.www.app.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ContentDetailBinding, DetailViewModel>(), DetailNavigator {

    private val mDetailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDetailViewModel.setNavigator(this)
    }

    override fun getBindingVariable(): Int = BR.detailViewModel

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel = mDetailViewModel

    override fun showHideLoading(visible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
