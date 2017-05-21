package com.bellng.kotlinmvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by bellng on 21/5/17.
 */
class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    val counterInteractor = MainInteractors.CounterInteractor()
    val textStyleInteractor = MainInteractors.TextStyleInteractor()

    override fun bindIntents() {
        val allIntents = Observable.merge(
                incrementCounterIntent(),
                decrementCounterIntent(),
                toggleOnBoldTextIntent(),
                toggleOffBoldTextIntent())
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(allIntents.scan(MainViewState(), this::reduce), {
            view, viewState ->
            view.render(viewState)
        })
    }

    private fun reduce(previousState: MainViewState, partialState: MainViewState.PartialState) = partialState.getUpdatedState(previousState)

    private fun incrementCounterIntent(): Observable<MainViewState.PartialState> {
        return intent { view -> view.incrementIntent() }
                .switchMap { counterInteractor.incrementCounter() }
    }

    private fun decrementCounterIntent(): Observable<MainViewState.PartialState> {
        return intent { view -> view.decrementIntent() }
                .switchMap { counterInteractor.decrementCounter() }
    }

    private fun toggleOnBoldTextIntent(): Observable<MainViewState.PartialState> {
        return intent { view -> view.toggleOnBoldTextIntent() }
                .switchMap(textStyleInteractor::toggleBold)
    }

    private fun toggleOffBoldTextIntent(): Observable<MainViewState.PartialState> {
        return intent { view -> view.toggleOffBoldTextIntent() }
                .switchMap(textStyleInteractor::toggleBold)
    }
}

