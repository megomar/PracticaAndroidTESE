package example.tese.practicas.practica01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    EditText texto2;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        texto2 = (EditText)findViewById(R.id.editText2);
        textView2 = (TextView) findViewById(R.id.textView2);

        Intent gi = getIntent();
        textView2.setText("Hola " + gi.getStringExtra("saludo"));
        texto2.setText(gi.getStringExtra("direccion_web"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void bntWeb(View v){
        String urlWeb = texto2.getText().toString();
        if(urlWeb.isEmpty()){
            urlWeb = "http://www.unam.mx";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlWeb));
        startActivity(i);
    }
    public void bntTel(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);
        startActivity(i);
    }
    public void bntCall(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("tel:5529536117"));
        startActivity(i);
    }
    public void bntMaps(View v){
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:19.6922729,-98.84569220?z17"));
        startActivity(i);
    }
    public void bntMail(View v){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,"Prueba de Correo");
        i.putExtra(Intent.EXTRA_TEXT,"Este es un correo de Prueba TESE");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"sebastian@arcelia.net",
                "omendoza@arcelia.net", "noel.andres@hotmail.com",
                "carlosr250700@gmail.com", "rsalazar_jr@hotmail.com"});
        try{
            startActivity(
                    Intent.createChooser(
                            i,"Quien puede enviar un email?")
            );
        }catch (android.content.ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
    public void bntSms(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.putExtra("sms_body","Hola Mundo TEP");
        i.putExtra("address", new String("5529536117"));
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
    }
    public void bntAlarm(View v){
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, texto2.getText().toString())
                .putExtra(AlarmClock.EXTRA_HOUR, 10)
                .putExtra(AlarmClock.EXTRA_MINUTES, 30);
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }
    public void bntCamera(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }
}
