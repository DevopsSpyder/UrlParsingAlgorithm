/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.ParsingAlgorithm;

import com.github.javaparser.ParseException;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author shyams
 */
public class VariableVauleExtractor {
    
    public static void main(String[] args) throws IOException, ParseException {
        new VariableVauleExtractor().variableExtract();
    }
    
    public void variableExtract() throws IOException, ParseException {
//    public Set<String> variableExtract() throws IOException, ParseException {
        File src = new File("D:/Shyam/Code base/DemoForVenkat/urlParser/src/main/java/com/newt/parser/urlParser/controller");
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());
//		combinedTypeSolver.add(new ReflectionFieldDeclaration());

        combinedTypeSolver.add(new JavaParserTypeSolver(src));
//		combinedTypeSolver.add(new JavaParserTypeSolver(
//				new File("C:/Users/shyams/Documents/ParsingCode/javasymbolsolver-master jamalvali/javasymbolsolver-master/java-symbol-solver-core/target/generated-sources/javacc")));
        VariableCollectingAlgorithm sourceFileInfoExtractor = new VariableCollectingAlgorithm();
        sourceFileInfoExtractor.setTypeSolver(combinedTypeSolver);
        sourceFileInfoExtractor.solve(src);
        System.out.println("OK " + sourceFileInfoExtractor.getOk());
        System.out.println("KO " + sourceFileInfoExtractor.getKo());
        System.out.println("UNSUPPORTED " + sourceFileInfoExtractor.getUnsupported());
//        System.out.println("UrlList " + sourceFileInfoExtractor.getUrlList());
        
//        return sourceFileInfoExtractor.getUrlList();
    }
    
}
