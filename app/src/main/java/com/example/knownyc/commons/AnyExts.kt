package com.example.knownyc.commons

/**
 * Extension function to provie TAG VALUE
 */

val Any.TAG: String
    get() {
        return if (!javaClass.isAnonymousClass) {
            val name: String = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0,23)
        } else {
            val name:String = javaClass.name
            if (name.length <= 23) name else name.substring(name.length - 23, name.length)
        }
    }