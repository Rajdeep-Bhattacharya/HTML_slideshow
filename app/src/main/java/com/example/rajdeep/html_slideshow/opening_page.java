package com.example.rajdeep.html_slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class opening_page extends AppCompatActivity {
String[] topics={"calculus","geometry"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        ArrayAdapter adapter = new ArrayAdapter<String>(opening_page.this, R.layout.listviewelement, topics);

       final ListView LV=(ListView) findViewById(R.id.listView);
        LV.setAdapter(adapter);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                String activity_name=(String)LV.getItemAtPosition(position);
                Intent html1=new Intent(opening_page.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("value",activity_name);
                html1.putExtras(bundle);
                startActivity(html1);




            }

        });
    }

}
