/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class ExecutadorRotinas {
    
    //Instâncias
    private RoundKey key = new RoundKey();
    //Variáveis
    private ArrayList<RoundKey> listRoundKeys = new ArrayList<>();

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
}
