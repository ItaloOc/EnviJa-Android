package com.envija.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.envija.app.database.RemessaDAO;

public class PodEntregaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pod_entrega);

        RemessaDAO dao = new RemessaDAO(this);

        EditText etCodigo = findViewById(R.id.etCodigoPod);
        EditText etNomeRec = findViewById(R.id.etRecebedorNome);
        EditText etDocRec = findViewById(R.id.etRecebedorDoc);
        CheckBox cbIntegro = findViewById(R.id.cbLacreIntegro);
        Button btnConfirmar = findViewById(R.id.btnConfirmarPod);

        btnConfirmar.setOnClickListener(v -> {
            String cod = etCodigo.getText().toString().trim().toUpperCase();
            String nome = etNomeRec.getText().toString().trim();
            String doc = etDocRec.getText().toString().trim();
            boolean integro = cbIntegro.isChecked();

            if (cod.isEmpty() || nome.isEmpty() || doc.isEmpty()) {
                Toast.makeText(this, "Preencha o código de rastreio e dados do recebedor!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dao.confirmarEntregaPOD(cod, nome, doc, integro)) {
                String msg = integro ? "POD registrado! Entrega concluída." : "POD registrado com AVARIA REPORTADA!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Código de rastreio não encontrado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}