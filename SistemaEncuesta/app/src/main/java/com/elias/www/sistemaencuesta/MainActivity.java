package com.elias.www.sistemaencuesta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG="MainActivity";

    //Base de datos
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference encuestaRef= db.collection("Encuesta");

    private FirebaseAuth mAuth;

    AdaptadorEncuesta oadEncuesta;
    ArrayList<Encuesta_BE> oLista;
    ListView lstListaEncuesta;

    Dialog oventana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();




        oLista=new ArrayList<Encuesta_BE>();

        //Bucle para traer los documentos de Encuesta

        encuestaRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    return;
                }
                String data="";
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Encuesta_BE encuesta = documentSnapshot.toObject(Encuesta_BE.class);
                    oLista.add(encuesta);
                }
            }
        });
        //Agregado Manual
        //oLista.add(new Encuesta_BE(1,"Clima Organizacional","Encuesta dirigida a obreros.","Elías Jurado","Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5"));
        //oLista.add(new Encuesta_BE(1,"Gestion de Cambios","Encuesta dirigida a administrativos.","Carla Pretel","Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5"));
        oadEncuesta=new AdaptadorEncuesta(oLista,getBaseContext());
        lstListaEncuesta=findViewById(R.id.lstListaEncuesta);
        lstListaEncuesta.setAdapter(oadEncuesta);


        //Responder encuesta haciendo click en el listview
        lstListaEncuesta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Encuesta_BE e = oLista.get(position);
                Intent ointent = new Intent(getBaseContext(),contesta_encuesta.class);
                ointent.putExtra("tit",e.getTitulo());
                ointent.putExtra("com",e.getComentario());
                ointent.putExtra("aut",e.getAutor());
                ointent.putExtra("pre1",e.getPregunta1());
                ointent.putExtra("pre2",e.getPregunta2());
                ointent.putExtra("pre3",e.getPregunta3());
                ointent.putExtra("pre4",e.getPregunta4());
                ointent.putExtra("pre5",e.getPregunta5());
                startActivity(ointent);
            }
        });

        //Boton circular inferior
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                //Acción del botón circular de la parte inferior
                CargarVentanaNuevoEncuesta();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void CargarVentanaNuevoEncuesta()
    {
        oventana = new Dialog(this,R.style.AppTheme);
        oventana.requestWindowFeature(Window.FEATURE_NO_TITLE);
        oventana.setCancelable(true);
        oventana.setContentView(R.layout.activity_nuevo_encuesta);

        oventana.findViewById(R.id.btnagregar).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String email = user.getEmail();

                EditText edtTituloN,edtComentarioN,edtPregunta1N,edtPregunta2N,edtPregunta3N,edtPregunta4N,edtPregunta5N;

                edtTituloN=oventana.findViewById(R.id.edtTituloN);
                edtComentarioN=oventana.findViewById(R.id.edtComentarioN);
                edtPregunta1N=oventana.findViewById(R.id.edtPregunta1N);
                edtPregunta2N=oventana.findViewById(R.id.edtPregunta2N);
                edtPregunta3N=oventana.findViewById(R.id.edtPregunta3N);
                edtPregunta4N=oventana.findViewById(R.id.edtPregunta4N);
                edtPregunta5N=oventana.findViewById(R.id.edtPregunta5N);



                //Firestore intento
                int posicion = oLista.size()+1;
                String edtTituloNu = edtTituloN.getText().toString();
                String edtComentarioNu = edtComentarioN.getText().toString();
                String edtAutorNu = email.toString();
                String edtPregunta1Nu = edtPregunta1N.getText().toString();
                String edtPregunta2Nu = edtPregunta2N.getText().toString();
                String edtPregunta3Nu = edtPregunta3N.getText().toString();
                String edtPregunta4Nu = edtPregunta4N.getText().toString();
                String edtPregunta5Nu = edtPregunta5N.getText().toString();

                Encuesta_BE encuesta = new Encuesta_BE(posicion,edtTituloNu,
                        edtComentarioNu,edtAutorNu,edtPregunta1Nu,
                        edtPregunta2Nu,edtPregunta3Nu,edtPregunta4Nu,
                        edtPregunta5Nu);

                if(edtTituloNu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtComentarioNu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtAutorNu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtPregunta1Nu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtPregunta2Nu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtPregunta3Nu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtPregunta4Nu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtPregunta5Nu.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                    return;
                }

                    encuestaRef.add(encuesta);
                //Firestore intento


                oLista.add(new Encuesta_BE(
                posicion
                ,edtTituloNu
                ,edtComentarioNu
                ,edtAutorNu
                ,edtPregunta1Nu
                ,edtPregunta2Nu
                ,edtPregunta3Nu
                ,edtPregunta4Nu
                ,edtPregunta5Nu
                        )
                );
                oadEncuesta.notifyDataSetChanged();
                oventana.dismiss();

            }
        });

        oventana.findViewById(R.id.btncancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oventana.dismiss();
            }
        });
        oventana.show();

    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu1) {
            CargarVentanaNuevoEncuesta();
        } else if (id == R.id.nav_menu2) {

        } else if (id == R.id.nav_menu3) {

        } /*else if (id == R.id.nav_menu4) {

        } else if (id == R.id.nav_menu5) {

        }*/ else if (id == R.id.nav_menu6) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Quieres salir de la aplicación?");
            builder.setTitle(":(");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
