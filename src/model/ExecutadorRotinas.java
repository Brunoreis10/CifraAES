/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class ExecutadorRotinas {

    private RoundKey key = new RoundKey();
    private AESUtils utils = new AESUtils();
    private ArrayList<RoundKey> listRoundKeys = new ArrayList<>();
    private Arquivo arquivo = new Arquivo();

    public ArrayList<RoundKey> executarRotinas(String[] chave) {
        key.setRoundKey(chave);
        listRoundKeys.add(key);
        for (int i = 0; i < 10; i++) {
            //Copiando chave anterior
            String[] part1 = key.copiarRoundKeyAnterior(listRoundKeys.get(i));

            //RotWord
            String[] part2 = key.rotWord(part1);

            //SubWord
            String[] part3 = key.subWord(part2);

            //Round constant
            String[] part4 = key.roundConstant(i);

            //Operação de xor
            String[] part5 = key.xorRoundConstant(part3, part4);

            //Busca a primeira palavra
            String[] part6 = key.primeiraPalavraRoundKey(key.getWord(listRoundKeys.get(i).getRoundKey(), 0), part5);

            //Geração da nova word e da round key alterada
            RoundKey key2 = new RoundKey();
            key2.setWord(key2.getRoundKey(), part6, 0);
            key2.setRoundKeyAlterada(listRoundKeys.get(i));
            listRoundKeys.add(key2);
        }
        return listRoundKeys;
    }

    public void executarProcessoPosGeracaoRoundKeys() throws IOException {
        //File file = "Passar aqui o arquivo";
        //File file = null;
        List<String[][]> valoresCriptografados = new ArrayList<>();

        //byte[] fileToByte = Files.readAllBytes(file.toPath());
        String[] vetAux = "44,45,53,45,4e,56,4f,4c,56,49,4d,45,4e,54,4f,21".split(",");
        //for(int i=0;i<fileToByte.length;i++){
           // vetAux[i] = String.valueOf(fileToByte[i]);
        //}
        //Muda o vetor atual de byte para um bidimensional com 16 bits, para ser mais fácil a manipulação depois.
        String[][] vetBidimensional = utils.mudarVetParaBidimensional(vetAux);
        valoresCriptografados.add(listRoundKeys.get(0).addRoundKey(vetBidimensional));
        
        RoundKey rk = new RoundKey();
        rk.setMatrizRoundKey(valoresCriptografados.get(0));
        rk.setMatrizRoundKey(rk.subBytes());
        rk.setMatrizRoundKey(rk.shiftRows());
        
        
        
        //Realizar o processo de AddRoundKey, ShiftRows, MixColums
        ////
        
        String nomeArquivo = "Pegar o nome do arquivo";
        //Passar a lista de valores criptografados pelos métodoa anteriores
        arquivo.escreverNoArquivo(nomeArquivo, valoresCriptografados);
    }
}
