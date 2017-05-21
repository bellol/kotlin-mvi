package com.bellng.kotlinmvi

/**
 * Created by bellng on 21/5/17.
 */

class MainViewState(val count: Int = 0, val hasBoldText: Boolean = false){
    sealed class PartialState {

        abstract fun getUpdatedState(previousState: MainViewState): MainViewState

        class BoldTextState(val hasBoldText: Boolean) : PartialState() {
            override fun getUpdatedState(previousState: MainViewState) = MainViewState(count = previousState.count, hasBoldText = hasBoldText)
        }

        class CounterState(val count: Int = 0) : PartialState() {
            override fun getUpdatedState(previousState: MainViewState) = MainViewState(count = count, hasBoldText = previousState.hasBoldText)
        }
    }
}


