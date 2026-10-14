package com.psbapp.mvi

import kotlinx.coroutines.CoroutineDispatcher

/**
 * JavaScript-facing wrapper around [MviBridge].
 *
 * A screen facade owns one of these and exposes typed methods over it, so no facade repeats the
 * collection, disposal or subscription-handle plumbing. Everything app-specific — resolving the
 * ViewModel and mapping state into exported shapes — stays with the facade.
 *
 * Build one per mount: [dispose] cancels the underlying scope for good.
 */
class MviJsBridge<Event : UiEvent, State : UiState, Effect : UiEffect>(
    viewModel: MVIBaseViewModel<Event, State, Effect>,
    dispatcher: CoroutineDispatcher,
) {

    private val bridge = MviBridge(viewModel, dispatcher)

    fun <Mapped> observeState(map: (State) -> Mapped, onState: (Mapped) -> Unit): JsSubscription =
        JsSubscription(bridge.observeState(map, onState))

    fun observeErrors(onError: (String) -> Unit): JsSubscription =
        JsSubscription(bridge.observeErrors(onError))

    /** All effects, for the one-shot signals a screen acts on — navigation, sign-out, dismissal. */
    fun observeEffects(onEffect: (Effect) -> Unit): JsSubscription =
        JsSubscription(bridge.observeEffects(onEffect))

    fun send(event: Event) = bridge.send(event)

    fun dispose() = bridge.dispose()
}
