package com.envija.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.envija.app.database.RemessaDAO;
import com.envija.app.model.Remessa;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListaRemessasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_remessas);

        ListView lv = findViewById(R.id.lvRemessas);
        RemessaDAO dao = new RemessaDAO(this);
        List<Remessa> remessas = dao.listarTodas();
        List<String> itens = new ArrayList<>();

        for (Remessa r : remessas) {
            String tagFragil = r.isCargaFragil() ? " [FRÁGIL]" : "";
            String infoPod = r.getStatus().equals("Em Trânsito") ? "" : "\nRecebedor: " + r.getRecebedorNome();
            itens.add(String.format(Locale.getDefault(),
                    "%s - %s%s\nDestinatário: %s | Frete: R$ %.2f\nStatus: %s%s",
                    r.getCodigoRastreio(), r.getProdutoTech(), tagFragil,
                    r.getDestinatario(), r.getValorFreteTotal(), r.getStatus(), infoPod));
        }

        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens));
    }
}