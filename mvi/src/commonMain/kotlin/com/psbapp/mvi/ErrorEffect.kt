package com.psbapp.mvi

/**
 * An effect that carries a failure message for the UI to present.
 *
 * A ViewModel must not decide *how* an error is shown — a snackbar on Android, a toast in the
 * browser — so it raises an effect implementing this interface and each front end renders it its
 * own way. Marking the effect lets a UI handle failures generically instead of matching on every
 * feature's own `ShowError` type.
 */
interface ErrorEffect : UiEffect {
    val message: String
}
