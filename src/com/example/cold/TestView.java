package com.example.cold;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import com.example.antlr.*;

public class TestView {
	
	public View v;
	private EditText inputTextView;
	private Button okButton;
	private EditText outputTextView;
	
	public TestView(Context context) {		
		LayoutInflater vi = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.test_layout, null);		
		inputTextView = (EditText) v.findViewById(R.id.editText1);
		okButton = (Button) v.findViewById(R.id.button1);
		outputTextView = (EditText) v.findViewById(R.id.editText2);
		setupCallbacks();
	}	
	
	private void setupCallbacks() {
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String func = inputTextView.getText().toString();
				ANTLRInputStream input = new ANTLRInputStream(func);
		        ComplexLexer lexer = new ComplexLexer(input);
		        CommonTokenStream tokens = new CommonTokenStream(lexer);
		        ComplexParser parser = new ComplexParser(tokens);
		        ParseTree tree = parser.prog(); // parse

		        EvalPrefixVisitor eval = new EvalPrefixVisitor();
		        String prefixFunc = eval.visit(tree);
		        
		        outputTextView.setText(prefixFunc);
			}
		});
	}
	
	
}
