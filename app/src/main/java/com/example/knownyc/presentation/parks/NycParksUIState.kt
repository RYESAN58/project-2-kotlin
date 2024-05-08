package com.example.knownyc.presentation.parks

import com.example.knownyc.domain.models.NycPark

// TODO : PROJECT 2

data class NycParksUIState(
    val isLoading: Boolean = false,
    val parks: List<NycPark> = emptyList(),
    val error: String? = null
)
