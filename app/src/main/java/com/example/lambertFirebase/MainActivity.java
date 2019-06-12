package com.example.lambertFirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {
    Button bLogin;
    EditText etEmail,etPassword;
    FirebaseAuth lambert;
    ProgressDialog fff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fff = new ProgressDialog(this);

        lambert = FirebaseAuth.getInstance ( );
        etEmail = (EditText) findViewById ( R.id.etEmail );
        etPassword = (EditText) findViewById ( R.id.etPassword );
        bLogin = (Button) findViewById ( R.id.bLogin );


        bLogin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                validate ( );
            }
        } );


    }

    public void validate() {

        String Email = etEmail.getText ( ).toString ( ).trim ( );
        String Password = etPassword.getText ( ).toString ( ).trim ( );

        if ( Email.isEmpty ( ) ) {
            Toast.makeText ( MainActivity.this, "Enter your Email please", Toast.LENGTH_LONG ).show ( );
        }

        if ( Password.isEmpty ( ) ) {
            Toast.makeText ( MainActivity.this, "Enter your Password please", Toast.LENGTH_LONG ).show ( );
        }

        else {
            fff.setMessage ( "Register is in processing please wait...." );
            fff.show ( );

            lambert.createUserWithEmailAndPassword ( Email, Password ).addOnCompleteListener ( this, new OnCompleteListener<AuthResult>( ) {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    fff.dismiss ( );

                    if ( task.isSuccessful ( ) ) {

                        Toast.makeText ( MainActivity.this, " Registered successfully", Toast.LENGTH_LONG ).show ( );
                    }

                }
            } );
        }


    }


}
