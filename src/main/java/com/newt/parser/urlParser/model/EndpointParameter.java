/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.model;

import com.newt.parser.urlParser.util.CommonUtil;

/**
 *
 * @author shyams
 */
public class EndpointParameter {
        public CommonUtil.ParameterType parameterType = CommonUtil.ParameterType.PAYLOAD;
        public String javaType;
        public String defaultValue;
        public String name;
        
        @Override
	public String toString() {
		return "Product [parameterType=" + parameterType + ", javaType=" + javaType
				+ ", defaultValue=" + defaultValue + ", name="
				+ name + "]";
	}
    }
