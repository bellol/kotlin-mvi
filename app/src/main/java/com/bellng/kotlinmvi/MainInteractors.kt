package com.bellng.kotlinmvi

import io.reactivex.Observable

/**
 * Created by bellng on 21/5/17.
 */
sealed class MainInteractors {

    class TextStyleInteractor {
        fun toggleBold(isBold: Boolean) = Observable.just(MainViewState.PartialState.BoldTextState(isBold))
    }

    class CounterInteractor {
        var currentCount = 0

        fun incrementCounter() = Observable.just(MainViewState.PartialState.CounterState(count = ++currentCount))

        fun decrementCounter() = Observable.just(MainViewState.PartialState.CounterState(count = --currentCount))

        fun resetCounter(): Observable<MainViewState.PartialState.CounterState> {
            currentCount = 0
            return Observable.just(MainViewState.PartialState.CounterState(count = currentCount))
        }
    }
}