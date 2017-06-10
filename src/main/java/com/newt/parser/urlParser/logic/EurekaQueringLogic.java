/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.logic;

import com.newt.parser.urlParser.model.Endpoint;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shyams
 */
@Service
public class EurekaQueringLogic {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Set<String> eurekaQueringLogic(List<String> depedentServiceList) {

        List<String> serviceUpListFromEureka = new ArrayList<String>();
        Set<String> servicesDownList = new HashSet<String>();

        for (String serviceName : this.discoveryClient.getServices()) {
            List<ServiceInstance> serviceInstanceList = this.discoveryClient.getInstances(serviceName);
            for (ServiceInstance serviceInstance : serviceInstanceList) {
                try {
                    InetAddress address = InetAddress.getByName(new URL(serviceInstance.getUri().toString()).getHost());

                    StringBuilder str;
                    if (address.isSiteLocalAddress()) {
                        str = new StringBuilder();
                        str.append(serviceInstance.isSecure() ? "https://" : "http://");
                        str.append("localhost:");
                        str.append(serviceInstance.getPort());
                        str.append("/");
                        serviceUpListFromEureka.add(str.toString());
                    } else {
                        serviceUpListFromEureka.add(serviceInstance.getUri().toString());
                        System.out.println("serviceInstance-getServiceId->" + serviceInstance.getServiceId());
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(EurekaQueringLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(EurekaQueringLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        StringBuilder str;
            for (String serviceUrl : depedentServiceList) {
                str = new StringBuilder();
                String urlArray[] = serviceUrl.split("/");
                if (urlArray.length >= 3) {
                    str.append(urlArray[0]);
                    str.append("//");
                    str.append(urlArray[1]);
                    str.append(urlArray[2]);
                    str.append("/");
                }
                if (!serviceUpListFromEureka.contains(str.toString())) {
                    servicesDownList.add(str.toString());
                }
            }

            return servicesDownList;
    }

}
