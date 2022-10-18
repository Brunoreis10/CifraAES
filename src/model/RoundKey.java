/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class RoundKey {

    private AESUtils utils = new AESUtils();
    private String[][] matrizRoundKey = new String[4][4];

    public String[][] getRoundKey() {
        return matrizRoundKey;
    }

    public void setRoundKey(String[] textoArquivo) {
        int contador = 0;
        for (int coluna = 0; coluna < 4; coluna++) {
            for (int linha = 0; linha < 4; linha++) {
                matrizRoundKey[linha][coluna] = textoArquivo[contador];
                contador++;
            }
        }
    }

    public String[] roundConstant(Integer indiceRound) {
        String[] vet = new String[4];
        vet[0] = Integer.toHexString(utils.getVetRoundConstant(indiceRound));
        vet[1] = "0";
        vet[2] = "0";
        vet[3] = "0";
        return vet;
    }

    public String[] xorRoundConstant(String[] palavra1, String[] palavra2) {
        String[] vetAux = new String[4];

        for (int i = 0; i < 4; i++) {
            //Operador ^ (operador xor), retorna 1 se os dois bits de entrada forem diferentes, caso contrário retorna 0
            vetAux[i] = Integer.toHexString(Integer.parseInt(palavra1[i], 16) ^ Integer.parseInt(palavra2[i], 16));
        }

        return vetAux;
    }

    public String[] primeiraPalavraRoundKey(String[] roundAnterior, String[] palavraEtapaXorRoundConstant) {
        return xorRoundConstant(roundAnterior, palavraEtapaXorRoundConstant);
    }

    public String[] copiarRoundKeyAnterior(RoundKey chave) {
        String[] copia = new String[4];
        for (int i = 0; i < 4; i++) {
            copia[i] = getWord(chave.getRoundKey(), 3)[i];
        }
        return copia;
    }

    public void setRoundKeyAlterada(RoundKey roundKeyAnt) {
        int contador = 0;
        int coluna = 1;
        for (int i = 0; i < 3; ++i) {
                    
            String[] word1 = getWord(roundKeyAnt.getRoundKey(), contador++);
            String[] word2 = getWord(roundKeyAnt.getRoundKey(), contador);
            contador++;

            String[] wordComXor = xorRoundConstant(word1, word2);
            preencheRoundKeyAlterada(coluna++, wordComXor);
        }
    }

    private void preencheRoundKeyAlterada(int coluna, String[] aWordXOR) {
        for (int i = 0; i < 4; i++) {
            getRoundKey()[i][coluna] = aWordXOR[i];
        }
    }
    
    public void setWord(String[][] matrizRoundKey, String[] textoPrimeiraPalavra, int coluna) {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            matrizRoundKey[contador][coluna] = textoPrimeiraPalavra[i];
            contador++;
        }
    }

    public String[] getWord(String[][] roundKey, int coluna) {
        String[] word = new String[4];

        int contador = 0;
        for (int i = 0; i < 4; i++) {
            word[i] = roundKey[contador][coluna];
            contador++;
        }

        return word;
    }

    public String[] rotWord(String[] palavra) {
        String[] palavraAux = new String[4];
        int iAux = 0;
        for (int i = 0; i < palavra.length; i++) {
            iAux = i;
            if ((iAux + 1) > 3) {
                palavraAux[i] = palavra[0];
            } else {
                palavraAux[i] = palavra[iAux + 1];
            }
        }
        return palavraAux;
    }

    public String[] subWord(String[] palavra) {
        Integer linha = null;
        Integer coluna = null;
        for (int i = 0; i < palavra.length; i++) {
            linha = Integer.parseInt(String.valueOf(palavra[i].charAt(0)), 16);
            if (palavra[i].length() == 1) {
                coluna = linha;
                linha = 0;
            } else {
                coluna = Integer.parseInt(String.valueOf(palavra[i].charAt(1)), 16);
            }
            palavra[i] = Integer.toHexString(utils.getSBoxByLinhaEColuna(linha, coluna));
        }

        return palavra;
    }
}