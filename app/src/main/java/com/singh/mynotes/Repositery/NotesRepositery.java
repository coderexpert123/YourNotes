package com.singh.mynotes.Repositery;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.singh.mynotes.Dao.NotesDao;
import com.singh.mynotes.Database.NotesDatabase;
import com.singh.mynotes.Model.Notes;

import java.util.List;

public class NotesRepositery {


    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highttolow;
    public LiveData<List<Notes>> lowtohigh;


        public NotesRepositery(Application application){

            NotesDatabase database=NotesDatabase.getDatabaseinstance(application);
            notesDao=database.notesDao();

            getAllNotes=notesDao.getAllNotes();
            highttolow=notesDao.highToLow();
            lowtohigh=notesDao.lowtohigh();




        }


        //insert notes
       public void insertNotes(Notes notes){

                notesDao.insertNotes(notes);
        }
        //delete Notes
       public void deleteNotes(int id){

            notesDao.deleteNotes(id);
        }
        //update Notes
       public void updateNotes(Notes notes){

            notesDao.updateNotes(notes);
        }
}
