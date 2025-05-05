package com.example.evidencia2_skillmatch.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.evidencia2_skillmatch.data.AppDatabase
import com.example.evidencia2_skillmatch.data.User
import com.example.evidencia2_skillmatch.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    suspend fun getAllUsersDirect(): List<User> {
        return repository.getAllUsersDirect()
    }

    suspend fun getAllUsers(): List<User> {
        return repository.getAllUsers()
    }
}
