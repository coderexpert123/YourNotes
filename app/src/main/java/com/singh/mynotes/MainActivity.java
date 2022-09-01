package com.singh.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.singh.mynotes.Activity.insertNotesActivity;
import com.singh.mynotes.Adapter.NotesAdapter;
import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.ViewModel.NotesViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FloatingActionButton btn;
NotesViewModel notesViewModel;
RecyclerView notesRecycler;
NotesAdapter adapter;
TextView nofilter,hightolow,lowtohigh;
List<Notes> filternamelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.new_notes_btn);
        notesRecycler = findViewById(R.id.notesRecycler);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilter = findViewById(R.id.nofileter);
        hightolow = findViewById(R.id.howtolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        nofilter.setBackgroundResource(R.drawable.slected_shape);

        nofilter.setOnClickListener(v -> {
            loaddate(0);

            nofilter.setBackgroundResource(R.drawable.slected_shape);
            hightolow.setBackgroundResource(R.drawable.unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.unselected_shape);

        });

        hightolow.setOnClickListener(v -> {
            loaddate(1);

            nofilter.setBackgroundResource(R.drawable.unselected_shape);
            hightolow.setBackgroundResource(R.drawable.slected_shape);
            lowtohigh.setBackgroundResource(R.drawable.unselected_shape);

        });

        lowtohigh.setOnClickListener(v -> {
            loaddate(2);

            nofilter.setBackgroundResource(R.drawable.unselected_shape);
            hightolow.setBackgroundResource(R.drawable.unselected_shape);
            lowtohigh.setBackgroundResource(R.drawable.slected_shape);
        });


        btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, insertNotesActivity.class));
        });

        notesViewModel.getallnotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filternamelist=notes;
            }
        });


    }

    private void loaddate(int i) {
   if (i==0){
       notesViewModel.getallnotes.observe(this, new Observer<List<Notes>>() {
           @Override
           public void onChanged(List<Notes> notes) {
               setAdapter(notes);
               filternamelist=notes;

           }
       });
   }else if(i==1){
       notesViewModel.highttolow.observe(this, new Observer<List<Notes>>() {
           @Override
           public void onChanged(List<Notes> notes) {
               setAdapter(notes);
               filternamelist=notes;

           }
       });
   }else if(i==2){

       notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
           @Override
           public void onChanged(List<Notes> notes) {
               setAdapter(notes);
               filternamelist=notes;

           }
       });
   }
    }

    public void setAdapter(List<Notes> notes){
            notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            adapter =new NotesAdapter(MainActivity.this,notes);
            notesRecycler.setAdapter(adapter);
        }



    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);

                return false;
            }
        });
        return true;

    }

    private void NotesFilter(String s) {

        ArrayList<Notes> filtername=new ArrayList<>();
        for(Notes notes:this.filternamelist){
            if (notes.notesTittle.contains(s) || notes.notesSubtittles.contains(s)){
                filtername.add(notes);

            }
        }
this.adapter.serchNotes(filtername);
    }
}
