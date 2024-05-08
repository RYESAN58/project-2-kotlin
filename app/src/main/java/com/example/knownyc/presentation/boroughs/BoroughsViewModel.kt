package com.example.knownyc.presentation.boroughs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knownyc.commons.AppError
import com.example.knownyc.commons.Either
import com.example.knownyc.commons.Event
import com.example.knownyc.commons.TAG
import com.example.knownyc.commons.sendEvent
import com.example.knownyc.domain.models.Borough
import com.example.knownyc.domain.repositories.BoroughsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoroughsViewModel  @Inject constructor(
    private val repository: BoroughsRepository
// we need a repository
) :ViewModel() {
    private val _state: MutableStateFlow<BoroughsUIState> = MutableStateFlow(BoroughsUIState())
    val state: StateFlow<BoroughsUIState> = _state.asStateFlow()

    init {
        Log.d(TAG, "ViewModel init: getting boroughs")
        getBoroughs()
    }

    private fun getBoroughs() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            when (val result: Either<AppError, List<Borough>> = repository.getBoroughs()) {
                is Either.Data -> {
                    Log.d(TAG, "successfully loaded boroughs data")
                    _state.update {
                        it.copy(
                            boroughs = result.value
                        )
                    }
                }
                is Either.Error -> {
                    Log.e(TAG, "Error: loading boroughs data")
                    _state.update {
                        it.copy(
                            error = result.error
                        )
                    }
                    sendEvent(Event.Toast(result.error.message))
                }
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}
