package com.example.cold;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import android.util.JsonReader;
import android.util.JsonWriter;

import com.example.antlr.ComplexLexer;
import com.example.antlr.ComplexParser;
import com.example.antlr.EvalPrefixVisitor;


public class ComplexExpression {
	private String expr;      // expression as used by openGL
	private String userExpr;  // expression as typed in by user
	private HashMap<String, ComplexVariable> variables;
	private String activeVar;
	private int maxIterations;
	
	public ComplexExpression() {
		expr          = "z";
		userExpr      = "z";
		variables     = new HashMap<String, ComplexVariable>();
		activeVar     = "";
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
	
	public String getUserExpression() {
		return userExpr;
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
	
	public String writeJson(String extraData) throws IOException {
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);
		
		writer.beginObject();
		writer.name("expr").value(expr);
		writer.name("extra").value(extraData);
		writer.name("maxIter").value(maxIterations);
		writer.name("variables");
		writer.beginArray();
		for (String var : variables.keySet()) {
			variables.get(var).writeJson(writer);
		}
		writer.endArray();
		writer.endObject();
		
		return sw.toString();
	}
	
	public void initFromJson(InputStream in) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in));
		
		variables.clear();
		
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("extra")) {
				this.userExpr = reader.nextString();
			} else if (name.equals("expr")) {
				this.expr = reader.nextString();
			} else if (name.equals("maxIter")) {
				this.maxIterations = reader.nextInt();
			} else if (name.equals("variables")) {
				readJsonVariables(reader);
			} else {
				reader.skipValue();
			}
		}
	}
	
	private void readJsonVariables(JsonReader reader) throws IOException {
		reader.beginArray();
		while (reader.hasNext()) {
			 ComplexVariable var = readVariable(reader);
			 variables.put(var.name, var);
		}
		reader.endArray();
	}
	
	private ComplexVariable readVariable(JsonReader reader) throws IOException {
		String varName = "";
		float real = 0, imag = 0, lowerRe = 0, upperRe = 0, lowerIm = 0, upperIm = 0;
		
		reader.beginObject();
		
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("name")) {
				varName = reader.nextString();
			} else if (name.equals("real")) {
				real = (float) reader.nextDouble();
			} else if (name.equals("imag")) {
				imag = (float) reader.nextDouble();
			} else if (name.equals("lowerRe")) {
				lowerRe = (float) reader.nextDouble();
			} else if (name.equals("upperRe")) {
				upperRe = (float) reader.nextDouble();
			} else if (name.equals("lowerIm")) {
				lowerIm = (float) reader.nextDouble();
			} else if (name.equals("upperIm")) {
				upperIm = (float) reader.nextDouble();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		ComplexVariable var = new ComplexVariable(varName, real, imag);
		var.setBoundsWoUpdate(lowerRe, upperRe, lowerIm, upperIm);
		return var;
	}
}
