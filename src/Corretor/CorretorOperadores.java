/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Corretor;

/**
 *
 * @author Mateus Silva
 */
public class CorretorOperadores {

    public String corrigeOps(String aux, int cont, int linerror) {
        String correcao = null;
        String[] test = new String[6];
        /////////////////////////////////////////////////////////////////////////
        //Checa se identificadores estão em letra maiuscula
        if (cont + 1 < aux.length() && Character.isLetter(aux.charAt(cont + 1))) {
            test[0] = aux.charAt(cont + 1) + "";
            test[1] = test[0].toUpperCase();
        }

        if (cont + 2 < aux.length() && Character.isLetter(aux.charAt(cont + 2))) {
            test[2] = aux.charAt(cont + 2) + "";
            test[3] = test[2].toUpperCase();
        }

        if (cont + 3 < aux.length() && Character.isLetter(aux.charAt(cont + 3))) {
            test[4] = aux.charAt(cont + 3) + "";
            test[5] = test[4].toUpperCase();
        }
        /////////////////////////////////////////////////////////////////////////
        //Parâmetros faltando
        if (aux.length() < 4) {
            correcao = "[ERRO - Linha " + linerror + "] Parâmetros ausentes";
        }// Checa ID1
        else if (!Character.isLetter(aux.charAt(cont + 1)) && cont + 1 < aux.length()) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador inválido";
        }
        else if (!Character.isLetter(aux.charAt(cont + 2)) && !Character.isDigit(aux.charAt(cont + 2))  && cont + 2 < aux.length()) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador inválido";
        }
        else if (!Character.isLetter(aux.charAt(cont + 3)) && !Character.isDigit(aux.charAt(cont + 3))  && cont + 3 < aux.length()) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador inválido";
        }
        // Checa case ID1
        else if (test[1] != null && Character.isLetter(aux.charAt(cont + 1)) && test[1].charAt(0) == aux.charAt(cont + 1)) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
        }// Checa case ID2
        else if (test[3] != null && Character.isLetter(aux.charAt(cont + 2)) && test[3].charAt(0) == aux.charAt(cont + 2)) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
        }// Checa case ID3
        else if (test[5] != null && Character.isLetter(aux.charAt(cont + 3)) && test[5].charAt(0) == aux.charAt(cont + 3)) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
        } else if (aux.length() > 4) {
            correcao = "[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido";
        }
        /////////////////////////////////////////////////////////////////////////
        return correcao;
    }
}
