package com.omaestre.core.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.omaestre.core.R

open class BaseActivity : AppCompatActivity() {

    private var isBack = false
    private var view : View? = null

    //region override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.decorView?.rootView.let {
            if (it != null) {
                view = it
            }
        }
    }

    override fun onStart() {
        super.onStart()
        isBack = false
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
        //check do animation
        if (isBack) {
            super.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        } else {
            super.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        isBack = true
    }

    //endregion

    //region public methods
    fun showSnackBarFailed(message: String) {
        view.let {
            if (it != null) {
                Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
            }
        }
    }
    //endregion

}