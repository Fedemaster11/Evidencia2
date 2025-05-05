package com.example.evidencia2_skillmatch.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users")
    fun getAllUsersBlocking(): List<User>


    @Query("SELECT * FROM users")
    fun getAllUsersLiveData(): LiveData<List<User>>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

}
