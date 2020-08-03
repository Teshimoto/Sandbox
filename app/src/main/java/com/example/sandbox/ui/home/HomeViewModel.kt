package com.example.sandbox.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sandbox.model.Repository
import com.example.sandbox.model.database.Person
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _allPersons = repository.getAllPersons()
    private val subscriptions = CompositeDisposable()
    val allPersons: LiveData<List<Person>> = _allPersons

    fun subscribe(addObservable: Observable<Unit>, deleteObservable: Observable<Unit>) {
        Log.d(TAG, "subscribe: ")
        subscriptions.add(addObservable
            .observeOn(Schedulers.io())
            .map { _ -> createPerson() }
            .subscribe(repository::addPerson, this::onError)
        )

        subscriptions.add(
            deleteObservable
                .map { allPersons.value?.first() ?: throw IllegalStateException("Нет данных") }
                .observeOn(Schedulers.io())
                .subscribe(repository::deletePerson, this::onError)
        )
    }

    fun unsubscribe() {
        Log.d(TAG, "unsubscribe: ")
        subscriptions.clear()
    }

    fun onError(e: Throwable) {
        Log.d(Companion.TAG, "onError: $e")
    }


    private fun createPerson(): Person {
        val dateFormat = SimpleDateFormat("HH:mm:ss dd-MM-yyyy")
        return Person(
            firstName = "Date: ",
            secondName = dateFormat.format(GregorianCalendar.getInstance().time)
        )
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}


