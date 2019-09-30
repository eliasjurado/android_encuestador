package com.elias.www.sistemaencuesta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class contesta_encuesta extends AppCompatActivity {
    private static final String TAG="contesta_encuesta";
    //Base de datos
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference respuestaRef= db.collection("Respuesta");
    private FirebaseAuth mAuth;

    TextView tvtituloc,tvcomentarioc,tvautorc,tvpregunta1c,tvpregunta2c,tvpregunta3c,tvpregunta4c,tvpregunta5c;
    EditText edtrespuesta1c,edtrespuesta2c,edtrespuesta3c,edtrespuesta4c,edtrespuesta5c;

    ArrayList<Respuesta_BE> oListaRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contesta_encuesta);

        mAuth = FirebaseAuth.getInstance();

        tvtituloc = (TextView)findViewById(R.id.tvtituloc);
        tvcomentarioc = (TextView)findViewById(R.id.tvcomentarioc);
        tvautorc = (TextView)findViewById(R.id.tvautorc);
        tvpregunta1c = (TextView)findViewById(R.id.tvpregunta1c);
        tvpregunta2c = (TextView)findViewById(R.id.tvpregunta2c);
        tvpregunta3c = (TextView)findViewById(R.id.tvpregunta3c);
        tvpregunta4c = (TextView)findViewById(R.id.tvpregunta4c);
        tvpregunta5c = (TextView)findViewById(R.id.tvpregunta5c);

        edtrespuesta1c= (EditText)findViewById(R.id.edtrespuesta1c);
        edtrespuesta2c= (EditText)findViewById(R.id.edtrespuesta2c);
        edtrespuesta3c= (EditText)findViewById(R.id.edtrespuesta3c);
        edtrespuesta4c= (EditText)findViewById(R.id.edtrespuesta4c);
        edtrespuesta5c= (EditText)findViewById(R.id.edtrespuesta5c);

        oListaRes=new ArrayList<Respuesta_BE>();
        MostrarDatos();
    }

    public void MostrarDatos(){
        Bundle odatos = getIntent().getExtras();

        tvtituloc.setText("Título     :  "+odatos.getString("tit"));
        tvcomentarioc.setText("Objetivo :  "+odatos.getString("com"));
        tvautorc.setText("Autor      :  "+odatos.getString("aut"));
        tvpregunta1c.setText("1. "+odatos.getString("pre1"));
        tvpregunta2c.setText("2. "+odatos.getString("pre2"));
        tvpregunta3c.setText("3. "+odatos.getString("pre3"));
        tvpregunta4c.setText("4. "+odatos.getString("pre4"));
        tvpregunta5c.setText("5. "+odatos.getString("pre5"));
    }

    public void GrabarDatos(View v){

        Bundle odatos = getIntent().getExtras();

        EditText edtrespuesta1c= findViewById(R.id.edtrespuesta1c);
        EditText edtrespuesta2c= findViewById(R.id.edtrespuesta2c);
        EditText edtrespuesta3c= findViewById(R.id.edtrespuesta3c);
        EditText edtrespuesta4c= findViewById(R.id.edtrespuesta4c);
        EditText edtrespuesta5c= findViewById(R.id.edtrespuesta5c);
        //Respuestas
        String rpta1 = edtrespuesta1c.getText().toString();
        String rpta2 = edtrespuesta2c.getText().toString();
        String rpta3 = edtrespuesta3c.getText().toString();
        String rpta4 = edtrespuesta4c.getText().toString();
        String rpta5 = edtrespuesta5c.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        Respuesta_BE respuesta = new Respuesta_BE(
                oListaRes.size()+1
                ,odatos.getString("tit")
                ,odatos.getString("com")
                ,odatos.getString("aut")
                ,email.toString()
                ,odatos.getString("pre1")
                ,rpta1
                ,odatos.getString("pre2")
                ,rpta2
                ,odatos.getString("pre3")
                ,rpta3
                ,odatos.getString("pre4")
                ,rpta4
                ,odatos.getString("pre5")
                ,rpta5
        );
        if(rpta1.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Todos las respuestas son requeridas",Toast.LENGTH_LONG).show();
            return;
        }
        if(rpta2.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Todos las respuestas son requeridas",Toast.LENGTH_LONG).show();
            return;
        }
        if(rpta3.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Todos las respuestas son requeridas",Toast.LENGTH_LONG).show();
            return;
        }
        if(rpta4.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Todos las respuestas son requeridas",Toast.LENGTH_LONG).show();
            return;
        }
        if(rpta5.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Todos las respuestas son requeridas",Toast.LENGTH_LONG).show();
            return;
        }
        respuestaRef.add(respuesta);

        Toast.makeText(this, "Respuesta guardada", Toast.LENGTH_SHORT).show();
        Intent ointent  = new Intent(getBaseContext(),MainActivity.class);
        startActivity(ointent);

    }

    public void VolverMain(View v){
        Intent ointent  = new Intent(getBaseContext(),MainActivity.class);
        startActivity(ointent);
    }


}
