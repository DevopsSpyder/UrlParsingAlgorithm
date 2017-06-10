/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shyams
 */
public class ClassList {

    private static Map<String, ClassDetails> methodsList = new HashMap<String, ClassDetails>();

    private static ClassList classList;
    
    private ClassList(){
    
    }
    
    public static ClassList getInstance(){
    
        if(classList==null)
            classList=new ClassList();
        
        return classList;
    }
    
    public ClassDetails getClassDetails(String className) {
        return methodsList.get(className);
    }

    public ClassDetails addClassDetails(String className) {
        ClassDetails classDetails;
        if (methodsList.get(className) == null) {
            classDetails = new ClassDetails(className);
            methodsList.put(className, classDetails);
            return classDetails;
        }
        return methodsList.get(className);
    }

    public ClassDetails removeClassDetails(String className) {
        return methodsList.remove(className);
    }

}
