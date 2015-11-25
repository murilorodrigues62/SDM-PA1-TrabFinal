package br.edu.ifspsaocarlos.sdm.trabalhofinal.model;

import android.app.Activity;
import android.media.MediaPlayer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.edu.ifspsaocarlos.sdm.trabalhofinal.R;


/**
 * Created by rodrigues on 12/11/15.
 */
public class Dado implements Serializable {
    private final int faceMenor = 1;
    private final int faceMaior = 6;
    private int faceSorteada;
    private final int arquivoAudio = R.raw.dado;
    private Map<Integer, Integer> faces = new HashMap<Integer, Integer>();

    public Dado(){

        // relaciona as faces do dado com a respectiva imagem
        faces.put(1,R.drawable.dado1);
        faces.put(2,R.drawable.dado2);
        faces.put(3,R.drawable.dado3);
        faces.put(4,R.drawable.dado4);
        faces.put(5,R.drawable.dado5);
        faces.put(6,R.drawable.dado6);

        faceSorteada = faceMenor;
    }

    // metodo faz a jogada do dado
    public void jogarDado(Activity view) {

        // Dispara som
        Audio.play(view, arquivoAudio);

        // Sorteia número entre as possíveis faces do dado
        Random random = new Random();
        int numero = random.nextInt((faceMaior - faceMenor) + 1) + faceMenor;

        // atribuimos o valor da face que foi sorteada
        faceSorteada = numero;

    }

    // metodo retorna a imagem da face sorteada
    public int getImagemFaceSorteada(){
        return faces.get(faceSorteada);
    }

    // metodo retorna a imagem de alguma face
    public int getImagemFace(int face){
        return faces.get(face);
    }

    public int getFaceMenor() {
        return faceMenor;
    }

    public int getFaceMaior() {
        return faceMaior;
    }

    public int getFaceSorteada() {
        return faceSorteada;
    }

    public int getArquivoAudio() {
        return arquivoAudio;
    }
}
