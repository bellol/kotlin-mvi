package com.bellng.kotlinmvi

/**
 * Created by bellng on 21/5/17.
 */

data class MainViewState(val count: Int = 0, val hasBoldText: Boolean = false, val showResetButton: Boolean = false) {

    sealed class PartialState {

        abstract fun getUpdatedState(previousState: MainViewState): MainViewState

        data class BoldTextState(val hasBoldText: Boolean) : PartialState() {
            override fun getUpdatedState(previousState: MainViewState) = previousState.copy(hasBoldText = hasBoldText)
        }

        data class CounterState(val count: Int = 0, val showResetButton: Boolean = count != 0) : PartialState() {
            override fun getUpdatedState(previousState: MainViewState) = previousState.copy(count = count, showResetButton = showResetButton)
        }

    }
}


