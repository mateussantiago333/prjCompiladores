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
        int i = 0, y = 0, z = 0;
        int iferror = 0;
        ArrayList lines = new ArrayList();
        ArrayList error = new ArrayList();
        String[] ops = {"*", "/", "+", "-"};
        int controlIf = 0;
        int controlW = 0;
        String temp = "";
        String aux = "";
        int contkey = 0, auxkey = 0;
        String aux2 = "";
        String text_final = "";
        int controlError = 0;
        int controlLenght = 0;
        char op[] = {'>', '<', '#', '='};

        for (i = 0; i < text.size(); i++) {
            temp = text.get(i).toString().replaceAll(" ", "");
            lines.add(temp);
        }

        for (i = 0; i < lines.size(); i++) {
            int linerror = i + 1;
            controlIf = 0;
            aux = lines.get(i).toString();
            for (int h = 0; h < aux.length(); h++) {
                
                // If
                if (aux.charAt(h) == 'I') {
                    iferror = i;
                    controlIf = 1;
                    controlLenght = 0;
                    String[] testI = new String[4];
                    //Checa se identificadores estão em letra maiuscula
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        testI[0] = aux.charAt(h + 1) + "";
                        testI[1] = testI[0].toUpperCase();
                    }

                    if (h + 3 < aux.length() && Character.isLetter(aux.charAt(h + 3))) {
                        testI[2] = aux.charAt(h + 3) + "";
                        testI[3] = testI[2].toUpperCase();
                    }
                } // While
                else if ((aux.charAt(h) == 'W') && controlIf == 0) {
                    controlW = 1;
                    contkey++;

                    String[] test = new String[4];
                    //Checa tamanho e servirá para testar o case de identificadores
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        test[0] = aux.charAt(h + 1) + "";
                        test[1] = test[0].toUpperCase();
                    }

                    if (h + 3 < aux.length() && Character.isLetter(aux.charAt(h + 3))) {
                        test[2] = aux.charAt(h + 3) + "";
                        test[3] = test[2].toUpperCase();
                    }
                    if (aux.indexOf("{") == -1) {
                        //Checa se contém chave
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Insira a '{'");
                    }//Parâmetros faltando
                    else if (aux.length() < 5) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Parâmetros ausentes");
                    } //Identificador em letra maiuscula
                    else if (test[1] != null && test[1].charAt(0) == aux.charAt(h + 1)) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                    } // Identificador não é uma letra 
                    else if (!Character.isLetter(aux.charAt(h + 1)) && h + 1 < aux.length()) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador inválido");
                    } // Caracter que será atribuido ao identificador está em letra maiuscula
                    else if (test[3] != null && Character.isLetter(aux.charAt(h + 3)) && test[3].charAt(0) == aux.charAt(h + 3)) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                    }//Checa se contem os operandos
                    else if (aux.charAt(h + 2) != '>' && aux.charAt(h + 2) != '<'
                            && aux.charAt(h + 2) != '=' && aux.charAt(h + 2) != '#') {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Insira os operadores necessários");
                    }//Mais parametros que o necessário
                    else if (aux.length() > 5) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido");
                    }

                } //Key Closed
                else if ((aux.charAt(h) == '}') && controlIf == 0) {
                    auxkey++;
                } //Gets
                else if ((aux.charAt(h) == 'G') && controlIf == 0) {
                    String[] test = new String[4];
                    //Checa se identificador está em letra maiuscula
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        test[0] = aux.charAt(h + 1) + "";
                        test[1] = test[0].toUpperCase();
                    }
                    //Checa se o parâmetro é do tipo correto
                    if (aux.length() < 2) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador ausente");
                    } else if (!Character.isLetter(aux.charAt(h + 1)) && (h + 1 < aux.length())) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Caracter não compatível com este comando");
                    }//Checa se identificador está em letra maiuscula
                    else if (test[1] != null && aux.charAt(h + 1) == test[1].charAt(0) && (h + 1 < aux.length())) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Comando não permite identificadores em letra maiúscula");
                    }//Checa a quantidade de parâmetros 
                    else if (aux.length() > 2) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido");
                    }
                } //Atribuição
                else if ((aux.charAt(h) == '=') && controlIf == 0) {
                    String[] test = new String[4];
                    //Checa tamanho e servirá para testar o case de identificadores
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        test[0] = aux.charAt(h + 1) + "";
                        test[1] = test[0].toUpperCase();
                    }

                    if (h + 2 < aux.length() && Character.isLetter(aux.charAt(h + 2))) {
                        test[2] = aux.charAt(h + 2) + "";
                        test[3] = test[2].toUpperCase();
                    }

                    //Parâmetros faltando
                    if (aux.length() < 3) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Parâmetros ausentes");
                    } //Identificador em letra maiuscula
                    else if (test[1] != null && test[1].charAt(0) == aux.charAt(h + 1)) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                    } // Identificador não é uma letra 
                    else if (!Character.isLetter(aux.charAt(h + 1)) && h + 1 < aux.length()) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador inválido");
                    } // Caracter que será atribuido ao identificador está em letra maiuscula
                    else if (test[3] != null && Character.isLetter(aux.charAt(h + 2)) && test[3].charAt(0) == aux.charAt(h + 2)) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                    } else if (aux.length() > 3) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido");
                    }

                } //Operação
                else if (aux.charAt(h) == '*' || aux.charAt(h) == '+'
                        || aux.charAt(h) == '/' || aux.charAt(h) == '-' || aux.charAt(h) == '%') {
                    if (controlIf == 0) {
                        String[] test = new String[6];
                        //Checa se identificadores estão em letra maiuscula
                        if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                            test[0] = aux.charAt(h + 1) + "";
                            test[1] = test[0].toUpperCase();
                        }

                        if (h + 2 < aux.length() && Character.isLetter(aux.charAt(h + 2))) {
                            test[2] = aux.charAt(h + 2) + "";
                            test[3] = test[2].toUpperCase();
                        }

                        if (h + 3 < aux.length() && Character.isLetter(aux.charAt(h + 3))) {
                            test[4] = aux.charAt(h + 3) + "";
                            test[5] = test[4].toUpperCase();
                        }

                        //Parâmetros faltando
                        if (aux.length() < 4) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Parâmetros ausentes");
                        }// Checa ID1
                        else if (!Character.isLetter(aux.charAt(h + 1)) && h + 1 < aux.length()) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Identificador inválido");
                        }// Checa case ID1
                        else if (test[1] != null && Character.isLetter(aux.charAt(h + 1)) && test[1].charAt(0) == aux.charAt(h + 1)) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                        }// Checa case ID2
                        else if (test[3] != null && Character.isLetter(aux.charAt(h + 2)) && test[3].charAt(0) == aux.charAt(h + 2)) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                        }// Checa case ID3
                        else if (test[5] != null && Character.isLetter(aux.charAt(h + 3)) && test[5].charAt(0) == aux.charAt(h + 3)) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                        } else if (aux.length() > 4) {
                            controlError = 1;
                            error.add("[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido");
                        }

                    }
                } //Print
                else if (aux.charAt(h) == 'P' && controlIf == 0) {
                    String[] test = new String[4];
                    //Checa se identificador está em letra maiuscula
                    if (h + 1 < aux.length() && Character.isLetter(aux.charAt(h + 1))) {
                        test[0] = aux.charAt(h + 1) + "";
                        test[1] = test[0].toUpperCase();
                    }

                    if (aux.length() < 2) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Parâmetros ausentes");
                    } //Identificador em letra maiuscula
                    else if (test[1] != null && test[1].charAt(0) == aux.charAt(h + 1)) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Identificador em letra maiuscula");
                    }//Parametros a mais do que permitido
                    else if (aux.length() > 2) {
                        controlError = 1;
                        error.add("[ERRO - Linha " + linerror + "] Foram inseridos mais parâmetos que o permitido");
                    }
                }
            }
        }
        //Chaves
        if (contkey != auxkey) {
            controlError = 1;
            error.add("[ERRO] Cheque se as chaves foram abertas ('{') e fechadas ('}')");
        }

        if (controlLenght != aux.length()) {
            controlError = 1;
            iferror = iferror + 1;
            error.add("[ERRO - Linha " + iferror + "]  Faltam parâmetros");
        }

        if (controlError == 0) {
            text_final = cv.resultado(text);
        } else {
            for (int j = 0; j < error.size(); j++) {
                text_final = text_final + error.get(j) + "\n";
            }
        }
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
