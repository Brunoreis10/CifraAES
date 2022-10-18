/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package view;

import java.io.File;
import model.AESUtils;

/**
 *
 * @author Bruno Henrique Wiedemann Reis Lucas Miguel Vieira
 */
public class TrabalhoCifraAES {

public static void main(String[] args) throws Exception {
            new TrabalhoCifraAES().init();
    }

    public void init() throws Exception{
//        CifraAESUI ui = new CifraAESUI();;;;;;
//        ui.setVisible(true);
        File file = new File("C:\\Users\\Lucas\\Desktop\\seguranca\\CifraAES\\src\\view\\teste.txt");
        byte[] retorno;
        AESUtils utils = new AESUtils();
        retorno = utils.codificarFileToBinary(file);
        System.out.print(retorno);

    }
}
