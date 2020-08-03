package com.example.sandbox.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sandbox.model.database.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAllPersons(): LiveData<List<Person>>

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)

    @Update
    fun update(person: Person)
}