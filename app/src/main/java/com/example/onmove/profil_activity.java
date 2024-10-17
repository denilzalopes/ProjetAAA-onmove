package com.example.onmove;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profil_activity extends AppCompatActivity {

    private TextView nomUser, emailUser;
    private Button btn_SeDeconnecter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //getSupportActionBar().hide();

        initierComposants();

        btn_SeDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(profil_activity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {
                    nomUser.setText(documentSnapshot.getString("nom"));
                    emailUser.setText(email);
                }
            }
        });
    }

    private void initierComposants() {
        nomUser = findViewById(R.id.textNomUser);
        emailUser = findViewById(R.id.textEmailUser);
        btn_SeDeconnecter = findViewById(R.id.btn_SeDeconnecter);
    }
}
