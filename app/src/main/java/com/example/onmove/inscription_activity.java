package com.example.onmove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class inscription_activity extends AppCompatActivity {

    private EditText edit_nom,edit_email,edit_mdp;
    private Button btn_inscription;
    String[] messages = {"remplir les champs", "Inscription reussi"};
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        InitierComponents();

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = edit_nom.getText().toString();
                String email = edit_email.getText().toString();
                String mdp = edit_mdp.getText().toString();

                if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, messages[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    InscrireUser(view);

                }
            }
        });
    }

    private void InscrireUser(View view){
        String email = edit_email.getText().toString();
        String mdp = edit_mdp.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    enregistrerDonneesUser();

                    Snackbar snackbar = Snackbar.make(view, messages[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    String erreur;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erreur = "Entrez un mot de passe avec au moins 6 caractères ";

                    } catch (FirebaseAuthUserCollisionException e) {
                        erreur = "Ce compte a déjà été enregistré";

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erreur = "Email invalide";

                    } catch (Exception e) {
                        erreur = "Erreur lors de l'enregistrement de l'utilisateur";
                    }

                    Snackbar snackbar = Snackbar.make(view,erreur,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

    }


    private void enregistrerDonneesUser(){
        String nom = edit_nom.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> users = new HashMap<>();
        users.put("nom",nom);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Users").document(userID);
        documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("db","Sauvegarde réussie des données");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error","erreur lors de la sauvegarde des données" + e.toString());

                    }
                });

    }

    private void InitierComponents(){

        edit_nom = findViewById(R.id.edit_nom);
        edit_email = findViewById(R.id.edit_email);
        edit_mdp = findViewById(R.id.edit_mdp);
        btn_inscription = findViewById(R.id.btn_inscription);



    }
}