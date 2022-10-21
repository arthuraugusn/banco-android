package br.senai.sp.jandira.agenda.model

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

// Definindo nome da tabela no Banco de Dados local
@Entity(tableName = "tbl_contact")
class Contact {

    // Definindo chave primária da tabela
    // auto-generate == gerar automaticamente
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var nome = ""
    var telefone = ""
    var email = ""

    // Definindo como a data de nascimento vai ser digitada no banco
    @ColumnInfo(name = "data_nascimento")
    var dataNascimento: LocalDate? = null

    var foto: Drawable? = null
}