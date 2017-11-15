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
public class CorretorGets {
    public String corrigeGets(String aux,int h,int linerror){
        String error = null;
        String[] test = new String[4];
                    //Checa se identificador está em letra maiuscula
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        test[0] = aux.charAt(h + 1) + "";
                        test[1] = test[0].toUpperCase();
                    } 
                    //Checa se possui todos os parametros
                    if (aux.length() < 2) {
                        error = "[ERRO - Linha " + linerror + "] Identificador ausente";
                    }else if (!Character.isLetter(aux.charAt(h + 1)) && (h + 1 < aux.length())) {
                        error = "[ERRO - Linha " + linerror + "] Caracter não compatível com este comando";
                    }//Checa se identificador está em letra maiuscula
                    else if (test[1] != null && aux.charAt(h + 1) == test[1].charAt(0) && (h + 1 < aux.length())) {
                        error = "[ERRO - Linha " + linerror + "] Comando não permite identificadores em letra maiúscula";
                    }//Checa a quantidade de parâmetros 
                    else if (aux.length() > 2) {
                        error = "[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido";
                    }
        return error;
    }
}
