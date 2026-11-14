package com.envija.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.envija.app.database.RemessaDAO;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvEmTransito, tvEntregues;
    private RemessaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dao = new RemessaDAO(this);
        tvEmTransito = findViewById(R.id.tvEmTransito);
        tvEntregues = findViewById(R.id.tvEntregues);

        Button btnNova = findViewById(R.id.btnNovaRemessa);
        Button btnListar = findViewById(R.id.btnListarRemessas);
        Button btnPod = findViewById(R.id.btnAbrirPod);

        btnNova.setOnClickListener(v -> startActivity(new Intent(this, NovaRemessaActivity.class)));
        btnListar.setOnClickListener(v -> startActivity(new Intent(this, ListaRemessasActivity.class)));
        btnPod.setOnClickListener(v -> startActivity(new Intent(this, PodEntregaActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvEmTransito.setText(String.valueOf(dao.contarPorStatus("Em Trânsito")));
        tvEntregues.setText(String.valueOf(dao.contarPorStatus("Entregue (POD)")));
    }
}