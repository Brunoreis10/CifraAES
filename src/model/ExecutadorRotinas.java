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

    public void executarProcessoPosGeracaoRoundKeys(List<RoundKey> listRoundKeys, File file, String nomeArquivo) throws IOException {
        List<String[][]> valoresCriptografados = new ArrayList<>();

        byte[] fileToByte = Files.readAllBytes(file.toPath());
        String[] vetAux = new String[fileToByte.length];
        for (int i = 0; i < fileToByte.length; i++) {
            vetAux[i] = Integer.toHexString(Integer.parseInt(String.valueOf(fileToByte[i])));
        }
        String[][] vetBidimensional = utils.mudarVetParaBidimensional(vetAux);

        valoresCriptografados.add(listRoundKeys.get(0).addRoundKey(vetBidimensional));

        for (int i = 0; i < 10; i++) {

            RoundKey rk = new RoundKey();
            rk.setMatrizRoundKey(valoresCriptografados.get(i));
            rk.setMatrizRoundKey(rk.subBytes());
            String[][] shiftRows = rk.shiftRows();
            rk.setMatrizRoundKey(shiftRows.clone());
            if (i != 9) {
                RoundKey rkAux = new RoundKey();
                rkAux.setWord(rkAux.getRoundKey(), utils.wordMixColumn(rk.getWordByColumn(0)), 0);
                rkAux.setWord(rkAux.getRoundKey(), utils.wordMixColumn(rk.getWordByColumn(1)), 1);
                rkAux.setWord(rkAux.getRoundKey(), utils.wordMixColumn(rk.getWordByColumn(2)), 2);
                rkAux.setWord(rkAux.getRoundKey(), utils.wordMixColumn(rk.getWordByColumn(3)), 3);
                valoresCriptografados.add(rkAux.addRoundKey(listRoundKeys.get(i + 1).getRoundKey()));
            } else {
                valoresCriptografados.add(rk.addRoundKey(listRoundKeys.get(i + 1).getRoundKey()));
            }

        }

        arquivo.escreverNoArquivo(nomeArquivo, valoresCriptografados);
    }
}
