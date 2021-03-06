package ir.mimrahe.sqliteormshowcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ir.mimrahe.sqliteorm.DatabaseSingleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseSingleton.init(getApplicationContext(), "orm_test", 2);

        NoteModel note1 = new NoteModel("note 1", true);
        note1.saveAndSetId();

        Log.e("note 1", note1.toString());

        note1.setNote("new note 1").update();

        Log.e("new note 1", note1.toString());

        NoteModel note2 = new NoteModel("note 2", false);
        note2.saveAndSetId();

        Log.e("note 2", note2.toString());

        NoteModel note3 = note2.copy();
        note3.saveAndSetId();
        note3.setMyFlag(true).update();

        for(NoteModel note: NoteModel.findAll()){
            Log.e("all notes", note.toString());
        }

        NoteModel note4 = NoteModel.findWithId(note2.getId());
        NoteModel note5 = NoteModel.findWithId(note3.getId());
        Log.e("find note #2 with id", note4.toString());
        Log.e("find note #3 with id", note5.toString());
    }

    @Override
    protected void onDestroy() {
        DatabaseSingleton.closeDatabase();
        super.onDestroy();
    }
}
