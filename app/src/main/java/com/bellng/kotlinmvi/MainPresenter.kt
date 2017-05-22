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
                resetCounterIntent(),
                toggleBoldTextIntent())
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(allIntents.scan(MainViewState(), this::reduce), {
            view, viewState ->
            view.render(viewState)
        })
    }

    private fun reduce(previousState: MainViewState, partialState: MainViewState.PartialState) = partialState.getUpdatedState(previousState)

    private fun incrementCounterIntent() = intent { view -> view.incrementIntent() }.switchMap { counterInteractor.incrementCounter() }

    private fun decrementCounterIntent() = intent { view -> view.decrementIntent() }.switchMap { counterInteractor.decrementCounter() }

    private fun resetCounterIntent() = intent { view -> view.resetCounterIntent() }.switchMap { counterInteractor.resetCounter() }

    private fun toggleBoldTextIntent() = intent { view -> view.toggleBoldTextIntent() }.switchMap(textStyleInteractor::toggleBold)

}

