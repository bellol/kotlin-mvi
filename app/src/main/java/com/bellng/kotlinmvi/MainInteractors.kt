package com.bellng.kotlinmvi

import io.reactivex.Observable

/**
 * Created by bellng on 21/5/17.
 */
sealed class MainInteractors {

    class TextStyleInteractor {
        fun toggleBold(isBold: Boolean): Observable<MainViewState.PartialState.BoldTextState> {
            return Observable.just(MainViewState.PartialState.BoldTextState(isBold))
        }
    }

    class CounterInteractor {
        var currentCount = 0

        fun incrementCounter(): Observable<MainViewState.PartialState.CounterState> {
            return Observable.just(MainViewState.PartialState.CounterState(++currentCount))
        }

        fun decrementCounter(): Observable<MainViewState.PartialState.CounterState> {
            return Observable.just(MainViewState.PartialState.CounterState(--currentCount))
        }
    }
}