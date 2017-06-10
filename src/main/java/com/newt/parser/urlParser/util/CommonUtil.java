/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author shyams
 */
public class CommonUtil {
    
    public final static List<String> REST_TEMPLATE_METHOD_NAME
            = Arrays.asList("getForObject","postForObject");
    
    public static final String[] sourceCode = {"C:/Users/shyams/Documents/DevOpsInBox/ECommerce-master",
        "C:/Users/shyams/Documents/DevOpsInBox/ECheckout_Microservice-master",
        "C:/Users/shyams/Documents/DevOpsInBox/ECustomer_Microservice-master",
        "C:/Users/shyams/Documents/DevOpsInBox/EEureka_Microservice-master"};
    
    public enum MethodEnum { PUT, POST, GET, DELETE }
    public enum ParameterType { QUERY, PATH, PAYLOAD, REQUEST_PARAM, PATH_PARAMS }
}
