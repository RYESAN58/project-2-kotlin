package com.example.knownyc.data.local.repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownyc.commons.AppError
import com.example.knownyc.commons.Either
import com.example.knownyc.commons.TAG
import com.example.knownyc.data.local.provider.AssetsProvider
import com.example.knownyc.data.mappers.boroughsMapper
import com.example.knownyc.data.mappers.toError
import com.example.knownyc.domain.models.Borough
import com.example.knownyc.domain.repositories.BoroughsRepository
import com.example.knownyc.util.AppConstants
import kotlinx.coroutines.delay
import org.json.JSONObject
import javax.inject.Inject

class BoroughsRepositoryImpl @Inject constructor(
    private val localAssetsProvider: AssetsProvider,
    ) : BoroughsRepository {

    private suspend fun loadJsonAndMapData(): List<Borough> {
        val json: JSONObject = loadJson()
        return boroughsMapper(json, localAssetsProvider)
    }

    private suspend fun loadJson(): JSONObject {
        val jsonString = localAssetsProvider.getJsonData(AppConstants.BOROUGHS_JSON)
        return JSONObject(jsonString)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getBoroughs(): Either<AppError, List<Borough>> {
        Log.d(TAG, "assets provider loading boroughs from JSON file")
        return try {
            delay(3000)
            Either.Data(
                loadJsonAndMapData()
            )
        } catch (e: Exception) {
            Either.Error(
                e.toError()
            )
        }
    }
}










