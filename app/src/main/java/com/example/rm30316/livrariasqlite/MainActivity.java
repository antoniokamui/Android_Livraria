package com.example.rm30316.livrariasqlite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rm30316.livrariasqlite.dao.LivrosDAO;
import com.example.rm30316.livrariasqlite.model.Livro;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        testeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void testeDB(){
        LivrosDAO dao = new LivrosDAO(this);

        SharedPreferences sp = getSharedPreferences("LIVRARIA", MODE_PRIVATE);
        if(!sp.getBoolean("JAINSERIU", false)) {
            dao.add(new Livro("Android in Action", "Ricardo Lechetta"));
            dao.add(new Livro("Crepusculo", "Stephenie Meyer"));
            dao.add(new Livro("50 tons de Cinza", "E. L. James"));

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("JAINSERIU",true);

        }
        List<Livro> livros = dao.getAll();
        for(Livro l: livros){
            Log.i("LIVRO", l.getTitulo());
        }

    }
}
