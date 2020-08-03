package com.example.sandbox.model

import androidx.lifecycle.LiveData
import com.example.sandbox.model.database.Person
import com.example.sandbox.model.database.PersonDao

class Repository(private val personDao: PersonDao) {

    fun getAllPersons(): LiveData<List<Person>> =
        personDao.getAllPersons()

    fun addPerson(person: Person) =
        personDao.insert(person)

    fun deletePerson(person: Person) =
        personDao.delete(person)

    fun updatePerson(person: Person) =
        personDao.update(person)
}