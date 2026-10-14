package com.psbapp.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIBaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> :
    BaseViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    init {
        subscribeEvents()
        subscribeEffects()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Start listening to Effects
     */
    private fun subscribeEffects() {
        viewModelScope.launch {
            effect.collect {
                handleEffect(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event: Event)

    /**
     * Handle each effect
     */
    abstract fun handleEffect(effect: Effect)

    /**
     * Set new Event
     */
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Update the UI State for synchronized update
     */
    protected fun updateState(reduce: State.() -> State) {
        _uiState.update(reduce)
    }

    /**
     * Set new Effect
     */
    fun setEffect(effect: Effect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}