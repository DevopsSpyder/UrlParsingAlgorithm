/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author shyams
 */
public class ServiceDetailsForUi {
    
    String id;
    String serviceName;
    List<String> dependentServices;
    Set<String> downServices;
    List<String> upServices;
    
    public ServiceDetailsForUi(){
         dependentServices=new ArrayList<String>();
         downServices=new HashSet<String>();
         upServices=new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<String> getDependentServices() {
        return dependentServices;
    }

    public void setDependentServices(List<String> dependentServices) {
        this.dependentServices = dependentServices;
    }

    public Set<String> getDownServices() {
        return downServices;
    }

    public void setDownServices(Set<String> downServices) {
        this.downServices = downServices;
    }

    public List<String> getUpServices() {
        return upServices;
    }

    public void setUpServices(List<String> upServices) {
        this.upServices = upServices;
    }
    
}
