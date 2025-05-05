package com.example.evidencia2_skillmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.evidencia2_skillmatch.data.User
import com.example.evidencia2_skillmatch.viewmodels.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // ✅ Insertar 2 usuarios de prueba si no hay ninguno
        lifecycleScope.launch {
            val usuarios = viewModel.getAllUsersDirect()
            if (usuarios.isEmpty()) {
                viewModel.insertUser(User(
                    firstName = "Eugenio",
                    lastName = "Macías",
                    age = 22,
                    skillsToLearn = "guitarra, canto",
                    skillsToTeach = "piano"
                ))
                viewModel.insertUser(User(
                    firstName = "Laura",
                    lastName = "Gómez",
                    age = 24,
                    skillsToLearn = "piano",
                    skillsToTeach = "guitarra"
                ))
            }
        }


        // 🔘 Botón Perfil
        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            checkUsersExist()
        }

        // 🎯 Botones de categorías
        findViewById<Button>(R.id.btnMusica).setOnClickListener {
            abrirCategoria("Música")
        }
        findViewById<Button>(R.id.btnDeportes).setOnClickListener {
            abrirCategoria("Deportes")
        }
        findViewById<Button>(R.id.btnCocina).setOnClickListener {
            abrirCategoria("Cocina")
        }
        findViewById<Button>(R.id.btnAcademico).setOnClickListener {
            abrirCategoria("Académico")
        }
        findViewById<Button>(R.id.btnServicios).setOnClickListener {
            abrirCategoria("Servicios")
        }
        findViewById<Button>(R.id.btnAsesorias).setOnClickListener {
            abrirCategoria("Asesorías")
        }
        findViewById<Button>(R.id.btnIdiomas).setOnClickListener {
            abrirCategoria("Idiomas")
        }
        findViewById<Button>(R.id.btnTecnologia).setOnClickListener {
            abrirCategoria("Tecnología")
        }
    }

    private fun abrirCategoria(nombre: String) {
        val intent = Intent(this, CategoriaUsuariosActivity::class.java)
        intent.putExtra("categoria", nombre)
        startActivity(intent)
    }

    private fun checkUsersExist() {
        viewModel.allUsers.observe(this) { users ->
            if (users.isEmpty()) {
                showProfileCreationDialog()
            } else {
                openProfileActivity()
            }
        }
    }

    private fun showProfileCreationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Perfil no encontrado")
            .setMessage("Debes crear tu perfil primero")
            .setPositiveButton("Crear perfil") { _, _ ->
                openProfileActivity()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun openProfileActivity() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }
}
