/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.ArrayList;

/**
 *
 * @author Mateus Silva
 */
public class CorretorConversor {

    Conversor cv = new Conversor();

    public String correctText(ArrayList text) {
        String text_final = "";
        return text_final;
    }

    public ArrayList textBreaker(String text) {
        ArrayList line = new ArrayList();
        int qntd = quantdLinhas(text);
        int y = 1, z = 0, t = 0;
        String temp = "";
        String temp2 = "";
        int i = 0;

        while (z < text.length()) {
            if (text.charAt(z) == '\n') {
                line.add(temp);
                temp = "";
            } else {
                temp = temp + text.charAt(z);
            }
            z++;
        }
        line.add(temp);
        return line;
    }

    public int quantdLinhas(String text) {
        int qntd = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                qntd++;
            }
        }

        qntd++;
        return qntd;
    }
}
