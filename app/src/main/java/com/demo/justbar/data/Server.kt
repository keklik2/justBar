package com.demo.justbar.data

import android.annotation.SuppressLint
import com.demo.justbar.domain.Cocktail
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase

object Server {

    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    fun getDbVersion(
        onSuccessCallback: ((Int) -> Unit)? = null,
        onErrorCallback: ((e: Exception) -> Unit)? = null
    ) {
        db.collection(CHILD_VERSION)
            .get()
            .addOnSuccessListener {
                val version = it.documents[0].data?.get(VERSION_KEY)
                onSuccessCallback?.invoke(
                    if (version != null) (version as Long).toInt()
                    else 1
                )
            }
            .addOnFailureListener { onErrorCallback?.invoke(it) }
    }

    fun getAllCocktails(
        onSuccessCallback: ((List<Cocktail>) -> Unit)? = null,
        onErrorCallback: ((e: Exception) -> Unit)? = null
    ) {
        db.collection(CHILD_COCKTAILS)
            .orderBy("title")
            .addSnapshotListener { value, error ->
                if (error != null) onErrorCallback?.invoke(error)
                else onSuccessCallback?.invoke(value?.toObjects(Cocktail::class.java) ?: listOf())
            }
    }

    fun addCocktail(cocktail: Cocktail) {
        db.collection(CHILD_COCKTAILS)
            .add(cocktail)
            .addOnFailureListener { err ->
                throw Exception(err)
            }
    }

    private const val CHILD_VERSION = "version"
    private const val CHILD_COCKTAILS = "cocktails"

    private const val VERSION_KEY = "ver"
}
