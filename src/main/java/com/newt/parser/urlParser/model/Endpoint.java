/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

import com.newt.parser.urlParser.util.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shyams
 */
public class Endpoint {
        public String uri;
        public CommonUtil.MethodEnum method;
        
        public String javaClass;
        public String javaMethodName;
        public String returnType;
        
        public List<EndpointParameter> queryParameters = new ArrayList<EndpointParameter>();
        public List<EndpointParameter> pathParameters = new ArrayList<EndpointParameter>();
        public List<EndpointParameter> payloadParameters = new ArrayList<EndpointParameter>();        
        
        @Override
	public String toString() {
		return "Product [javaClass=" + javaClass + ", javaMethodName=" + javaMethodName
				+ ", queryParameters=" + queryParameters.toString() + ", pathParameters="
				+ pathParameters.toString() + " , payloadParameters=" + payloadParameters.toString() +", uri="+uri+", returnType="+returnType+"] ";
	}

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public CommonUtil.MethodEnum getMethod() {
        return method;
    }

    public void setMethod(CommonUtil.MethodEnum method) {
        this.method = method;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public String getJavaMethodName() {
        return javaMethodName;
    }

    public void setJavaMethodName(String javaMethodName) {
        this.javaMethodName = javaMethodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<EndpointParameter> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(List<EndpointParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public List<EndpointParameter> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(List<EndpointParameter> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public List<EndpointParameter> getPayloadParameters() {
        return payloadParameters;
    }

    public void setPayloadParameters(List<EndpointParameter> payloadParameters) {
        this.payloadParameters = payloadParameters;
    }
        
        
        
    }
