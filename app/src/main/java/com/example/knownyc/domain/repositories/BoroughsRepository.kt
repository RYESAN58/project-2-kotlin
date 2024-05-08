package com.example.knownyc.domain.repositories

import com.example.knownyc.commons.AppError
import com.example.knownyc.commons.Either
import com.example.knownyc.domain.models.Borough

interface BoroughsRepository {

    suspend fun getBoroughs(): Either<AppError, List<Borough>>
}