package com.example.abdallahsamara.ex2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private ListView mListView;
    private CustomListAdapter mCustomListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        mListView = (ListView) findViewById(R.id.listView);

        mCustomListAdapter = new CustomListAdapter(MainActivity.this);

        mListView.setAdapter(mCustomListAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogWith(mCustomListAdapter.getItem(position), position);
                return true;
            }
        });
    }

    private void showDialogWith(final String text, final int position) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(text)
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mCustomListAdapter.removeItemAt(position);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void appendTextToListView() {
        String textToAdd = mEditText.getText().toString();
        mCustomListAdapter.addItem(textToAdd);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                appendTextToListView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
