package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.agenda.dao.ContactDb
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityNewContactBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonSave.setOnClickListener{
            save()
            val returnActivityMain = Intent(this, MainActivity::class.java)

            startActivity(returnActivityMain)
        }
    }

    private fun save() {

        //Criar o objeto contato
        val contact = Contact()
        contact.nome = binding.editTextName.text.toString()
        contact.email = binding.editTextEmail.text.toString()
        contact.dataNascimento = binding.editTextBirthday.text.toString()
        contact.telefone = binding.editTextPhone.text.toString()

        //Criar uma instancia do repositorio
        contactRepository = ContactRepository(this)
        val id=contactRepository.save(contact)


        Toast.makeText(this, "ID: $id", Toast.LENGTH_LONG).show()
    }
}