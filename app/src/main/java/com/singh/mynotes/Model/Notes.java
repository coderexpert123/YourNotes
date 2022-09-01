package com.singh.mynotes.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_db")
public class Notes {
    @PrimaryKey (autoGenerate = true)
    public int id;
    @ColumnInfo (name = "notes_Tittles")
    public String notesTittle;
    @ColumnInfo (name = "notes_Subtittles")
    public String notesSubtittles;
    @ColumnInfo (name = "notes_Date")
    public String notesDate;
    @ColumnInfo (name = "notes")
    public String notes;
    @ColumnInfo (name = "notes_Priority")
    public String notesPriority;

}
