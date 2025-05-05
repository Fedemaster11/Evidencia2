package com.example.evidencia2_skillmatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evidencia2_skillmatch.adapters.UserAdapter
import com.example.evidencia2_skillmatch.data.AppDatabase
import com.example.evidencia2_skillmatch.databinding.ActivityUsersBinding
import com.example.evidencia2_skillmatch.utils.categoryKeywords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriaUsuariosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoria = intent.getStringExtra("categoria")
        if (categoria == null) {
            Toast.makeText(this, "Categoría no recibida", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val keywords = categoryKeywords[categoria]
        if (keywords == null) {
            Toast.makeText(this, "Categoría no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        binding.recyclerUsuarios.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val allUsers = withContext(Dispatchers.IO) {
                    userDao.getAllUsers()
                }

                val filtrados = allUsers.filter { user ->
                    keywords.any { palabra ->
                        user.skillsToTeach.contains(palabra, ignoreCase = true) ||
                                user.skillsToLearn.contains(palabra, ignoreCase = true)
                    }
                }

                if (filtrados.isEmpty()) {
                    Toast.makeText(this@CategoriaUsuariosActivity, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show()
                }

                binding.recyclerUsuarios.adapter = UserAdapter(filtrados)

            } catch (e: Exception) {
                Log.e("CategoriaUsuarios", "Error al filtrar usuarios", e)
                Toast.makeText(this@CategoriaUsuariosActivity, "Error al cargar usuarios", Toast.LENGTH_LONG).show()
            }
        }

        // 🟢 Botón para volver al inicio
        binding.btnVolverInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
