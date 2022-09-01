package com.singh.mynotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.Dao.NotesDao;

@Database(entities = {Notes.class},version = 1)
    public abstract class NotesDatabase extends RoomDatabase {


        public abstract NotesDao notesDao();
        public static NotesDatabase INSTANCE;


public static NotesDatabase getDatabaseinstance(Context context){

    if (INSTANCE==null){
        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                NotesDatabase.class,"notes_db").allowMainThreadQueries().build();


    }
    return INSTANCE;

}
}
