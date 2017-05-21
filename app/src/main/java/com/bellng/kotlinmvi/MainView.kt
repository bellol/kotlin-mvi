package com.bellng.kotlinmvi

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/**
 * Created by bellng on 21/5/17.
 */

interface MainView : MvpView {
    fun incrementIntent(): Observable<Unit>

    fun decrementIntent(): Observable<Unit>

    fun toggleOnBoldTextIntent(): Observable<Boolean>

    fun toggleOffBoldTextIntent(): Observable<Boolean>

    fun render(viewState: MainViewState)
}