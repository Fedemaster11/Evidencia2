package com.example.evidencia2_skillmatch.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsersLiveData()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    fun getAllUsersBlocking(): List<User> {
        return userDao.getAllUsersBlocking()
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
    suspend fun getAllUsersDirect(): List<User> {
        return userDao.getAllUsers()
    }


    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }


}
