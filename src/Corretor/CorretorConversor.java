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
        int contkey = 0, auxkey = 0;
        //ArrayList para cada linha do código digitado
        ArrayList lines = new ArrayList();
        //ArrayList para erros
        ArrayList error = new ArrayList();
        String temp = "";
        int controlError = 0;
        char cmds[] = {'+','-','*','/','%','=',
                         'W','I','P','G','}'};

        //Retirando todos os spaces
        for (i = 0; i < text.size(); i++) {
            temp = text.get(i).toString().replaceAll(" ", "");
            lines.add(temp);
        }

        for (i = 0; i < lines.size(); i++) {
            controlIf = 0;
            aux = lines.get(i).toString();
            int linerror = i + 1;
            String tst = null;
            if(aux.charAt(0) != '\n' || aux != null || aux.length() != 0){
            tst = aux.charAt(0) + "";}
            int found = -1;
            //Checa se o comando existe
            if(tst != null){
            for(int f = 0;f < cmds.length;f++){
                String txt = cmds[f] + "";
               if(tst.indexOf(txt) != -1){
                   found++;
               }
            }
            if(found == -1){
                controlError = 1;
                error.add("[ERRO - Linha " + linerror + "] Comando não existe");
            }
            }
            if(found != -1){
            for (int h = 0; h < aux.length(); h++) {
                //If
                if (aux.charAt(h) == 'I') {
                    controlIf = 1;
                    ArrayList resultIf = new ArrayList();
                    resultIf = corretorIf.corrigeIf(aux, h, linerror);
                    if (resultIf.size() > 0) {
                        controlError = 1;
                        for(int p = 0;p < resultIf.size();p++){
                        error.add(resultIf.get(p));
                        }
                    }
                } // While
                else if ((aux.charAt(h) == 'W') && controlIf == 0) {
                    contkey++;
                    String result = null;
                    result = corretorW.corrigeWhile(aux, h, linerror);
                    if (result != null) {
                        controlError = 1;
                        error.add(result);
                    }
                    break;
                }//Key Closed
                else if ((aux.charAt(h) == '}') && controlIf == 0) {
                    auxkey++;
                    break;
                } // Get
                else if ((aux.charAt(h) == 'G') && controlIf == 0) {
                    String result = null;
                    result = corretorG.corrigeGets(aux, h, linerror);
                    if (result != null) {
                        controlError = 1;
                        error.add(result);
                    }
                    break;
                } //Atribuição
                else if ((aux.charAt(h) == '=') && controlIf == 0) {
                    String result = null;
                    result = corretorAt.corrigeAt(aux, h, linerror);
                    if (result != null) {
                        controlError = 1;
                        error.add(result);
                    }
                    break;
                }//Operação
                else if (aux.charAt(h) == '*' || aux.charAt(h) == '+'
                        || aux.charAt(h) == '/' || aux.charAt(h) == '-' || aux.charAt(h) == '%') {
                    if (controlIf == 0) {
                        String result = null;
                        result = corretorOp.corrigeOps(aux, h, linerror);
                        if (result != null) {
                            controlError = 1;
                            error.add(result);
                        }
                    }
                    break;
                }//Print
                else if (aux.charAt(h) == 'P' && controlIf == 0) {
                    String result = null;
                    result = corretorP.corrigePrint(aux, h, linerror);
                    if (result != null) {
                        controlError = 1;
                        error.add(result);
                    }
                    break;
                }
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
