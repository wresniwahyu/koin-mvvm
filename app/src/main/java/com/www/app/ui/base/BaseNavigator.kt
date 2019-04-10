package com.www.app.ui.base

interface BaseNavigator {

    fun showHideLoading(visible: Boolean)

    fun showError(message: String)
}