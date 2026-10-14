package com.psbapp.mvi

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.reflect.KClass

/**
 * What a JavaScript front end needs from its host app: a started object graph, the dispatcher its
 * ViewModels observe on, and a way to look an instance up.
 *
 * An app implements this once — over Koin, Kodein, or hand-rolled factories, this module does not
 * care and takes no DI dependency of its own. Screen facades then call [bridgeTo] and know nothing
 * about how the app is wired.
 */
interface JsAppGraph {

    /** Builds the graph if it is not up yet. Must be safe to call repeatedly. */
    fun ensureStarted()

    /** Where ViewModel state is observed — the UI thread equivalent for the platform. */
    val mainDispatcher: CoroutineDispatcher

    /** Resolves a single instance of [type] from the graph. */
    fun <T : Any> instance(type: KClass<T>): T
}

/**
 * Resolves [ViewModel] from this graph and wires a bridge to it, so a screen facade needs one line
 * of setup:
 *
 * ```kotlin
 * private val bridge = AppGraph.bridgeTo<HomeViewModel, Event, State, Effect>()
 * ```
 *
 * The graph starts on first use, so nothing has to be bootstrapped before the first screen mounts.
 */
inline fun <reified ViewModel, Event : UiEvent, State : UiState, Effect : UiEffect>
    JsAppGraph.bridgeTo(): MviJsBridge<Event, State, Effect>
    where ViewModel : MVIBaseViewModel<Event, State, Effect> {
    ensureStarted()
    return MviJsBridge(instance(ViewModel::class), mainDispatcher)
}
