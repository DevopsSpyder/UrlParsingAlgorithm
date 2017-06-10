/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newt.parser.urlParser.ParsingAlgorithm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
//import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.newt.parser.urlParser.model.Endpoint;
import com.newt.parser.urlParser.util.CommonUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javaslang.collection.IndexedSeq;

//import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
//import com.github.javaparser.symbolsolver.javaparsecom.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolverrmodel.JavaParserFacade;
//import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
//import com.github.javaparser.symbolsolver.model.typesystem.ReferenceType;
//import com.github.javaparser.symbolsolver.model.typesystem.Type;
//import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
//import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
//import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
/**
 *
 * @author shyams
 */
public class ParsingHardCodedUrl {

    List<String> methodNames = new ArrayList<String>();
    List<Endpoint> endpointList = new ArrayList<Endpoint>();
    List<String> endpointUri = new ArrayList<String>();

    public static void main(String[] args) {
        List<String> url = Arrays.asList(CommonUtil.sourceCode);
        Map<Integer, List<String>> endPoints = new HashMap<Integer, List<String>>();
//        String packagename = "com.newt.parser.urlParser.controller";
//        String packagename = "com.newt.parser.urlParser";
        StringBuilder str;
        int i = 1;
        for (String uri : url) {
            str = new StringBuilder();
            str.append(uri).append("/src/main/java");
            ParsingHardCodedUrl parsingCo = new ParsingHardCodedUrl();
            endPoints.put(i++, parsingCo.javaParser(str.toString()));
        }
        System.out.println("endPoints---"+endPoints);
    }

    public List<String> javaParser(String packagename) {

        try {
            // Open the file that is the first 
            // command line parameter
            List<String> classNames = getClasses(packagename);

            System.out.println("classNames-->" + classNames);
            for (String className : classNames) {
//                System.out.println("className-->" + className);
//                InputStream in1 = getClass().getResourceAsStream("/Utils/CEP/Ciades/" + className + ".txt");
//                InputStream in1 = getClass().getResourceAsStream(className);
//                Reader fr = new InputStreamReader(in, "utf-8");
//            InputStream in = null;
//            CompilationUnit cu = null;
//            try
//            {
//                    in = getClass().getResourceAsStream(className);
////                    in = new SEDInputStream(className);
//                    cu = JavaParser.parse(in);
//            }
//            catch(ParseException x)
//            {
//                 // handle parse exceptions here.
//            }
//            finally
//            {
//                  in.close();
//            }
//            return cu;
                File fle = new File(className);
//                File fle = new File("src/main/java/" + className);

                CompilationUnit cu;
                cu = JavaParser.parse(fle);
                new MethodVisitor().visit(cu, className);
                
//                TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver(), 
//                        new JavaParserTypeSolver(
//                                fle));
//                Node node = cu.getParentNode();
//                Type typeOfTheNode = JavaParserFacade.get(typeSolver).getType(node);
//                System.out.println("typeOfTheNode--->"+typeOfTheNode.toString());
//                System.out.println("fle--->"+fle.getAbsolutePath());
//                FileInputStream fstream = new FileInputStream(fle.getAbsolutePath());
//                // Get the object of DataInputStream
//                DataInputStream in = new DataInputStream(fstream);
//                BufferedReader br = new BufferedReader(new InputStreamReader(in));
//                String strLine;
//                //Read File Line By Line
//                while ((strLine = br.readLine()) != null) {
//                    // Print the content on the console
//                    System.out.println(strLine);
//                }
//                //Close the input stream
//                in.close();

            }
            System.out.println("endpointList--->"+endpointList);
        } catch (Exception e) {//Catch exception if any
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
        return endpointUri;
    }

    /**
     * Returns all of the classes in the specified package (including
     * sub-packages).
     */
    @SuppressWarnings("rawtypes")
    private static List<String> getClasses(String pkg) throws IOException, ClassNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        // turn package into the folder equivalent
        String path = pkg.replace('.', '/');
//        System.out.println("path--->"+pkg);
//        Enumeration<URL> resources = classloader.getResources(path);
        List<File> dirs = new ArrayList<File>();
//        while (resources.hasMoreElements()) {
            
//            URL resource = resources.nextElement();
//            System.out.println("resources--->"+resource.getFile());
            dirs.add(new File(pkg));
//        }
//System.out.println("dirs--->"+dirs);
        ArrayList<String> classes = new ArrayList<String>();
        for (File directory : dirs) {
            classes.addAll(getClasses(directory, pkg));
        }
        return classes;
    }

    @SuppressWarnings("rawtypes")
    private static Set<String> getClasses(File dir, String pkg) throws ClassNotFoundException {
        Set<String> classes = new HashSet<String>();
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
//            System.out.println("file--->"+file.getAbsolutePath());
            if (file.isDirectory()) {
//                classes.addAll(getClasses(file, pkg + file.getName()));
                classes.addAll(getClasses(file, pkg + "." + file.getName()));
            } else if (file.getName().endsWith(".java")) {
//            } else if (file.getName().endsWith(".class")) {
//                StringTokenizer fileName = new StringTokenizer(file.getName().substring(0, file.getName().length() - 5), "$");
//                String replaced=fileName.nextToken().replace('.', '/');
//                System.out.println("fileName.nextToken()--->"+fileName.nextToken());
//System.out.println("pkg--->"+pkg);
                classes.add(pkg.replace('.', '/') + '/' + file.getName());
            }
        }
        return classes;
    }

    private class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodCallExpr methodCall, Object arg) {
            Endpoint endpoint;//=new Endpoint();
//            System.out.print("Method call: " + methodCall.getName() + "\n");
            if (CommonUtil.REST_TEMPLATE_METHOD_NAME.contains(methodCall.getNameAsString())) {
                List<Expression> args = methodCall.getArguments();
//                System.out.println("args.get(0).toString()-->"+args.get(0).toString());
                String url=args.get(0).toString().trim();
                if (args != null && url.startsWith("\"http")) {
                    endpoint = new Endpoint();
//                    System.out.println("arg--->"+arg);
                    endpoint.javaClass = String.valueOf(arg).replace(".java", "");//.replace("/", ".");
                    endpoint.javaMethodName = methodCall.getName().asString();
                    endpoint.method = CommonUtil.MethodEnum.GET;
                    String uri=args.get(0).toString().replaceAll("\"", "");;
                    endpoint.uri = uri;
                    endpointUri.add(uri);
                    endpoint.returnType = args.get(1).toString();
                    endpointList.add(endpoint);
                }

            }
        }

//        @Override
//        public void visit(VariableDeclarationExpr n, Object arg) {
//            System.out.println("n.getChildrenNodes().get(0).toString()--->"+n.getChildrenNodes().get(0).toString());
//            System.out.println("n.getChildrenNodes().size()--->"+n.getChildrenNodes().size());
//            System.out.println("n.getParentNode().get(0).toString()--->"+n.getParentNode().toString());
//            System.out.println("n.getType().toString()--->"+n.getType().toString());
//            System.out.println("n.getType().toString()--->"+n.getType().toString());
//            List<VariableDeclarator> myVars = n.getVars();
//            for (VariableDeclarator vars : myVars) {
//                System.out.println("Variable Name: " + vars.getId().getName());
//                System.out.println("Variable Name: " + vars.getId().toString());
//                System.out.println("Variable Name: " + vars.toString());
//            }
//        }
        
//        @Override
//        public void visit(FieldDeclaration n, Object arg) {
////            System.out.println(n);
//            System.out.println("n.toString()"+n.toString());
//        }

        private void handleExpressions(List<Expression> expressions,Endpoint endpoint) {
            for (Expression expr : expressions) {
                System.out.println("expr--->" + expr.toString());
//                System.out.println("expr--->"+expr);
                if (expr instanceof MethodCallExpr) {
                    visit((MethodCallExpr) expr, null);
                } else if (expr instanceof BinaryExpr) {
                    BinaryExpr binExpr = (BinaryExpr) expr;
//                    System.out.println("binExpr--->" + binExpr.toString());
//                    handleExpressions(Arrays.asList(binExpr.getLeft(), binExpr.getRight()));
                }
            }
        }
    }
}
