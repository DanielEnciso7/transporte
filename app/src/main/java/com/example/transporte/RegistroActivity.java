package com.example.transporte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener
{
    public EditText TextEmail2;
    public EditText TextPassword2;
    public EditText TextNombre;
    public EditText TextSexo;
    public EditText TextEdad;
    public RadioButton RadioDescuento;
    public Button BotonRegister;
    public ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference Users;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Users = FirebaseDatabase.getInstance().getReference("model_usuarios");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);



        TextNombre = findViewById(R.id.txtNombreCompleto);
        TextEdad = (EditText) findViewById(R.id.txtEdad);
        TextSexo = (EditText) findViewById(R.id.txtSexo);
        TextEmail2 = (EditText) findViewById(R.id.txtEmail2);
        TextPassword2 = (EditText) findViewById(R.id.txtPassword2);
        RadioDescuento = (RadioButton) findViewById(R.id.rb_descuento);

        BotonRegister = (Button) findViewById(R.id.btnRegister);

        BotonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Registro");

    }


    private void registrarUsuario()
    {
        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail2.getText().toString().trim();
        String password  = TextPassword2.getText().toString().trim();
        String nom  = TextNombre.getText().toString().trim();
        String edad  = TextEdad.getText().toString().trim();
        String sexo  = TextSexo.getText().toString().trim();
        String descuento = RadioDescuento.getText().toString().trim();



        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(nom))
        {
            Toast.makeText(this,"El nombre no puede estar vacío",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(edad))
        {
            Toast.makeText(this,"La edad no puede estar vacía",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(sexo))
        {
            Toast.makeText(this,"El sexo no puede estar vacío",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            String id = Users.push().getKey();
            model_usuarios user = new model_usuarios(id, nom, edad, sexo, descuento);
            Users.child("Usuarios").child(id).setValue(user);
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //registrar el usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //verificación
                        if(task.isSuccessful()){

                            Toast.makeText(RegistroActivity.this,"Se ha registrado el usuario con el email: "+ TextEmail2.getText(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(RegistroActivity.this,"Ese usuario ya existe",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(RegistroActivity.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

        Intent principal = new Intent(getApplication(), LoginActivity.class);
        startActivity(principal);
    }

    public void onClick(View view)
    {
        registrarUsuario();
    }
}
