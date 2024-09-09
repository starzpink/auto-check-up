package com.example.autocheckup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.PopupMenu;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private DbHelper db;
    private ListView listView;
    private List<Veiculo> lista;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listView);
        db = new DbHelper(getApplicationContext());

        ArrayList<String> VeiculoList = new ArrayList<>();
        VeiculoList.add("Veiculo 1");
        VeiculoList.add("Veiculo 2");

        adapter = new CustomAdapter(this, VeiculoList);
        listView.setAdapter(adapter);

        consultarVeiculosELigarListView();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Veiculo veiculoSelecionado = lista.get(position);
            abrirDetalhesVeiculo(veiculoSelecionado);
        });

    }
    private void consultarVeiculosELigarListView() {
        lista = db.consultarVeiculos();

        ArrayAdapter<Veiculo> arrayAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(arrayAdapter);
    }
    private void abrirDetalhesVeiculo(Veiculo veiculo) {
        Intent intentDetalhes = new Intent(getApplicationContext(), FichaActivity.class);
        intentDetalhes.putExtra("veiculo", veiculo);
        startActivity(intentDetalhes);
    }


    public void add(View view) {
        Intent registro = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(registro);
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
                    FirebaseAuth.getInstance().signOut();
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                }
                return true;
            });
            popupMenu.show();
        }
    }
}