/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
        try ( OutputStreamWriter outputWriter = new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8)) {

            String[][] valores = valoresCriptografados.get(10);
            for (int i = 0; i < 4; i++) {
                String value = "";
                for (int j = 0; j < 4; j++) {
                    String valorEmHexaDecimal = valores[j][i];
                    value += "0x" + valorEmHexaDecimal + " ";

                }
                value += "\r\n";
                try {
                    outputWriter.write(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
