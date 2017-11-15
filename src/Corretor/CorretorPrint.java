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
public class CorretorPrint {
        public String corrigePrint(String aux,int cont,int linerror){
        String correcao = null;
        String[] test = new String[4];
                    //Checa se identificador está em letra maiuscula
                    if (cont + 1 < aux.length() && Character.isLetter(aux.charAt(cont + 1))) {
                        test[0] = aux.charAt(cont + 1) + "";
                        test[1] = test[0].toUpperCase();
                    } 
                    //Checa se possui todos os parametros
                    if (aux.length() < 2) {
                        correcao = "[ERRO - Linha " + linerror + "] Parametro ausente";
                    }//Checa se identificador está em letra maiuscula
                    else if (test[1] != null && aux.charAt(cont + 1) == test[1].charAt(0) && (cont + 1 < aux.length())) {
                        correcao = "[ERRO - Linha " + linerror + "] Comando não permite identificadores em letra maiúscula";
                    }//Checa a quantidade de parâmetros 
                    else if (aux.length() > 2) {
                        correcao = "[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido";
                    }
        return correcao;
    }
}
