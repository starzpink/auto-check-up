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
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    private EditText apelido_veiculo, modelo, ano, placa, media_km, km_atual, oleo_data, fluido_t_data, fluido_f_data, fluido_dh_data, troca_pneus_data, freios_data, seletrico_data, sref_data, cor_mang_data, rev_geral_data, ultabastecimento, tipoCombustivel, qt_litros;

    private Button salvar;
    private DbHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = findViewById(R.id.topAppBar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        db = new DbHelper(getApplicationContext());
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
        tipoCombustivel = findViewById(R.id.tipoCombustivel);
        qt_litros = findViewById(R.id.qt_litros);
        salvar = findViewById(R.id.salvar);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("VEICULO_EXISTENTE")) {
            Veiculo veiculoExistente = (Veiculo) intent.getSerializableExtra("VEICULO_EXISTENTE");
            apelido_veiculo.setText(veiculoExistente.getApelido());
            modelo.setText(veiculoExistente.getModelo_carro());
            ano.setText(veiculoExistente.getAno_carro());
            placa.setText(veiculoExistente.getPlaca_carro());
            media_km.setText(veiculoExistente.getMediakm());
            km_atual.setText(veiculoExistente.getKmatual());
            oleo_data.setText(veiculoExistente.getOleodata());
            fluido_t_data.setText(veiculoExistente.getFlutdata());
            fluido_f_data.setText(veiculoExistente.getFlufdata());
            fluido_dh_data.setText(veiculoExistente.getFludhdata());
            troca_pneus_data.setText(veiculoExistente.getTpneudata());
            freios_data.setText(veiculoExistente.getFreiodata());
            seletrico_data.setText(veiculoExistente.getEletdata());
            sref_data.setText(veiculoExistente.getRefrdata());
            cor_mang_data.setText(veiculoExistente.getCormangdata());
            rev_geral_data.setText(veiculoExistente.getRevgeraldata());
            ultabastecimento.setText(veiculoExistente.getUlt_abs());
            tipoCombustivel.setText(veiculoExistente.getTipoCombustivel());
            qt_litros.setText(veiculoExistente.getQtlitros());

        }

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veiculo veiculo = new Veiculo(
                        apelido_veiculo.getText().toString(),
                        modelo.getText().toString(),
                        ano.getText().toString(),
                        placa.getText().toString(),
                        media_km.getText().toString(),
                        km_atual.getText().toString(),
                        oleo_data.getText().toString(),
                        fluido_t_data.getText().toString(),
                        fluido_f_data.getText().toString(),
                        fluido_dh_data.getText().toString(),
                        troca_pneus_data.getText().toString(),
                        freios_data.getText().toString(),
                        seletrico_data.getText().toString(),
                        sref_data.getText().toString(),
                        cor_mang_data.getText().toString(),
                        rev_geral_data.getText().toString(),
                        ultabastecimento.getText().toString(),
                        tipoCombustivel.getText().toString(),
                        qt_litros.getText().toString()
                );

                db.salvarVeiculo(veiculo);

                apelido_veiculo.setText("");
                modelo.setText("");
                ano.setText("");
                placa.setText("");
                media_km.setText("");
                km_atual.setText("");
                oleo_data.setText("");
                fluido_t_data.setText("");
                fluido_f_data.setText("");
                fluido_dh_data.setText("");
                troca_pneus_data.setText("");
                freios_data.setText("");
                seletrico_data.setText("");
                sref_data.setText("");
                cor_mang_data.setText("");
                rev_geral_data.setText("");
                ultabastecimento.setText("");
                tipoCombustivel.setText("");
                qt_litros.setText("");

                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });
        DbHelper dbHelper = new DbHelper(this);
        String trocaDeOleo = dbHelper.obterTrocaDeOleo();
        String revisaoCompleta = dbHelper.obterRevisaoCompleta();
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