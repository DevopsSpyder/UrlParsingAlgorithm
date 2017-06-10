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
public class ClassDetails {
    
    String className;
    List<Method> methodsList=new ArrayList<Method>();
    List<Variable> globalVariablesList=new ArrayList<Variable>();

    public ClassDetails(String className){
        this.className=className;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Method> getMethodsList() {
        return methodsList;
    }

//    public void setMethodsList(List<Method> methodsList) {
//        this.methodsList = methodsList;
//    }

    public List<Variable> getGlobalVariablesList() {
        return globalVariablesList;
    }

//    public void setGlobalVariablesList(List<Variable> globalVariablesList) {
//        this.globalVariablesList = globalVariablesList;
//    }
    
    public Method getMethod(String methodSignature){
        for(Method method:methodsList){
            if(methodSignature.equals(method.getMethodSignature()))
                return method;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        return "ClassDetails [globalVariablesList = "+globalVariablesList+", className = "+className.toString()+", "
                + "methodsList = "+methodsList+"]";
    }

}
