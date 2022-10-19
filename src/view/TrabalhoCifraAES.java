/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package view;

import java.io.File;
import java.util.ArrayList;
import model.AESUtils;
import model.ExecutadorRotinas;
import model.RoundKey;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class TrabalhoCifraAES {

public static void main(String[] args) throws Exception {
            new TrabalhoCifraAES().init();
    }

    public void init() throws Exception{
        //CifraAESUI ui = new CifraAESUI();;;;;;
        //ui.setVisible(true);
        // file = new File("C:\\Users\\Lucas\\Desktop\\seguranca\\CifraAES\\src\\view\\teste.txt");
        //byte[] retorno;
        //AESUtils utils = new AESUtils();
        //retorno = utils.codificarFileToBinary(file);
        //System.out.print(retorno.toString());
        ExecutadorRotinas er = new ExecutadorRotinas();
        String texto = "41,42,43,44,45,46,47,48,49,4a,4b,4c,4d,4e,4f,50";
        String textoSimples = "44,45,53,45,4e,56,4f,4c,56,49,4d,45,4e,54,4f,21";
        ArrayList<RoundKey> arr = er.executarRotinas(texto.split(","));        

    }
}
