/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.ParsingAlgorithm;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;
import static com.github.javaparser.symbolsolver.javaparser.Navigator.getParentNode;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.declarations.FieldDeclaration;
import com.github.javaparser.symbolsolver.model.declarations.TypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceType;
import com.newt.parser.urlParser.model.ClassDetails;
import com.newt.parser.urlParser.model.ClassList;
import com.newt.parser.urlParser.model.Method;
import com.newt.parser.urlParser.model.Variable;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static com.newt.parser.urlParser.ParsingAlgorithm.SourceFileVariableValueExtractor.secondIndexOf;

/**
 *
 * @author shyams
 */
public class VariableCollectingAlgorithm {

    private TypeSolver typeSolver;

    private int ok = 0;
    private int ko = 0;
    private int unsupported = 0;
    private boolean printFileName = true;
    private PrintStream out = System.out;
    private PrintStream err = System.err;

    private ClassDetails currentClassDetails;

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    private boolean verbose = false;

    public void setPrintFileName(boolean printFileName) {
        this.printFileName = printFileName;
    }

    public void clear() {
        ok = 0;
        ko = 0;
        unsupported = 0;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public void setErr(PrintStream err) {
        this.err = err;
    }

    public int getOk() {
        return ok;

    }

    public int getUnsupported() {
        return unsupported;
    }

    public int getKo() {
        return ko;
    }

    private void solveTypeDecl(ClassOrInterfaceDeclaration node) {
        TypeDeclaration typeDeclaration = JavaParserFacade.get(typeSolver).getTypeDeclaration(node);
        if (typeDeclaration.isClass()) {
            out.println("\n[ Class " + typeDeclaration.getQualifiedName() + " ]");
            for (ReferenceType sc : typeDeclaration.asClass().getAllSuperClasses()) {
                out.println("  superclass: " + sc.getQualifiedName());
                System.out.println("currentClassDetails--->" + currentClassDetails);
                currentClassDetails = ClassList.getInstance().addClassDetails(sc.getQualifiedName());
            }
            for (ReferenceType sc : typeDeclaration.asClass().getAllInterfaces()) {
                out.println("  interface: " + sc.getQualifiedName());
            }
        }
    }

    private void solve(Node node) {
        if (node instanceof ClassOrInterfaceDeclaration) {
            solveTypeDecl((ClassOrInterfaceDeclaration) node);
        } else if (node instanceof Expression) {
            if ((getParentNode(node) instanceof MethodDeclaration)) {
                MethodDeclaration md = (MethodDeclaration) (getParentNode(node));
//                System.out.println("md.getChildNodesByType(VariableDeclarator)--->"
//                        + md.getChildNodesByType(VariableDeclarator.class));
//                System.out.println("md.getSignature().asString()--->"+md.getSignature().asString());
                Method method = new Method(md.getSignature().asString());
                List<VariableDeclarator> variableDeclaratorNodeList = md.getChildNodesByType(VariableDeclarator.class);
                List<AssignExpr> assignExprNodeList = md.getChildNodesByType(AssignExpr.class);

//                List<Node> nodeList=md.getChildNodes();
                Variable variable;
//                VariableDeclarator variableDeclarator;
//                AssignExpr assignExpr;

//                for (Node methodNodes : nodeList) {
//                    System.out.println("methodNodes--->"+methodNodes);
//                    if (methodNodes instanceof VariableDeclarator) {
//                        System.out.println("variable");
//                        variableDeclarator = (VariableDeclarator) getParentNode(methodNodes);
//                    }
//                }
                for (VariableDeclarator variableDeclarator : variableDeclaratorNodeList) {
//                    System.out.println("value--->" + variableDeclarator.toString());
                    String value = variableDeclarator.toString().replaceAll("\\s+", "");
                    String str = "";
                    boolean eligibleToAdd = false;
//                            int equalsIndex=value.indexOf("=");
//                            String afterEqualsPart=value.substring(equalsIndex+1);
//                            System.out.println("afterEqualsPart" + afterEqualsPart);
//                            System.out.println("variableDeclarator.getChildNodes()" + variableDeclarator.getChildNodes().get(0).toString().replaceAll("\\s+", ""));
//                        if(afterEqualsPart.startsWith("\"") || afterEqualsPart.startsWith("newString")){
//                                int index = afterEqualsPart.indexOf("\"");
//                                int finalIndex = ordinalIndexOf(afterEqualsPart);
//                                if (afterEqualsPart.startsWith("\"")) {
//                                    afterEqualsPart = afterEqualsPart.substring(index + 1, afterEqualsPart.length() - 1);
//                                } else {
//                                    afterEqualsPart = afterEqualsPart.substring(index + 1, finalIndex);//replaceAll("newString(", "");
//                                }
//                                variable = new Variable();
//                                variable.setVariableName(variableDeclarator.getNameAsString());
//                                variable.setVariableValue(afterEqualsPart);
//                                method.getVariablesList().add(variable);
//                                currentClassDetails.getMethodsList().add(method);
//                        }

                    if (value.contains("=")) {
                        int equalsIndex = value.indexOf("=");
                        String afterEqualsPart = value.substring(equalsIndex + 1);
//                        System.out.println("value-->"+value);
//                        if(afterEqualsPart.startsWith("\"") || afterEqualsPart.startsWith("newString(\"")
//                                || afterEqualsPart.startsWith("newStringBuilder(\"") || afterEqualsPart.startsWith("newStringBuffer(\"") ){
                            str = AssignExprSolver.concatSolverV2(afterEqualsPart, md.getSignature().asString(), currentClassDetails);
                            eligibleToAdd = true;
//                        }
                    } else {
                        
                        if ("String".equals(variableDeclarator.getType().asString())
                                || "StringBuilder".equals(variableDeclarator.getType().asString())
                                || "StringBuffer".equals(variableDeclarator.getType().asString())) {
                            eligibleToAdd = true;
                        }

                    }
                    if (eligibleToAdd) {
                        variable = new Variable();
                        variable.setVariableName(variableDeclarator.getNameAsString());
                        variable.setVariableValue(str);
                        method.getVariablesList().add(variable);
//                        System.out.println("method--->"+method.getMethodSignature());
//                        System.out.println("variableDeclarator.getNameAsString()--->"+variableDeclarator.getNameAsString());
                        currentClassDetails.getMethodsList().add(method);
                    }
//                    } else if (getParentNode(methodNodes) instanceof AssignExpr) {
//                        assignExpr = (AssignExpr) getParentNode(methodNodes);
//                        String str=AssignExprSolver.concatSolver(assignExpr.getValue().toString(), md.getSignature().asString(), currentClassDetails);
//                        System.out.println("str--->"+str);
//                    }
                }

                for (AssignExpr fieldDeclaration : assignExprNodeList) {
//                    System.out.println("fieldDeclaration-getOperator-->"+fieldDeclaration.getOperator());
//                    System.out.println("fieldDeclaration-getClass-->"+fieldDeclaration.getClass());
//                    System.out.println("statement--->"+fieldDeclaration.toString());
//                    System.out.println("statement-getValue()-->"+fieldDeclaration.getTarget().toString());
//                    System.out.println("statement-getValue()-->"+fieldDeclaration.getValue().toString());
                    String afterEqualsPart = fieldDeclaration.getValue().toString();
                    String str = "";

//                    System.out.println("afterEqualsPart--->"+afterEqualsPart);
//                    if (afterEqualsPart.startsWith("\"") || afterEqualsPart.startsWith("newString(\"")
//                            || afterEqualsPart.startsWith("newStringBuilder(\"") || afterEqualsPart.startsWith("newStringBuffer(\"")) {
                        str = AssignExprSolver.concatSolverV2(afterEqualsPart, md.getSignature().asString(), currentClassDetails);
                        System.out.println("str--->" + str);
                        variable = new Variable();
                        variable.setVariableName(fieldDeclaration.getTarget().toString());
                        variable.setVariableValue(str);
                        method.getVariablesList().add(variable);
//                    }
                }
            }

        }
    }

    public static int ordinalIndexOf(String str) {
        int pos = str.indexOf("\"");
        int n = 2;
        while (--n > 0 && pos != -1) {
            pos = str.indexOf("\"", pos + 1);
        }
        return pos;
    }

    private void solveMethodCalls(Node node) {
        if (node instanceof MethodCallExpr) {
            out.println("  Line " + node.getBegin().get().line + ") " + node + " ==> " + toString((MethodCallExpr) node));
        }
        for (Node child : node.getChildNodes()) {
            solveMethodCalls(child);
        }
    }

    private String toString(MethodCallExpr node) {
        try {
            return toString(JavaParserFacade.get(typeSolver).solve(node));
        } catch (Exception e) {
            if (verbose) {
                System.err.println("Error resolving call at L" + node.getBegin().get().line + ": " + node);
                e.printStackTrace();
            }
            return "ERROR";
        }
    }

    private String toString(SymbolReference<com.github.javaparser.symbolsolver.model.declarations.MethodDeclaration> methodDeclarationSymbolReference) {
        if (methodDeclarationSymbolReference.isSolved()) {
            return methodDeclarationSymbolReference.getCorrespondingDeclaration().getQualifiedSignature();
        } else {
            return "UNSOLVED";
        }
    }

    private List<Node> collectAllNodes(Node node) {
        List<Node> nodes = new LinkedList<>();
        collectAllNodes(node, nodes);
        nodes.sort((n1, n2) -> n1.getBegin().get().compareTo(n2.getBegin().get()));
        return nodes;
    }

    private void collectAllNodes(Node node, List<Node> nodes) {
        nodes.add(node);
        node.getChildNodes().forEach(c -> collectAllNodes(c, nodes));
    }

    public void solve(File file) throws IOException, ParseException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                solve(f);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                if (printFileName) {
                    out.println("- parsing " + file.getAbsolutePath());
                }
                CompilationUnit cu = JavaParser.parse(file);
                List<Node> nodes = collectAllNodes(cu);
                nodes.forEach(n -> solve(n));
            }
        }
    }

    public void solveMethodCalls(File file) throws IOException, ParseException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                solveMethodCalls(f);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                if (printFileName) {
                    out.println("- parsing " + file.getAbsolutePath());
                }
                CompilationUnit cu = JavaParser.parse(file);
                solveMethodCalls(cu);
            }
        }
    }

    public void setTypeSolver(TypeSolver typeSolver) {
        this.typeSolver = typeSolver;
    }

}
