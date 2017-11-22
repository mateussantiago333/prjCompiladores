/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Corretor;

import java.util.ArrayList;

/**
 *
 * @author Mateus Silva
 */
public class CorretorIf {

    CorretorWhile corretorW = new CorretorWhile();
    CorretorAtribuicao corretorAt = new CorretorAtribuicao();
    CorretorPrint corretorP = new CorretorPrint();
    CorretorGets corretorG = new CorretorGets();
    CorretorOperadores corretorOp = new CorretorOperadores();
    ArrayList error = new ArrayList();
    int errorGeral = 0;

    public ArrayList corrigeIf(String aux, int cont, int linerror) {
        ArrayList line = new ArrayList();
        //Checa se identificadores estão em letra maiuscula;
        breakLine(aux, cont, linerror);
        String correcao = null;
        int contkey = 0, auxkey = 0;
        /////////////////////////////////////////////////////////////////////////
        correctInitial(aux, cont, linerror);
        if (error.size() == 0) {
            line = breakLine(aux, cont, linerror);
            for (int i = 0; i < line.size(); i++) {
            aux = line.get(i).toString();

            for (int h = 0; h < aux.length(); h++) {
                if ((aux.charAt(h) == 'G')) {
                    String result = null;
                    result = corretorG.corrigeGets(aux, h, linerror);
                    if (result != null) {
                        error.add(result);
                    }
                } //Atribuição
                else if ((aux.charAt(h) == '=')) {
                    String result = null;
                    result = corretorAt.corrigeAt(aux, h, linerror);
                    if (result != null) {
                        error.add(result);
                    }
                }//Operação
                else if (aux.charAt(h) == '*' || aux.charAt(h) == '+'
                        || aux.charAt(h) == '/' || aux.charAt(h) == '-' || aux.charAt(h) == '%') {
                        String result = null;
                        result = corretorOp.corrigeOps(aux, h, linerror);
                        if (result != null) {
                            error.add(result);
                        }
                    }
                //Print
                else if (aux.charAt(h) == 'P' ) {
                    String result = null;
                    result = corretorP.corrigePrint(aux, h, linerror);
                    if (result != null) {
                        error.add(result);
                    }
                }
            }
        }
        }
        ////////////////////////////////////////////////////////////////////////
        return error;
    }

    public ArrayList breakLine(String aux, int cont, int linerror) {
        ArrayList breaker = new ArrayList();
        int tam = cont + 4;
        int controlIf = 0;
        int y = tam;
        for (int g = y; g < aux.length(); g++) {
            controlIf = 0;

            /////////////////////////////////////////////////////////////////////////////////////
            if (aux.charAt(g) == 'G') {
                int j = 0;
                int total = g + 1;
                String temp = "";
                if (total < aux.length()) {
                    while (j < 2) {
                        temp = temp + aux.charAt(y);
                        j++;
                        y++;
                    }
                    breaker.add(temp);
                } else {
                    error.add("[ERRO - Linha " + linerror + " dentro do IF - Get] Parâmetros ausentes");
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////
            if (aux.charAt(g) == 'P') {
                int j = 0;
                int total = g + 1;
                String temp = "";
                if (total < aux.length()) {
                    while (j < 2) {
                        temp = temp + aux.charAt(y);
                        j++;
                        y++;
                    }
                    breaker.add(temp);
                } else {
                    error.add("[ERRO - Linha " + linerror + " dentro do IF - Print] Parâmetros ausentes");
                    break;
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////
            if (aux.charAt(g) == '+' || aux.charAt(g) == '-' || aux.charAt(g) == '%' || aux.charAt(g) == '*' || aux.charAt(g) == '/') {
                int j = 0;
                int total = g + 3;
                String temp = "";
                if (total < aux.length()) {
                    while (j < 4) {
                        temp = temp + aux.charAt(y);
                        j++;
                        y++;
                    }
                    breaker.add(temp);
                } else {
                    error.add("[ERRO - Linha " + linerror + " dentro do IF - Operação] Parâmetros ausentes");
                    break;
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////
            if (aux.charAt(g) == '=') {
                int j = 0;
                int total = g + 2;
                String temp = "";
                if (total < aux.length()) {
                    while (j < 3) {
                        temp = temp + aux.charAt(y);
                        j++;
                        y++;
                    }
                    breaker.add(temp);
                } else {
                    error.add("[ERRO - Linha " + linerror + " dentro do IF - Atribuição] Parâmetros ausentes");
                    break;
                }
            }
        }
        return breaker;
    }

    public void correctInitial(String aux, int cont, int linerror) {
        String[] testI = new String[4];
        String correcao = "";
        int errorIf = 0;
        if (cont + 1 < aux.length() && Character.isLetter(aux.charAt(cont + 1))) {
            testI[0] = aux.charAt(cont + 1) + "";
            testI[1] = testI[0].toUpperCase();
        }

        if (cont + 3 < aux.length() && Character.isLetter(aux.charAt(cont + 3))) {
            testI[2] = aux.charAt(cont + 3) + "";
            testI[3] = testI[2].toUpperCase();
        }
        /////////////////////////////////////////////////////////////////////////
        if (aux.length() < 4) {
            correcao = "[ERRO - Linha " + linerror + "] Parâmetros ausentes";
            error.add(correcao);
            errorIf = 1;
        }//Checa o case do segundo identificador
        else if (testI[1] != null && testI[1].charAt(0) == aux.charAt(cont + 1)) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
            error.add(correcao);
            errorIf = 1;
        } //Checa se foi colocado outro caracter que não seja um identificador
        else if (!Character.isLetter(aux.charAt(cont + 1)) && (cont + 1 < aux.length())) {
            correcao = "[ERRO - Linha " + linerror + "] Caracter não compatível com este comando";
            error.add(correcao);
            errorIf = 1;
        }//Checa se contem os operandos
        else if ((cont + 2 < aux.length()) && aux.charAt(cont + 2) != '>' && aux.charAt(cont + 2) != '<'
                && aux.charAt(cont + 2) != '=' && aux.charAt(cont + 2) != '#') {
            correcao = "[ERRO - Linha " + linerror + "] Insira os operadores necessários";
            error.add(correcao);
            errorIf = 1;
        }
        else if (!Character.isLetter(aux.charAt(cont + 3)) && !Character.isDigit(aux.charAt(cont + 3))  && cont + 3 < aux.length()) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador inválido";
            error.add(correcao);
            errorIf = 1;
        }
        //Checa o case do segundo identificador
        else if (testI[3] != null && testI[3].charAt(0) == aux.charAt(cont + 3)) {
            correcao = "[ERRO - Linha " + linerror + "] Identificador em letra maiuscula";
            error.add(correcao);
            errorIf = 1;
        }
        
         //Checa o case do segundo identificador
        else if (aux.indexOf("<=") != -1 || aux.indexOf(">=") != -1) {
                correcao = "[ERRO - Linha " + linerror + "] Adicione um numero ou identificador";
                error.add(correcao);
                errorIf = 1;
        }
    }
}
