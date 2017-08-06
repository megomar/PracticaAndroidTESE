package example.tese.practicas.practica01;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SqlActivity extends AppCompatActivity {

    DBManager dbManager;
    ListView listView;
    SimpleCursorAdapter adapter;

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.NOMBRE, DatabaseHelper.CARRERA,
            DatabaseHelper.CURP};

    final int[] to = new int[]{R.id.id, R.id.nombre, R.id.carrera,
            R.id.curp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Agregar Alumno", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(getApplicationContext(), AlumnoActivity.class);
                startActivity(i);
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView)findViewById(R.id.list_wiew);
        listView.setEmptyView(findViewById(R.id.empty_list_item));

        adapter = new SimpleCursorAdapter(this, R.layout.ver_registro, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nombreTextView = (TextView) view.findViewById(R.id.nombre);
                TextView carreraTextView = (TextView) view.findViewById(R.id.carrera);
                TextView curpTextView = (TextView) view.findViewById(R.id.curp);

                Intent mi = new Intent(getApplicationContext(), AlumnoActivity.class);
                mi.putExtra("id", idTextView.getText().toString());
                mi.putExtra("nombre", nombreTextView.getText().toString());
                mi.putExtra("carrera", carreraTextView.getText().toString());
                mi.putExtra("curp", curpTextView.getText().toString());

                startActivity(mi);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
