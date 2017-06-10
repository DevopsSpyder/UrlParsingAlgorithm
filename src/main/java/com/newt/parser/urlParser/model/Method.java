/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shyams
 */
public class Method {
    
    String methodSignature;
    List<Variable> variablesList=new ArrayList<Variable>();

    public Method(String methodSignature){
        this.methodSignature=methodSignature;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }
    
    public List<Variable> getVariablesList() {
        return variablesList;
    }
    
    public Variable getVariable(String variableName){
        for(Variable variable:variablesList){
            if(variableName.equals(variable.getVariableName()))
                return variable;
        }
        return null;
    }

//    public void setVariablesList(List<Variable> variablesList) {
//        this.variablesList = variablesList;
//    }
    @Override
    public String toString()
    {
        return "Method [variablesList = "+variablesList+", methodSignature = "+methodSignature+"]";
    }

}
