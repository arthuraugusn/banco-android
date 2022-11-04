package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.adapter.ContactAdapter
import br.senai.sp.jandira.agenda.dao.ContactDao
import br.senai.sp.jandira.agenda.dao.ContactDb
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var rvContact:RecyclerView
    lateinit var adapterContact: ContactAdapter
    private lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.fbNewContact.setOnClickListener {
            val openCadastro = Intent(this, NewContactActivity::class.java)

            startActivity(openCadastro)
        }


    }

    override fun onResume() {
        super.onResume()
        carregarRecyclerView()
    }

    private fun carregarRecyclerView() {
        contactRepository = ContactRepository(this)
        val contact = contactRepository.getAll()
        adapterContact = ContactAdapter(contact, this)

        rvContact =binding.rvAgenda
        rvContact.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvContact.adapter = adapterContact
    }
}