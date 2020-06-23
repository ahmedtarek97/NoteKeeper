package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(NoteListActivity.this,NoteActivity.class));
            }
        });

        intializeDisplayContent();
    }

    private void intializeDisplayContent() {
       final ListView listNotes = findViewById(R.id.list_notes);
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        listNotes.setAdapter(adapterNotes);
        //AdapterView.OnItemClickListener is called an anonymous class
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //the parameter position is the position of where the user clicked
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //to launch other activity we need an intent
                // we call this code from the NodeList activity  and Activity can be based as context
                // this  should refer to the NoteListActivity class but it actually refers to the anonymous class so we should not use it
                // we instead use NoteListActivity.this which refers to NoteListActivity
                // second parameter we want a class information about the activity we will launch
                Intent intent = new Intent(NoteListActivity.this,NoteActivity.class);
                // the note at the position of the click
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                // package the clicked note in the intent and send it to the node activity
                intent.putExtra(NoteActivity.NOTE_POSITION,position);
                startActivity(intent);


            }
        });
    }
}