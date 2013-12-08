package com.example.cold;

import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.example.antlr.ComplexLexer;
import com.example.antlr.ComplexParser;
import com.example.antlr.EvalPrefixVisitor;


public class ComplexExpression {
	private String expr;
	private HashMap<String, ComplexVariable> variables;
	private String activeVar;
	
	public ComplexExpression() {
		expr = "z";
		variables = new HashMap<String, ComplexVariable>();
		activeVar = "";
	}
	
	// parse String 'expression' via ANTLR generated parser, 
	// save function in prefix notation for shader and generate
	// variables (if there are any in the expression)
	public boolean parseExpression(String expression) {
		try {
			ANTLRInputStream input = new ANTLRInputStream(expression);
			ComplexLexer lexer = new ComplexLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ComplexParser parser = new ComplexParser(tokens);
			ParseTree tree = parser.prog();

			EvalPrefixVisitor evalVisitor = new EvalPrefixVisitor();
			evalVisitor.reset();
			evalVisitor.visit(tree);
			
			expr = evalVisitor.resultFunc;
			variables.clear();
			for (String var : evalVisitor.variables) {
				variables.put(var, new ComplexVariable(var, 1.0f, 0.0f));
			}
			return true;
		} catch (RuntimeException e) {
			expr = "";
			activeVar =  "";
			variables.clear();
			return false;
		}
	}
	
	public String getExpression() {
		return expr;
	}
	
	public HashMap<String,ComplexVariable> getVariables() {
		return variables;
	}
	
	public ComplexVariable getVariable(String name) {
		return variables.get(name);
	}
	
	public void setActiveVariable(String name) {
		if ( ! variables.containsKey(name)) {
			activeVar = "";
		} else {
			activeVar = name;
		}
	}
	
	public ComplexVariable getActiveVariable() {		
		if (activeVar.equals("")) {
			return null;
		}
		return variables.get(activeVar);
	}
}
