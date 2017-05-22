package com.bellng.kotlinmvi

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable

class MainActivity : MviActivity<MainView, MainPresenter>(), MainView {

    @BindView(R.id.text) lateinit var text: TextView
    @BindView(R.id.increment_button) lateinit var incrementButton: Button
    @BindView(R.id.decrement_button) lateinit var decrementButton: Button
    @BindView(R.id.bold_on_button) lateinit var boldOnButton: Button
    @BindView(R.id.bold_off_button) lateinit var boldOffButton: Button
    @BindView(R.id.reset_button) lateinit var resetButton: Button

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    override fun render(viewState: MainViewState) {
        if (viewState.hasBoldText) {
            text.setTypeface(null, Typeface.BOLD)
        } else {
            text.setTypeface(null, Typeface.NORMAL)
        }

        if (viewState.showResetButton) {
            resetButton.visibility = View.VISIBLE
        } else {
            resetButton.visibility = View.GONE
        }

        text.text = viewState.count.toString()
    }

    override fun incrementIntent() = incrementButton.clicks()

    override fun decrementIntent() = decrementButton.clicks()

    override fun resetCounterIntent() = resetButton.clicks()

    override fun toggleBoldTextIntent(): Observable<Boolean> = Observable.merge(boldOnButton.clicks().map { true }, boldOffButton.clicks().map { false })

}
