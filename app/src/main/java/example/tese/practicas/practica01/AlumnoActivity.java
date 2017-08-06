package example.tese.practicas.practica01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlumnoActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText nombreText, carreraText, curpText;
    private Button udateBnt, deleteBnt, insertBnt;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        setTitle("Editar Registro");

        dbManager = new DBManager(this);
        dbManager.open();

        nombreText = (EditText) findViewById(R.id.nombre_edittext);
        carreraText = (EditText)findViewById(R.id.carrera_edittext2);
        curpText = (EditText)findViewById(R.id.curp_edittext3);

        udateBnt = (Button)findViewById(R.id.bnt_update);
        deleteBnt = (Button)findViewById(R.id.bnt_delete);
        insertBnt = (Button)findViewById(R.id.bnt_insert);

        Intent i = getIntent();

        String extra_id = i.getStringExtra("id");
        try {
            _id = Long.parseLong(extra_id);
        }catch (Exception e){

        }

        nombreText.setText(i.getStringExtra("nombre"));
        carreraText.setText(i.getStringExtra("carrera"));
        curpText.setText(i.getStringExtra("curp"));

        udateBnt.setOnClickListener(this);
        deleteBnt.setOnClickListener(this);
        insertBnt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bnt_update:
                dbManager.update(_id, nombreText.getText().toString(), carreraText.getText().toString(),
                        curpText.getText().toString());
                this.returnHome();
                break;
            case  R.id.bnt_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
            case R.id.bnt_insert:
                dbManager.insert(nombreText.getText().toString(), carreraText.getText().toString(),
                        curpText.getText().toString());
                this.returnHome();

        }
    }

    public void returnHome(){
        Intent hi = new Intent(getApplicationContext(), SqlActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(hi);
    }
}
