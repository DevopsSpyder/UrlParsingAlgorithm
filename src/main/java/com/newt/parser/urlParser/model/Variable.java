/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

/**
 *
 * @author shyams
 */
public class Variable {
    
    String variableName;
//    String variableType;
    String variableValue;
    Boolean isDependent=true;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

//    public String getVariableType() {
//        return variableType;
//    }
//
//    public void setVariableType(String variableType) {
//        this.variableType = variableType;
//    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    public Boolean getIsDependent() {
        return isDependent;
    }

    public void setIsDependent(Boolean isDependent) {
        this.isDependent = isDependent;
    }
    
    @Override
    public String toString()
    {
        return "Variable [variableName = "+variableName+", variableValue = "+variableValue+", isDependent = "+isDependent+"]";
    }
}
