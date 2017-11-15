/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Corretor;

import Conversor.Conversor;
import java.util.ArrayList;

/**
 *
 * @author Mateus Silva
 */
public class CorretorConversor {

    Conversor cv = new Conversor();
    CorretorIf corretorIf = new CorretorIf();
    CorretorWhile corretorW = new CorretorWhile();
    CorretorAtribuicao corretorAt = new CorretorAtribuicao();
    CorretorPrint corretorP = new CorretorPrint();
    CorretorGets corretorG = new CorretorGets();
    CorretorOperadores corretorOp = new CorretorOperadores();
    
    public String correctText(ArrayList text) {
        String text_final = "";
        String aux = "";
        int controlIf = 0;
        int i = 0;
        //Inteiros para checar abertura e fechamento de chaves
        int contkey = 0,auxkey = 0;
        //ArrayList para cada linha do código digitado
        ArrayList lines = new ArrayList();
        //ArrayList para erros
        ArrayList error = new ArrayList();
        String temp = "";
        int controlError = 0;
        
        //Retirando todos os spaces
        for (i = 0; i < text.size(); i++) {
            temp = text.get(i).toString().replaceAll(" ", "");
            lines.add(temp);
        }
        
        for (i = 0; i < lines.size(); i++) {
            controlIf = 0;
            aux = lines.get(i).toString();
            
            for (int h = 0; h < aux.length(); h++) {
                //If
                if (aux.charAt(h) == 'I') {
                } // While
                else if ((aux.charAt(h) == 'W') && controlIf == 0) {
                    contkey++;
                }//Key Closed
                else if ((aux.charAt(h) == '}') && controlIf == 0) {
                    auxkey++;
                } // Get
                 else if ((aux.charAt(h) == 'G') && controlIf == 0) {
                     
                 } //Atribuição
                else if ((aux.charAt(h) == '=') && controlIf == 0) { 
                }//Operação
                else if (aux.charAt(h) == '*' || aux.charAt(h) == '+'
                        || aux.charAt(h) == '/' || aux.charAt(h) == '-' || aux.charAt(h) == '%') {
                    if (controlIf == 0) {
                    }
                }//Print
                else if (aux.charAt(h) == 'P' && controlIf == 0) {
                }
            }
        }
        //Chaves
        if (contkey != auxkey) {
            controlError = 1;
            error.add("[ERRO] Cheque se as chaves foram abertas ('{') e fechadas ('}')");
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
