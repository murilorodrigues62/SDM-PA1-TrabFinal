package br.edu.ifspsaocarlos.sdm.trabalhofinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.trabalhofinal.R;
import br.edu.ifspsaocarlos.sdm.trabalhofinal.model.GameInfo;

public class ConfigXadrezActivity extends AppCompatActivity {

    //private static final int INTENT_CRONO_XADREZ = 2;
    private GameInfo gameInfo;
    private TextView txtNomeJogador1, txtNomeJogador2, txtTempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_xadrez);

        txtNomeJogador1 = (TextView) findViewById(R.id.txtJogador1);
        txtNomeJogador2 = (TextView) findViewById(R.id.txtJogador2);
        txtTempo = (TextView) findViewById(R.id.txtTempo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracao_xadrez, menu);
        return true;
    }

    public void newGame(MenuItem menuItem) {

        // Cria nova activity e passa os parâmetros para ela
        Intent intent = new Intent(this, JogoXadrezActivity2.class);
        intent.putExtra("EXTRA_JOGADOR1", txtNomeJogador1.getEditableText().toString());
        intent.putExtra("EXTRA_JOGADOR2", txtNomeJogador2.getEditableText().toString());
        intent.putExtra("EXTRA_TEMPO", Long.valueOf(txtTempo.getEditableText().toString()));
        startActivity(intent);

    }
}
