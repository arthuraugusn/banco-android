package br.senai.sp.jandira.agenda.dao

import androidx.room.*
import br.senai.sp.jandira.agenda.model.Contact
import kotlinx.coroutines.selects.select

// Mostra pro banco que esta "classe" é uma dao
@Dao
interface ContactDao {

    // Mostra pro banco o que ele precisa fazer com o método a seguir
    // No caso é salvar
    @Insert
    fun save (contact: Contact): Long

    // Mostra pro banco que está classe é para apagar o contato
    @Delete
    fun delete (contact: Contact): Int

    // Atualiza um contato
    @Update
    fun update (contact: Contact): Int

    // Consulta o banco para pegar todos os objetos
    // Seleciona todos os contatos da tabela pelo nome de forma ascendente
    @Query("select * from tbl_contact order by nome asc")
    fun getAll():List<Contact>

    @Query("select * from tbl_contact where id = :id")
    fun getById(id:Int): Contact
}