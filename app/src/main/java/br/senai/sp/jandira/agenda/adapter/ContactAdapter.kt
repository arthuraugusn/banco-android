package br.senai.sp.jandira.agenda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.ui.MainActivity
import br.senai.sp.jandira.agenda.ui.NewContactActivity

class ContactAdapter(var contactList: List<Contact>, var context: Context):RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    fun updateContactList(newContactList: List<Contact>){
        this.contactList = newContactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        //Maneira de pegar o contato de determinada posição
        val contact = contactList[position]

        holder.tvNome.text = contact.nome
        holder.tvEmail.text = contact.email
        holder.tvTelefone.text = contact.telefone
        holder.tvInicial.text = contact.nome.substring(0,1)
        holder.cardViewContact.setOnClickListener{
            val intent = Intent(context, NewContactActivity::class.java)
            intent.putExtra("idContact", contact.id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        //Retorna quantidade de itens que existem na lista
        return contactList.size
    }

    class ContactHolder(view:View):RecyclerView.ViewHolder(view){
        val tvNome =view.findViewById<TextView>(R.id.tv_nome)
        val tvEmail = view.findViewById<TextView>(R.id.tv_email)
        val tvInicial = view.findViewById<TextView>(R.id.tv_inicial)
        val tvTelefone = view.findViewById<TextView>(R.id.tv_telefone)
        val cardViewContact: CardView = view.findViewById(R.id.cv_contato)
    }
}