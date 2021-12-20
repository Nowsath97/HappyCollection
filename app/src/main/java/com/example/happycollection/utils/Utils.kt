package com.example.happycollection.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class Utils {

    private var snackbar: Snackbar? = null

    fun showSnackbar(view: View, message: String) {
        //Snackbar(view)
        snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Action", null)
        snackbar!!.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar!!.view
        snackbarView.setBackgroundColor(Color.BLACK)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.RED)
        textView.textSize = 12f
        snackbar!!.show()
    }

    fun dismissSnackbar() {
        if (snackbar != null && snackbar!!.isShown) {
            snackbar!!.dismiss()
        }
    }


    fun getValidCount(count: Int): Any {
        return if (count <= 0) {
            false
        } else {
            count
        }
    }

    fun getValidPrice(price: Double): Any {
        return if (price < 0 || price.equals(Double.MAX_VALUE)) {
            false
        } else {
            price
        }
    }

    fun getRating(rate: Float): Float {
        return if (rate > 5F) {
            5F
        } else {
            rate
        }
    }
}