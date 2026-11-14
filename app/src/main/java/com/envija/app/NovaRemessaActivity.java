package com.envija.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.envija.app.database.RemessaDAO;
import com.envija.app.model.Remessa;
import com.envija.app.utils.FreteTechCalculator;
import java.util.Locale;
import java.util.Random;

public class NovaRemessaActivity extends AppCompatActivity {

    private EditText etDestinatario, etProduto, etNfeNum, etNfeVal, etPeso, etComp, etLarg, etAlt;
    private CheckBox cbFragil;
    private TextView tvResumoCalculo;
    private RemessaDAO dao;

    private double ultimoSeguro = 0, ultimoCubado = 0, ultimoFreteTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_remessa);

        dao = new RemessaDAO(this);

        etDestinatario = findViewById(R.id.etDestinatario);
        etProduto = findViewById(R.id.etProduto);
        etNfeNum = findViewById(R.id.etNfeNum);
        etNfeVal = findViewById(R.id.etNfeVal);
        etPeso = findViewById(R.id.etPeso);
        etComp = findViewById(R.id.etComp);
        etLarg = findViewById(R.id.etLarg);
        etAlt = findViewById(R.id.etAlt);
        cbFragil = findViewById(R.id.cbFragil);
        tvResumoCalculo = findViewById(R.id.tvResumoCalculo);

        Button btnCalcular = findViewById(R.id.btnCalcularFrete);
        Button btnSalvar = findViewById(R.id.btnSalvarRemessa);

        btnCalcular.setOnClickListener(v -> calcularValores());

        btnSalvar.setOnClickListener(v -> {
            if (!calcularValores()) return;

            String dest = etDestinatario.getText().toString().trim();
            String prod = etProduto.getText().toString().trim();
            String nfe = etNfeNum.getText().toString().trim();

            if (dest.isEmpty() || prod.isEmpty() || nfe.isEmpty()) {
                Toast.makeText(this, "Preencha destinatário, equipamento e NF-e!", Toast.LENGTH_SHORT).show();
                return;
            }

            double valNfe = Double.parseDouble(etNfeVal.getText().toString());
            double pesoReal = Double.parseDouble(etPeso.getText().toString());
            String rastreio = "ENV" + (100000 + new Random().nextInt(900000)) + "BR";

            Remessa r = new Remessa(0, rastreio, dest, prod, nfe, valNfe, ultimoSeguro,
                    pesoReal, ultimoCubado, ultimoFreteTotal, cbFragil.isChecked(),
                    "Em Trânsito", "", "", true);

            if (dao.inserirRemessa(r)) {
                Toast.makeText(this, "Remessa gerada! Código: " + rastreio, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar remessa.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean calcularValores() {
        try {
            double valNfe = Double.parseDouble(etNfeVal.getText().toString());
            double pesoReal = Double.parseDouble(etPeso.getText().toString());
            double comp = Double.parseDouble(etComp.getText().toString());
            double larg = Double.parseDouble(etLarg.getText().toString());
            double alt = Double.parseDouble(etAlt.getText().toString());

            if (valNfe <= 0) {
                Toast.makeText(this, "RN01: Valor da NF-e obrigatório para seguro!", Toast.LENGTH_SHORT).show();
                return false;
            }

            ultimoCubado = FreteTechCalculator.calcularPesoCubado(comp, larg, alt);
            double maiorPeso = Math.max(pesoReal, ultimoCubado);

            if (maiorPeso > FreteTechCalculator.LIMITE_MAXIMO_KG) {
                Toast.makeText(this, "RN03: Excede limite de 30kg para cargas de TI!", Toast.LENGTH_LONG).show();
                return false;
            }

            ultimoSeguro = FreteTechCalculator.calcularSeguroNFe(valNfe);
            ultimoFreteTotal = FreteTechCalculator.calcularFreteTotal(pesoReal, ultimoCubado, valNfe, cbFragil.isChecked());

            tvResumoCalculo.setText(String.format(Locale.getDefault(),
                    "Peso Cubado: %.2f kg | Seguro NF-e: R$ %.2f\nFrete Total: R$ %.2f",
                    ultimoCubado, ultimoSeguro, ultimoFreteTotal));
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Preencha todas as dimensões, peso e valor da NF-e.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}