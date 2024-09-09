package com.example.autocheckup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;

public class ChecklistActivity extends AppCompatActivity {
    CheckBox checkBoxOleo, checkBoxFluidoTrans, checkBoxFreio, checkBoxDirecao, checkBoxPneus, checkBoxFreios, checkBoxEletrico, checkBoxRefri, checkBoxMangueira, checkBoxCNH, checkBoxCRLV, checkBoxTriangulo, checkBoxFerramentas, checkBoxLanterna, checkBoxCabos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Toolbar toolbar = findViewById(R.id.topAppBar5);

        checkBoxOleo = findViewById(R.id.checkBoxOleo);
        checkBoxFluidoTrans = findViewById(R.id.checkBoxFluidoTrans);
        checkBoxFreio = findViewById(R.id.checkBoxFreio);
        checkBoxDirecao = findViewById(R.id.checkBoxDirecao);
        checkBoxPneus = findViewById(R.id.checkBoxPneus);
        checkBoxFreios = findViewById(R.id.checkBoxFreios);
        checkBoxEletrico = findViewById(R.id.checkBoxEletrico);
        checkBoxRefri = findViewById(R.id.checkBoxRefri);
        checkBoxMangueira = findViewById(R.id.checkBoxMangueira);
        checkBoxCNH = findViewById(R.id.checkBoxCNH);
        checkBoxCRLV = findViewById(R.id.checkBoxCRLV);
        checkBoxTriangulo = findViewById(R.id.checkBoxTriangulo);
        checkBoxFerramentas = findViewById(R.id.checkBoxFerramentas);
        checkBoxLanterna = findViewById(R.id.checkBoxLanterna);
        checkBoxCabos = findViewById(R.id.checkBoxCabos);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_app_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notification) {
            Intent notif = new Intent(getApplicationContext(), NotifActivity.class);
            startActivity(notif);
            return true;
        } else if (id == R.id.action_edit_vehicles || id == R.id.action_logout) {
            showSubMenu(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showSubMenu(MenuItem item) {
        View view = findViewById(item.getItemId());
        if (view != null) {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.submenu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(subMenuItem -> {
                // Lógica para lidar com a seleção do submenu
                int subMenuId = subMenuItem.getItemId();
                if(subMenuId == R.id.action_logout){
                    performLogout();
                }
                return true;
            });
            popupMenu.show();
        }
    }
    private void performLogout() {

        FirebaseAuth.getInstance().signOut();
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }
}