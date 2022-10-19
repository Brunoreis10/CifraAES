/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class Arquivo {

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void escreverNoArquivo(String nomeArquivo, List<String[][]> valoresCriptografados) throws IOException {
        try (OutputStream outputWriter = new FileOutputStream(nomeArquivo)) {
            for (int x = 0; x < valoresCriptografados.size(); x++) {
                String[][] valores = valoresCriptografados.get(x);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        String valorEmHexaDecimal = valores[i][j];
                        int intValue = Integer.parseInt(valorEmHexaDecimal, 16);
                        try {
                            outputWriter.write(intValue);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
