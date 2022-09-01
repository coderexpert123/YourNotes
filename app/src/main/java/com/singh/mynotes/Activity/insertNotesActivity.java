package com.singh.mynotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.singh.mynotes.MainActivity;
import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.R;
import com.singh.mynotes.ViewModel.NotesViewModel;
import com.singh.mynotes.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class insertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding biding;
    String tittle,subtittle,notes;
    NotesViewModel notesViewModel;
    String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        biding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());
        getSupportActionBar().hide();

        biding.purpleProirty.setOnClickListener(v->{
            biding.purpleProirty.setImageResource(R.drawable.ic_baseline_done_24);
            biding.orangeProirty.setImageResource(0);
            biding.redProirty.setImageResource(0);

            priority="1";

        });
        biding.orangeProirty.setOnClickListener(v->{

            biding.orangeProirty.setImageResource(R.drawable.ic_baseline_done_24);
            biding.purpleProirty.setImageResource(0);
            biding.redProirty.setImageResource(0);

            priority="2";

        });

        biding.redProirty.setOnClickListener(v->{

            biding.redProirty.setImageResource(R.drawable.ic_baseline_done_24);
            biding.purpleProirty.setImageResource(0);
            biding.orangeProirty.setImageResource(0);

            priority="3";
        });


        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);


        biding.doneNotesBtn.setOnClickListener(v->{


        tittle=biding.edTittle.getText().toString();
        subtittle=biding.edSubTittle.getText().toString();
        notes=biding.edNotes.getText().toString();
        CreteNotes(tittle,subtittle,notes);

        });

    }

    private void CreteNotes(String tittle, String subtittle, String notes) {

        Date date=new Date();
        CharSequence sequence= android.text.format.DateFormat.format("yyyy-MM-dd",date.getTime());



        Notes notes1=new Notes();
        notes1.notesTittle=tittle;
        notes1.notesSubtittles=subtittle;
        notes1.notes=notes;
        notes1.notesDate=sequence.toString();
        notes1.notesPriority=priority;




        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Notes Creted Successfully...", Toast.LENGTH_SHORT).show();
        finish();
    }

    public static class splash extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            //hide the toolbar
            getSupportActionBar().hide();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(splash.this, MainActivity.class));
                    finish();
                }
            },2000);
        }
    }

}
