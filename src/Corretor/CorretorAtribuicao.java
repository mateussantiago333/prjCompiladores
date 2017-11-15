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
public class CorretorAtribuicao {

    public String corrigeAt(String aux, int cont, int linerror) {
        String correcao = null;
        String[] test = new String[4];
         /////////////////////////////////////////////////////////////////////////
        //Checa tamanho e servirá para testar o case de identificadores
        if (cont + 1 < aux.length() && Character.isLetter(aux.charAt(cont + 1))) {
            test[0] = aux.charAt(cont + 1) + "";
            test[1] = test[0].toUpperCase();
        }

        if (cont + 2 < aux.length() && Character.isLetter(aux.charAt(cont + 2))) {
            test[2] = aux.charAt(cont + 2) + "";
            test[3] = test[2].toUpperCase();
        }
        /////////////////////////////////////////////////////////////////////////
        //Parâmetros faltando
                    if (aux.length() < 3) {
                        correcao = "[ERRO - Linha " + linerror + "] Parâmetros ausentes";
                    } //Identificador em letra maiuscula
                    else if (test[1] != null && test[1].charAt(0) == aux.charAt(cont + 1)) {
                        correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
                    } // Identificador não é uma letra 
                    else if (!Character.isLetter(aux.charAt(cont + 1)) && cont + 1 < aux.length()) {
                        correcao = "[ERRO - Linha " + linerror + "] Identificador inválido";
                    } // Caracter que será atribuido ao identificador está em letra maiuscula
                    else if (test[3] != null && Character.isLetter(aux.charAt(cont + 2)) && test[3].charAt(0) == aux.charAt(cont + 2)) {
                        correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
                    } else if (aux.length() > 3) {
                        correcao = "[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido";
                    }
        return correcao;
    }
}
