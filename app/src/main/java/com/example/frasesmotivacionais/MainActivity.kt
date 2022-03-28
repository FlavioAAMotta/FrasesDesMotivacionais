package com.example.frasesmotivacionais

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.frasesmotivacionais.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var category: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()

        val name = SecurityPreferences(this).getString("USER_NAME")
        binding.textUserName.text = "Olá $name"
        handleFilter(R.id.image_all)
        gerarFraseNova()

        binding.buttonNovaFrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageFaces.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_nova_frase){
            gerarFraseNova()
        }else if(view.id in listOf(R.id.image_all, R.id.image_faces, R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {
        unselectAll()
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.white))
                category = 1
            }
            R.id.image_faces -> {
                binding.imageFaces.setColorFilter(ContextCompat.getColor(this,R.color.white))
                category = 2
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.white))
                category = 3

            }
        }
    }

    private fun unselectAll() {
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageFaces.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
    }

    private fun gerarFraseNova() {
        val phrase = Mock().getPhrase(category)
        binding.textFrase.text = phrase
    }
}