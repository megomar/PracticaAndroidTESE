package example.tese.practicas.practica01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonmensaje;
    TextView etiqueta;

    RatingBar ratingBar;
    SeekBar seekBar;
    ListView listView;
    Switch aSwitch;
    ImageButton imageButton1, imageButton2;

    ProgressDialog progressDialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonmensaje = (Button) findViewById(R.id.buttonMsg);
        etiqueta = (TextView) findViewById(R.id.textView);

        imageButton1 = (ImageButton) findViewById(R.id.imageButton);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        aSwitch = (Switch) findViewById(R.id.switch2);
        listView =(ListView) findViewById(R.id.listView);

        seekBarWork();
        switchWork();
        listViewWork();

        botonmensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etiqueta.setText("Hola Mundo");
                etiqueta.setTextSize(40);
                etiqueta.setTextColor(
                        getResources().getColor(R.color.colorPrimary));
                Toast.makeText(getApplicationContext(),
                        "Mi primer App de Android",
                        Toast.LENGTH_SHORT).show();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void listViewWork(){
        String[] datos = new String[]{"Toast", "Toast Personalizado",
            "Snak Bar", "Cuadro de Dialogo", "Notificacion",
                "Progress Dialog"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                       android.R.layout.simple_list_item_1,
                        datos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        Toast.makeText(getApplicationContext(),
                                "Este es un ejemplo de Toast",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_layout,
                                (ViewGroup) findViewById(R.id.toastLinearLayout));

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                        break;
                    case 2:
                        Snackbar.make(view, "Ejemplo de SnackBar",
                                Snackbar.LENGTH_LONG)
                                .setAction("Action",null).show();
                        break;
                    case 3:
                        mostrarAlertDialog();
                        break;
                    case 4:
                        mostrarNotification();
                        break;
                    case 5:
                        mostrarProgressDialog();
                        break;
                }
            }
        });
    }

    public void mostrarAlertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Ejemplo Alert Dialog");
        dialog.setMessage("¿Desea Salir?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void mostrarNotification(){
        int icono = R.mipmap.ic_launcher;
                //android.R.drawable.ic_menu_send;
        CharSequence titulo = "Notificacion de practica";
        CharSequence titulobar = "Notificacion Barra";
        CharSequence texto = "Ejemplo de lanzamiento de Notificacion TESE";

        Notification notification = new Notification.Builder(this)
                .setTicker(titulo)
                .setContentTitle(titulobar)
                .setContentText(texto)
                .setSmallIcon(icono)
                .setAutoCancel(true)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults|= Notification.DEFAULT_LIGHTS;

        NotificationManager nm
                = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }
    public void mostrarProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Descargando Archivo");
        progressDialog.setTitle("Ejemplo de ProgressDialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < progressDialog.getMax()){
                    try{
                        Thread.sleep(200);
                        progressStatus+=5;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setProgress(progressStatus);
                        }
                    });
                }
                progressDialog.dismiss();
                progressStatus = 0;
                progressDialog.setProgress(progressStatus);
            }
        }).start();
    }
    public void seekBarWork(){
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int p;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                etiqueta.setTextSize(progress+30);
                etiqueta.setText("Avance: "+progress+"/"+
                seekBar.getMax());
                p = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                etiqueta.setText("Avance: "+p+"/"+seekBar.getMax());

            }
        });
    }

    public void switchWork(){
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(
                   CompoundButton compoundButton, boolean isChecked) {
               if(isChecked){
                   imageButton1.setEnabled(true);
                   imageButton2.setEnabled(true);
                   etiqueta.setTextSize(20);
                   etiqueta.setTextColor(
                           getResources().getColor(R.color.miVerde));
                   etiqueta.setText("El switch esta encendido");
               }else{
                   imageButton1.setEnabled(false);
                   imageButton2.setEnabled(false);
                   etiqueta.setTextSize(20);
                   etiqueta.setTextColor(Color.GRAY);
                   etiqueta.setText("El switch esta apagado");
               }

           }
       });
    }

    public void imageButtonWork(View v){
        String rv = String.valueOf(ratingBar.getRating());
        etiqueta.setText("Calificacion: " + rv);
        etiqueta.setTextSize(30);
        etiqueta.setTextColor(Color.MAGENTA);
    }

    public void calificar(View v){
        ratingBar.setRating(3);
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
            Intent i = new Intent(this, Activity2.class);
            i.putExtra("direccion_web","http://gmail.com");
            i.putExtra("saludo","OMAR");
            startActivity(i);
            return true;
        }
        if (id == R.id.action_sql) {
            Intent i = new Intent(this, SqlActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
