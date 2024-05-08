package com.example.knownyc.data.local.provider

import android.annotation.SuppressLint
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetsProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    ) : AssetsProvider {
    override suspend fun getJsonData(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

    @SuppressLint("DiscouragedApi")
    override suspend fun getDrawableResourceId(name: String): Int =
        context.resources.getIdentifier(name, "drawable", context.packageName)
}


