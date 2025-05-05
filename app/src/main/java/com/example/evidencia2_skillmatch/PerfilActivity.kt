package com.example.evidencia2_skillmatch;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.evidencia2_skillmatch.R
import com.example.evidencia2_skillmatch.data.User
import com.example.evidencia2_skillmatch.viewmodels.UserViewModel

class PerfilActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Observar cambios en el usuario
        viewModel.allUsers.observe(this) { users ->
            if (users.isNotEmpty()) {
                populateForm(users.last()) //
            }
        }
        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            saveUser()
        }
        findViewById<Button>(R.id.btnVolverInicio).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun populateForm(user: User) {
        findViewById<EditText>(R.id.etNombre).setText(user.firstName)
        findViewById<EditText>(R.id.etApellido).setText(user.lastName)
        findViewById<EditText>(R.id.etEdad).setText(user.age.toString())
        findViewById<EditText>(R.id.etAprender).setText(user.skillsToLearn)
        findViewById<EditText>(R.id.etEnsenar).setText(user.skillsToTeach)
    }

    private fun saveUser() {
        val firstName = findViewById<EditText>(R.id.etNombre).text.toString().trim()
        val lastName = findViewById<EditText>(R.id.etApellido).text.toString().trim()
        val age = findViewById<EditText>(R.id.etEdad).text.toString().toIntOrNull() ?: 0
        val skillsToLearn = findViewById<EditText>(R.id.etAprender).text.toString().trim()
        val skillsToTeach = findViewById<EditText>(R.id.etEnsenar).text.toString().trim()

        // Validaciones básicas
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "Nombre y apellido son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        if (age < 13) {
            Toast.makeText(this, "Debes tener al menos 13 años", Toast.LENGTH_SHORT).show()
            return
        }

        if (skillsToLearn.isEmpty() || skillsToTeach.isEmpty()) {
            Toast.makeText(this, "Debes ingresar habilidades", Toast.LENGTH_SHORT).show()
            return
        }

        val user = User(
            firstName = firstName,
            lastName = lastName,
            age = age,
            skillsToLearn = skillsToLearn,
            skillsToTeach = skillsToTeach
        )

        viewModel.insertUser(user)
        Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show()
        finish()
    }
}