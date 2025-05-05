package com.example.evidencia2_skillmatch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val age: Int,
    @ColumnInfo(name = "skills_to_learn") val skillsToLearn: String,
    @ColumnInfo(name = "skills_to_teach") val skillsToTeach: String
)
