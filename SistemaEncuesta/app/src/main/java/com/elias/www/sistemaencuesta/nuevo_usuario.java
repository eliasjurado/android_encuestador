package com.elias.www.sistemaencuesta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class nuevo_usuario extends AppCompatActivity {

    private static final String TAG = " ";
    TextView tvIniciarSesion;
    EditText edtemail,edtpassword;
    Button btnRegistrarme;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        tvIniciarSesion = (TextView)findViewById(R.id.tvIniciarSesion);
        edtemail=(EditText)findViewById(R.id.edtemail);
        edtpassword=(EditText)findViewById(R.id.edtcontrasena);
        btnRegistrarme = (Button)findViewById(R.id.btnRegistrarme);

        mAuth = FirebaseAuth.getInstance();

        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=edtemail.getText().toString();
                password=edtpassword.getText().toString();


                if(email.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Ingrese email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(email.contains("@")==false)
                {
                    Toast.makeText(getBaseContext(),"Ingrese un email válido",Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Ingrese contraseña",Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(getBaseContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_LONG).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(nuevo_usuario.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(nuevo_usuario.this, "Error en registro",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

        tvIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
            }
        });


    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Intent ointent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(ointent);

        }
    }
}
