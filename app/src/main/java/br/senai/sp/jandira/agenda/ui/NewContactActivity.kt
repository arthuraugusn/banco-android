package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository
    private var id = 0
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityNewContactBinding.inflate(layoutInflater)

        setContentView(binding.root)
        contact = Contact()

        id = intent.getIntExtra("idContact", 0)

        if(id > 0){
            binding.buttonExcluir.visibility = View.VISIBLE
            carregarContato()
        }

        binding.buttonExcluir.setOnClickListener{
            deleteContact()
        }

        binding.buttonSave.setOnClickListener{
            save()
        }

    }

    private fun deleteContact() {
        val confirmacao = AlertDialog.Builder(this)
        confirmacao.setTitle("Exclusão")
        confirmacao.setMessage("Confirma a exclusão do(a) ${contact.nome}")
        confirmacao.setPositiveButton("Sim"){_,_->
            contactRepository.delete(contact)
            finish()
        }

        confirmacao.setNegativeButton("Não"){_,_->
        }

        confirmacao.show()
    }

    private fun carregarContato() {
        contactRepository = ContactRepository(this)
        contact = contactRepository.getById(id)

        //Alterar texto do edit text dessa maneira
        binding.editTextEmail.setText(contact.email)
        binding.editTextName.setText(contact.nome)
        binding.editTextPhone.setText(contact.telefone)
        binding.editTextBirthday.setText(contact.dataNascimento)

        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
    }

    private fun save() {

        //Criar o objeto contato
        contact.nome = binding.editTextName.text.toString()
        contact.email = binding.editTextEmail.text.toString()
        contact.dataNascimento = binding.editTextBirthday.text.toString()
        contact.telefone = binding.editTextPhone.text.toString()

        //Criar uma instancia do repositorio
        contactRepository = ContactRepository(this)
        if(id>0){
            contactRepository.update(contact)

        }else{
            val id=contactRepository.save(contact)
        }

        Toast.makeText(this, "ID: $id", Toast.LENGTH_LONG).show()

        finish()
    }
}