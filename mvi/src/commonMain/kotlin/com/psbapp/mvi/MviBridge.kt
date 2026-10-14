package com.psbapp.mvi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Drives a [MVIBaseViewModel] from a UI that cannot collect flows itself.
 *
 * Compose collects `uiState` directly, but a React front end (or any other non-Kotlin host) needs
 * plain callbacks. This owns the collection scope and hands each observation back as a [Job] the
 * caller cancels when its view goes away.
 *
 * The bridge holds no platform types, so the same class serves the web facades today and any
 * other host later.
 *
 * **[dispose] is terminal.** It cancels the scope for good; a bridge is built per mount, never
 * reused after disposal.
 */
class MviBridge<Event : UiEvent, State : UiState, Effect : UiEffect>(
    private val viewModel: MVIBaseViewModel<Event, State, Effect>,
    dispatcher: CoroutineDispatcher,
) {

    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Pushes every state through [map] before handing it to [onState], so the host receives its
     * own shape rather than the domain model.
     */
    fun <Mapped> observeState(map: (State) -> Mapped, onState: (Mapped) -> Unit): Job =
        scope.launch {
            viewModel.uiState.collectLatest { state -> onState(map(state)) }
        }

    /** Every effect implementing [ErrorEffect]; anything else is left to [observeEffects]. */
    fun observeErrors(onError: (String) -> Unit): Job =
        scope.launch {
            viewModel.effect.collectLatest { effect ->
                if (effect is ErrorEffect) onError(effect.message)
            }
        }

    /** All effects, for hosts that act on navigation or one-shot signals too. */
    fun observeEffects(onEffect: (Effect) -> Unit): Job =
        scope.launch {
            viewModel.effect.collectLatest { effect -> onEffect(effect) }
        }

    fun send(event: Event) {
        viewModel.setEvent(event)
    }

    fun dispose() {
        scope.cancel()
    }
}
