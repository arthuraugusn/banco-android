package br.senai.sp.jandira.agenda.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.agenda.model.Contact

// Cria o acesso ao banco de dados
@Database(entities = [Contact::class], version = 1)
abstract class ContactDb: RoomDatabase() {

    // Função abstrata pois ela só representa a interface
    // Classe astrata não pode ser instanciada
    abstract fun contactDao():ContactDao

    companion object{

        private lateinit var instance:ContactDb

        fun getDataBase(context: Context):ContactDb{
            //:: devolve uma instancia existente, podendo adentrar nos métodos dela
            if(!::instance.isInitialized){
                instance = Room.databaseBuilder(context, ContactDb::class.java,"db_agenda").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}