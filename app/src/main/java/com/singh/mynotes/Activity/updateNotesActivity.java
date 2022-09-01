
package com.singh.mynotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.singh.mynotes.Model.Notes;
import com.singh.mynotes.R;
import com.singh.mynotes.ViewModel.NotesViewModel;
import com.singh.mynotes.databinding.ActivityInsertNotesBinding;
import com.singh.mynotes.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class updateNotesActivity extends AppCompatActivity {
ActivityUpdateNotesBinding biding;
String priority="1";
String stittle,ssubtittle,snotes,sdate,sproeity;
NotesViewModel notesViewModel;
int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding=ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());

        sid=getIntent().getIntExtra("id",0);
        stittle=getIntent().getStringExtra("notesTittle");
        ssubtittle=getIntent().getStringExtra("notesSubtittles");
        snotes=getIntent().getStringExtra("notes");

        sdate=getIntent().getStringExtra("notesDate");
        sproeity=getIntent().getStringExtra("notesPriority");

        biding.upTittle.setText(stittle);
        biding.upSubTittle.setText(ssubtittle);
        biding.upNotes.setText(snotes);
if (sproeity.equals("1")){
    biding.purpleProirty.setImageResource(R.drawable.ic_baseline_done_24);
}else if(sproeity.equals("1")){

    biding.orangeProirty.setImageResource(R.drawable.ic_baseline_done_24);
}
else if(sproeity.equals("3")){

    biding.redProirty.setImageResource(R.drawable.ic_baseline_done_24);
}
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


        biding.updateNotesBtn.setOnClickListener(v->{



            String tittle=biding.upTittle.getText().toString();
            String  subtittle=biding.upSubTittle.getText().toString();
            String notes=biding.upNotes.getText().toString();

            updateNotes(tittle,subtittle,notes);

        });


        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
    }

    private void updateNotes(String tittle, String subtittle, String notes) {
        Date date=new Date();
        CharSequence sequence= android.text.format.DateFormat.format("yyyy-MM-dd",date.getTime());

        Notes updateNotes=new Notes();
        updateNotes.id=sid;
        updateNotes.notesTittle=tittle;
        updateNotes.notesSubtittles=subtittle;
        updateNotes.notes=notes;
        updateNotes.notesDate=sequence.toString();
        updateNotes.notesPriority=priority;

        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this, "Notes Updated Successfully...", Toast.LENGTH_SHORT).show();
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.delete_emnu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete) {
            BottomSheetDialog sheetDialog=new BottomSheetDialog(updateNotesActivity.this);
            View view= LayoutInflater.from(updateNotesActivity.this).
                    inflate(R.layout.deletelayoutresorce,(LinearLayout)findViewById(R.id.bottom_shape));
                sheetDialog.setContentView(view);
                sheetDialog.show();

            TextView yes,no;

            yes=view.findViewById(R.id.delete_yes);
            no=view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v->{

                notesViewModel.deleteNote(sid);
                finish();
                Toast.makeText(this, "Notes Deleted SUccesfuly !", Toast.LENGTH_SHORT).show();

            });

            no.setOnClickListener(v->{
                sheetDialog.dismiss();

            });

        }
        return true;
    }
}