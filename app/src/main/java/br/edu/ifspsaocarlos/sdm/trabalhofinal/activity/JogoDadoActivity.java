package br.edu.ifspsaocarlos.sdm.trabalhofinal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifspsaocarlos.sdm.trabalhofinal.R;
import br.edu.ifspsaocarlos.sdm.trabalhofinal.model.Dado;

public class JogoDadoActivity extends Activity {
    private ImageView imgDado;
    private Dado dado;
    private final String FACE_SORTEADA = "FACE_SORTEADA" ;
    private int ultaFaceSorteada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar_dado);

        // Cria o objeto e imagem do dado
        dado = new Dado();
        imgDado = (ImageView) findViewById(R.id.imgDado);

        // Se há aldo salvo
        if (savedInstanceState != null){

            // armazena a ultima face sorteada antes de reconstruir activity
            ultaFaceSorteada = savedInstanceState.getInt(FACE_SORTEADA);

        }
        else {
            // armazena a face sorteada inicialmente ao criar o dado
            ultaFaceSorteada = dado.getFaceSorteada();
        }

        // apresenta a imagem da face sorteada
        imgDado.setImageResource(dado.getImagemFace(ultaFaceSorteada));
    }

    public void OnClickJogarDado(View v) {

        // Executa metodo para jogar o dado
        dado.jogarDado(this);

        // Mostra na tela a face do dado que foi sorteada
        imgDado.setImageResource(dado.getImagemFaceSorteada());

        ultaFaceSorteada = dado.getFaceSorteada();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // salva qual foi a ultima face sorteada
        outState.putInt(FACE_SORTEADA, ultaFaceSorteada);
    }
}