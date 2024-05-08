package com.example.knownyc.presentation.boroughs

import com.example.knownyc.commons.AppError
import com.example.knownyc.domain.models.Borough

data class BoroughsUIState(
    val isLoading: Boolean = false,
    val boroughs: List<Borough> = emptyList(),
    val error: AppError? = null,
)


   

