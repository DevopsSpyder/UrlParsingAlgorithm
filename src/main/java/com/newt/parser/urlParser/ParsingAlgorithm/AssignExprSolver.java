/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.ParsingAlgorithm;

import com.newt.parser.urlParser.model.ClassDetails;
import com.newt.parser.urlParser.model.ClassList;
import com.newt.parser.urlParser.model.Method;
import com.newt.parser.urlParser.model.Variable;

/**
 *
 * @author shyams
 */
public class AssignExprSolver {
    
    public static String concatSolver(String totalString, String methodSignature,ClassDetails currentClassDetails){
//        ClassDetails currentClassDetails=ClassList.getInstance().addClassDetails(className);
//System.out.println("totalString--->"+totalString);
        Method method=currentClassDetails.getMethod(methodSignature);
        Variable variable;
        String[] values=totalString.split("\\+");
        StringBuilder finalValue=new StringBuilder();
        for(String str:values){
            str=str.trim();
            
            if(str.contains("\"")){
                finalValue.append(str.replaceAll("\"", ""));
            } else {
                variable=method.getVariable(str);
                finalValue.append(variable.getVariableValue());
            }
            
        }
        return finalValue.toString();
    }
    public static String concatSolverV2(String totalString, String methodSignature,ClassDetails currentClassDetails){
//        ClassDetails currentClassDetails=ClassList.getInstance().addClassDetails(className);
//System.out.println("totalString--->"+totalString);
        Method method=currentClassDetails.getMethod(methodSignature);
        Variable variable;
        String[] values=totalString.split("\\+");
        StringBuilder finalValue=new StringBuilder();
        for(String str:values){
            str=str.trim();
            
            
            if(str.startsWith("\"") || str.startsWith("newString(\"")
                                || str.startsWith("newStringBuilder(\"") || str.startsWith("newStringBuffer(\"") ){
                int index = str.indexOf("\"");
                int finalIndex = ordinalIndexOf(str);
                str = str.substring(index + 1, finalIndex);
                finalValue.append(str.replaceAll("\"", ""));
            } else if(!"null".equals(String.valueOf(method.getVariable(str)))){
//                if(str.equals("newString()") || str.equals("newStringBuilder()") || str.equals("newString()"))
                variable=method.getVariable(str);
                finalValue.append(variable.getVariableValue());
            }
            
        }
        return finalValue.toString();
    }
    
    public static int ordinalIndexOf(String str) {
        int pos = str.indexOf("\"");
        int n = 2;
        while (--n > 0 && pos != -1) {
            pos = str.indexOf("\"", pos + 1);
        }
        return pos;
    }
    
}
