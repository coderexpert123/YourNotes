package com.singh.mynotes.Adapter;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.singh.mynotes.Activity.updateNotesActivity;
import com.singh.mynotes.MainActivity;
import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.R;
import com.singh.mynotes.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesVIewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allnotes;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity=mainActivity;
        this.notes=notes;
        allnotes=new ArrayList<>(notes);


    }


    public void serchNotes(List<Notes> filterNmae){
            this.notes=filterNmae;
            notifyDataSetChanged();

    }

    @NonNull
    @Override
    public notesVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesVIewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesVIewHolder holder, int position) {


            Notes note=notes.get(position);


        switch (note.notesPriority) {
            case "1":
                holder.notesp.setBackgroundResource(R.drawable.shape_green);
                break;
            case "2":
                holder.notesp.setBackgroundResource(R.drawable.shape_blue);
                break;
            case "3":
                holder.notesp.setBackgroundResource(R.drawable.shape_red);
                break;
        }
            holder.tittle.setText(note.notesTittle);
            holder.mainnotes.setText(note.notes);
            holder.substittle.setText(note.notesSubtittles);
            holder.date.setText(note.notesDate);

            holder.itemView.setOnClickListener(v->{

                Intent i=new Intent(mainActivity, updateNotesActivity.class);

                i.putExtra("id",note.id);
                i.putExtra("notesTittle",note.notesTittle);
                i.putExtra("notesSubtittles",note.notesSubtittles);
                i.putExtra("notesDate",note.notesDate);
                i.putExtra("notes",note.notes);
                i.putExtra("notesPriority",note.notesPriority);



                mainActivity.startActivity(i);

            });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesVIewHolder extends RecyclerView.ViewHolder {
        TextView tittle,substittle,date, mainnotes;
        View notesp;


        public notesVIewHolder(@NonNull View itemView) {
            super(itemView);

            notesp=itemView.findViewById(R.id.notesp);
            tittle=itemView.findViewById(R.id.notes_tittle_rv);
            substittle=itemView.findViewById(R.id.notes_subtittle_rv);

            mainnotes=itemView.findViewById(R.id.notes_rv);
            date=itemView.findViewById(R.id.notes_date);





        }
    }
}
