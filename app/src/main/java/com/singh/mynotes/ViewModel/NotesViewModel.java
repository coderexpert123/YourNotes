package com.singh.mynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.Repositery.NotesRepositery;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepositery repositery;
    public LiveData<List<Notes>> getallnotes;
    public LiveData<List<Notes>> highttolow;
    public LiveData<List<Notes>> lowtohigh;


    public NotesViewModel(@NonNull Application application) {
        super(application);
        repositery=new NotesRepositery(application);

        getallnotes= repositery.getAllNotes;
        highttolow=repositery.highttolow;
        lowtohigh=repositery.lowtohigh;

    }


  public void insertNote(Notes notes){
        repositery.insertNotes(notes);
    }

    public void deleteNote(int id){
        repositery.deleteNotes(id);
    }

    public void updateNote(Notes notes){
        repositery.updateNotes(notes);
    }
}
