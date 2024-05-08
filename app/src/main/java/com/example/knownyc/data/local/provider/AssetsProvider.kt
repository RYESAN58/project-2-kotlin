package com.example.knownyc.data.local.provider

interface AssetsProvider {

    suspend fun getJsonData(filename: String): String

    suspend fun getDrawableResourceId(name: String): Int
}