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
	private int maxIterations;
	
	public ComplexExpression() {
		expr = "z";
		variables = new HashMap<String, ComplexVariable>();
		activeVar = "";
		maxIterations = 1;
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
			System.out.println("expr=" + expr);
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
	
	public int getMaxIerations() {
		return maxIterations;
	}
	
	public void setMaxIterations(int m) {
		if (m > 0) {
			maxIterations = m;
		} else {
			maxIterations = 1;
		}
	}
	
	// check if variables in otherExpression match variables in this expression
	// and if yes, take their values
	public void takeOverVariablesFrom(ComplexExpression otherExpression) {
		for (String otherVarName : otherExpression.getVariables().keySet()) {
			if (variables.containsKey(otherVarName)) {
				ComplexVariable cVar = variables.get(otherVarName);				
				cVar.updateAll(otherExpression.getVariable(otherVarName));
			}
		}
	}
}
