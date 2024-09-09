package com.example.autocheckup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class NotifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        Toolbar toolbar = findViewById(R.id.topAppBar4);
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
        } else if (id == R.id.check) {
            Intent check = new Intent(getApplicationContext(), ChecklistActivity.class);
            startActivity(check);
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