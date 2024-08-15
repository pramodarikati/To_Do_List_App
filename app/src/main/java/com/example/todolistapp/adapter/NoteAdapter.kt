package com.example.todolistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.model.Note
import io.realm.RealmResults

class NoteAdapter(var notes: RealmResults<Note>,val onCallBack: OnCallBack) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun setData(notes: RealmResults<Note>) {
        this.notes = notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item, null, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]!!
        holder.title.text = note.title ?:"A7A"
        holder.content.text = note.content ?:"A7A"
        holder.btnMore.setOnClickListener { onCallBack.onCallBack(holder.btnMore, notes[position]!!) }
        holder.btn_edit.setOnClickListener { onCallBack.onItemSelected(notes[position]!!) }
    }

    override fun getItemCount(): Int = notes.size


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.noteTitle)
        val content:TextView = itemView.findViewById(R.id.noteContent)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
        val btn_edit: ImageView = itemView.findViewById(R.id.btn_edit)
    }

    interface OnCallBack{
        fun onCallBack(view: View,note:Note)
        fun onItemSelected(note: Note)
    }
}