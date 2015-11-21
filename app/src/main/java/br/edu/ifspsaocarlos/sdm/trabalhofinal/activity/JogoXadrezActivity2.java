package br.edu.ifspsaocarlos.sdm.trabalhofinal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.trabalhofinal.R;


public class JogoXadrezActivity2 extends Activity implements View.OnClickListener {

    private boolean tempoIniciado = false;
    private boolean jogador1Ativo = true;
    private Button btnTrocar; //// TODO: 18/11/15 Incliur botão para parar 
    public TextView txtTempoJogador1, txtTempoJogador2, txtNomeJogador1, txtNomeJogador2;
    private long tempoJogador1;
    private long tempoJogador2;
    private final long intervalo = 1000;
    private CountDownTimer countDownTimer1, countDownTimer2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_xadrez2);

        // Cria os componentes da tela
        btnTrocar = (Button) this.findViewById(R.id.btnIniciarXadrez);
        btnTrocar.setOnClickListener(this);
        txtTempoJogador1 = (TextView) this.findViewById(R.id.txtTempoJogador1);
        txtTempoJogador2 = (TextView) this.findViewById(R.id.txtTempoJogador2);
        txtNomeJogador1 = (TextView) this.findViewById(R.id.txtNomeJogador1);
        txtNomeJogador2 = (TextView) this.findViewById(R.id.txtNomeJogador2);

        // Pega os parâmetros Extras da tela anterior
        Intent intent = getIntent();

        // Apresenta nome dos jogadores
        txtNomeJogador1.setText(intent.getStringExtra("EXTRA_JOGADOR1"));
        txtNomeJogador2.setText(intent.getStringExtra("EXTRA_JOGADOR2"));

        if (txtNomeJogador1.getText().toString().isEmpty()){
            txtNomeJogador1.setText(getText(R.string.jogador1));
        }

        if (txtNomeJogador2.getText().toString().isEmpty()){
            txtNomeJogador2.setText(getText(R.string.jogador2));
        }

        // Pega o tempo parametrizado e converte os minutos em milisegundos
        tempoJogador1 = intent.getLongExtra("EXTRA_TEMPO", 0) *60 *1000;
        tempoJogador2 = intent.getLongExtra("EXTRA_TEMPO", 1) *60 *1000;

        // Apresenta na tela os tempos
        txtTempoJogador1.setText(txtTempoJogador1.getText() + String.valueOf(tempoJogador1 / 1000));
        txtTempoJogador2.setText(txtTempoJogador2.getText() + String.valueOf(tempoJogador2 / 1000));

        // Cria temporizador para os jogadores
        countDownTimer1 = new MyCountDownTimer(tempoJogador1, intervalo);
        countDownTimer2 = new MyCountDownTimer(tempoJogador2, intervalo);
    }

    @Override
    public void onClick(View v) {

        // Se o jogo ainda não foi iniciado
        if (!tempoIniciado) {

            // marca jogo como iniciado
            tempoIniciado = true;
            // marca jogador 1 como ativo
            jogador1Ativo = true;

            // inicia contagem regressiva do jogador 1
            countDownTimer1.start();


        }
        // Se o jogo já foi iniciado
        else {

            // Se o Jogador 1 é quem estava ativo
            if(jogador1Ativo){

                // Jogador 2 é quem vai jogar
                jogador1Ativo = false;
                // Cancela contador do Jogador 1
                countDownTimer1.cancel();

                // Reinicia temporizador do Jogador 2
                countDownTimer2 = new MyCountDownTimer(tempoJogador2, intervalo);
                countDownTimer2.start();

            }
            // Se o Jogador 2 é quem estava jogando
            else {

                // Jogador 1 é quem vai jogar
                jogador1Ativo = true;
                // Cancela contador do Jogador 2
                countDownTimer2.cancel();

                // Reinicia temporizador do Jogador 1
                countDownTimer1 = new MyCountDownTimer(tempoJogador1, intervalo);
                countDownTimer1.start();

            }

        }
        btnTrocar.setText(R.string.trocar);
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {

            // Apresenta mensagem para o jogador ganhador e perdedor
            if (jogador1Ativo){
                txtTempoJogador1.setText(R.string.perdeu);
                txtTempoJogador2.setText(R.string.ganhou);
            }
            else
            {
                txtTempoJogador2.setText(R.string.perdeu);
                txtTempoJogador1.setText(R.string.ganhou);
            }

        }

        @Override
        public void onTick(long millisUntilFinished) {

            // Atualiza na tela o tempo e guarda o tempo atual para ser utilizado na troca de jogadores
            if (jogador1Ativo) {

                txtTempoJogador1.setText("" + millisUntilFinished / 1000);
                tempoJogador1 = millisUntilFinished;

            } else {

                txtTempoJogador2.setText("" + millisUntilFinished / 1000);
                tempoJogador2 = millisUntilFinished;

            }
        }
    }

}