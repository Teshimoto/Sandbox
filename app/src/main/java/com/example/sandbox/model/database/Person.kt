package com.example.sandbox.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey
    @ColumnInfo(name = "person_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "second_name")
    var secondName: String) {

    override fun toString(): String {
        return "$firstName $secondName"
    }
}