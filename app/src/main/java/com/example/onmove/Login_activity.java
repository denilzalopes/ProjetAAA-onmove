package com.example.onmove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils; // Ajout de l'import pour TextUtils
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity {

    private TextView text_page_inscription;
    private EditText edit_email, edit_mdp;
    private Button btn_acceder;
    private ProgressBar progressBar;
    String[] messages = {"Remplissez tous les champs", "Login réussi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        InitierComponents();

        text_page_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_activity.this, inscription_activity.class);
                startActivity(intent);
            }
        });

        btn_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString().trim();
                String mdp = edit_mdp.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(mdp)) {
                    afficherSnackbar(view, messages[0]);
                } else {
                    AutentifierUser(view);
                }
            }
        });
    }

    private void AutentifierUser(View view) {
        String email = edit_email.getText().toString().trim();
        String mdp = edit_mdp.getText().toString().trim();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.VISIBLE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    EcranPrincipale();
                                }
                            }, 3000);
                        } else {
                            String erreur = "Erreur lors de la connexion de l'utilisateur";

                            Snackbar snackbar = Snackbar.make(view, erreur, Snackbar.LENGTH_SHORT);
                            afficherSnackbarAvecCouleur(snackbar);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            EcranPrincipale();
        }
    }

    private void EcranPrincipale() {
        Intent intent = new Intent(Login_activity.this, Accueil_activity.class);
        startActivity(intent);
        //finish();
    }

    private void InitierComponents() {
        text_page_inscription = findViewById(R.id.text_page_inscription);
        edit_email = findViewById(R.id.edit_email);
        edit_mdp = findViewById(R.id.edit_mdp);
        btn_acceder = findViewById(R.id.btn_acceder);
        progressBar = findViewById(R.id.progressbar);
    }

    private void afficherSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        afficherSnackbarAvecCouleur(snackbar);
    }

    private void afficherSnackbarAvecCouleur(Snackbar snackbar) {
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }
}
