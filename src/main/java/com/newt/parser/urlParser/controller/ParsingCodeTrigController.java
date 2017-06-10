/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.controller;

import com.newt.parser.urlParser.logic.EurekaQueringLogic;
import com.newt.parser.urlParser.ParsingAlgorithm.ParsingHardCodedUrl;
//import com.newt.parser.urlParser.logic.EurekaQueringLogic;
import com.newt.parser.urlParser.model.ServiceDetailsForUi;
import com.newt.parser.urlParser.util.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shyams
 */
@RestController
public class ParsingCodeTrigController {
    
    @Autowired
    EurekaQueringLogic eurekaQueringLogic;
    
    @RequestMapping("/parseCompletely")
    public List<ServiceDetailsForUi> parserTriggingPoint(){
        List<ServiceDetailsForUi> serviceDetailsForUiList=new ArrayList<ServiceDetailsForUi>();
        ServiceDetailsForUi serviceDetailsForUi;//=new ServiceDetailsForUi();
        List<String> url = Arrays.asList(CommonUtil.sourceCode);
        List<String> dependentServices;
        Set<String> downServices;
        StringBuilder str;
//        EurekaQueringLogic queringLogic=new EurekaQueringLogic();
        
        for (String uri : url) {
            serviceDetailsForUi=new ServiceDetailsForUi();
            str = new StringBuilder();
            str.append(uri).append("/src/main/java");
            ParsingHardCodedUrl parsingCo = new ParsingHardCodedUrl();
            dependentServices=parsingCo.javaParser(str.toString());
            downServices=eurekaQueringLogic.eurekaQueringLogic(dependentServices);
            
            String[] pathValue=uri.split("/");
            int index=pathValue.length;
            String serviceName=pathValue[index-1];
            serviceDetailsForUi.setServiceName(serviceName);
            serviceDetailsForUi.setDependentServices(dependentServices);
            serviceDetailsForUi.setDownServices(downServices);
            serviceDetailsForUiList.add(serviceDetailsForUi);
        }
        return serviceDetailsForUiList;
    }
    
}
