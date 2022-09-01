package com.singh.mynotes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.singh.mynotes.Model.Notes;

import java.util.List;

        //data access object === Dao
        @androidx.room.Dao
        public interface NotesDao {
        @Query("SELECT * FROM notes_db")
       LiveData<List<Notes>> getAllNotes();


       @Query("SELECT * FROM notes_db ORDER BY notes_Priority DESC")
       LiveData<List<Notes>> highToLow();

       @Query("SELECT * FROM notes_db ORDER BY notes_Priority ASC")
       LiveData<List<Notes>> lowtohigh();

       @Insert
       void insertNotes(Notes... notes);


       @Query("DELETE FROM notes_db WHERE id=:id")
       void deleteNotes(int id);

       @Update
       void updateNotes(Notes notes);
}
