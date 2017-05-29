package com.bellng.kotlinmvi

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MviActivity<MainView, MainPresenter>(), MainView {

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun render(viewState: MainViewState) {
        when (viewState.hasBoldText) {
            true -> text_view.setTypeface(null, Typeface.BOLD)
            else -> text_view.setTypeface(null, Typeface.NORMAL)
        }

        when (viewState.showResetButton) {
            true -> reset_button.visibility = View.VISIBLE
            else -> reset_button.visibility = View.GONE
        }

        text_view.text = viewState.count.toString()
    }

    override fun incrementIntent() = increment_button.clicks()

    override fun decrementIntent() = decrement_button.clicks()

    override fun resetCounterIntent() = reset_button.clicks()

    override fun toggleBoldTextIntent(): Observable<Boolean> = Observable.merge(bold_on_button.clicks().map { true }, bold_off_button.clicks().map { false })

}
