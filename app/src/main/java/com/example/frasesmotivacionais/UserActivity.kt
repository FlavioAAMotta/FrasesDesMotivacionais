package com.example.frasesmotivacionais

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frasesmotivacionais.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()
        binding.buttonSalvar.setOnClickListener(this)

        verifyUserName()
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString("USER_NAME")
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_salvar) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString("USER_NAME", name)
            startActivity(Intent(this, MainActivity::class.java))
            finish() //Tira da memória a aplicação
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show()
        }
    }
}