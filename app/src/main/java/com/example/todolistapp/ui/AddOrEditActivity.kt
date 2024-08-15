package com.example.todolistapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.databinding.ActivityAddOrEditBinding
import com.example.todolistapp.model.Note
import com.example.todolistapp.realmmananger.NoteManager

class AddOrEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddOrEditBinding
    private val realmManager: NoteManager by lazy { NoteManager() }
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        supportActionBar?.title = title

        note = intent.getParcelableExtra("myNote")

        if (note != null) {
            fillEditText(note!!)
        }

        saveOrUpdateNote()
    }

    private fun fillEditText(note: Note) {
        binding.saveBtn.text = "Update"
        binding.addOrEditTitleEd.setText(note.title)
        binding.addOrEditNoteEd.setText(note.content)
    }

    private fun saveOrUpdateNote() {


        binding.saveBtn.setOnClickListener {

            if(binding.addOrEditNoteEd.text.toString().isEmpty()||binding.addOrEditTitleEd.text.toString().isEmpty()){
                Toast.makeText(applicationContext,"fields not be empty",Toast.LENGTH_SHORT).show()
            }
            else {
                val id = note?.id ?: System.currentTimeMillis().toString()
                val updatedNote = Note(
                    id,
                    binding.addOrEditTitleEd.text.trim().toString(),
                    binding.addOrEditNoteEd.text.trim().toString()
                )
                realmManager.addNoteOrUpdate(updatedNote)
                finish()
            }
        }
    }
}
