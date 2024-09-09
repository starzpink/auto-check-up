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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class FichaActivity extends AppCompatActivity {

    private TextView apelido_veiculo, modelo, ano, placa, media_km, km_atual, oleo_data, fluido_t_data, fluido_f_data, fluido_dh_data, troca_pneus_data, freios_data, seletrico_data, sref_data, cor_mang_data, rev_geral_data, ultabastecimento, t_combustivel, qt_litros;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        apelido_veiculo = findViewById(R.id.apelido_veiculo);
        modelo = findViewById(R.id.modelo);
        ano = findViewById(R.id.ano);
        placa = findViewById(R.id.placa);
        media_km = findViewById(R.id.media_km);
        km_atual = findViewById(R.id.km_atual);
        oleo_data = findViewById(R.id.oleo_data);
        fluido_t_data = findViewById(R.id.fluido_t_data);
        fluido_f_data = findViewById(R.id.fluido_f_data);
        fluido_dh_data = findViewById(R.id.fluido_dh_data);
        troca_pneus_data = findViewById(R.id.troca_pneus_data);
        freios_data = findViewById(R.id.freios_data);
        seletrico_data = findViewById(R.id.seletrico_data);
        sref_data = findViewById(R.id.sref_data);
        cor_mang_data = findViewById(R.id.cor_mang_data);
        rev_geral_data = findViewById(R.id.rev_geral_data);
        ultabastecimento = findViewById(R.id.ultabastecimento);
        t_combustivel = findViewById(R.id.t_combustivel);
        qt_litros = findViewById(R.id.qt_litros);


        Toolbar toolbar = findViewById(R.id.topAppBar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            veiculo = (Veiculo) intent.getSerializableExtra("veiculo");
            if (veiculo != null) {
                apelido_veiculo.setText(veiculo.getApelido());
                modelo.setText(veiculo.getModelo_carro());
                ano.setText(veiculo.getAno_carro());
                placa.setText(veiculo.getPlaca_carro());
                media_km.setText(veiculo.getMediakm());
                km_atual.setText(veiculo.getKmatual());
                oleo_data.setText(veiculo.getOleodata());
                fluido_t_data.setText(veiculo.getFlutdata());
                fluido_f_data.setText(veiculo.getFlufdata());
                fluido_dh_data.setText(veiculo.getFludhdata());
                troca_pneus_data.setText(veiculo.getTpneudata());
                freios_data.setText(veiculo.getFreiodata());
                seletrico_data.setText(veiculo.getEletdata());
                sref_data.setText(veiculo.getRefrdata());
                cor_mang_data.setText(veiculo.getCormangdata());
                rev_geral_data.setText(veiculo.getRevgeraldata());
                ultabastecimento.setText(veiculo.getUlt_abs());
                t_combustivel.setText(veiculo.getTipoCombustivel());
                qt_litros.setText(veiculo.getQtlitros());

            }
        }
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
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();

                if (id ==  R.id.action_logout) {
                    onLogout(view);
                } else if (id == R.id.action_edit_vehicles) {
                    onEdit(view);
                }
                return true;
            });
            popupMenu.show();
        }
    }
    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
    }
    public void onEdit(View view){
        Intent editar = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(editar);
    }


}
