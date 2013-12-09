package com.example.cold;

public class ShaderSourceGenerator {
	
	private String srcVertexShader;
	private String srcFragShader;
	private ColdSettings.Coloring coloring;
	
	private String defaultVertexShader = 
		"uniform mat3 uViewMatrix;\n" +
		"uniform mat3 uMoveMatrix;\n" +	
		"attribute vec2 aPosition;\n" +
		"varying vec2 vPosition;\n" +
		"void main() {\n" +
		"	vec3 pos = uViewMatrix * uMoveMatrix * vec3(aPosition, 1.0);\n" +
		"	vPosition = (pos.xy / pos.z) * 1.0;\n" +
		"	gl_Position = vec4(aPosition, 0.0, 1.0);\n" +
		"}\n";
	
	private String fragmentShaderHeader = 
			"precision mediump float;\n" +
			"varying vec2 vPosition;\n";
	
	private String fragmentShaderComplexFuns = 
			"float PI = atan(1.0)*4.0;\n" +
			"float cAbs(vec2 z)\n" +
			"{\n" +
			"	return sqrt(z.x * z.x + z.y * z.y);\n" +
			"}\n" +
			"float cArg(vec2 z)\n" +
			"{\n" +
			"	return atan(z.y, z.x);\n" +
			"}\n" +
			"vec2 complexSin(vec2 z)\n" +
			"{\n" +
			"	float expy = exp(z.y);\n" +
			"	float expmy = exp(-z.y);\n" +
			"	return vec2(0.5*sin(z.x)*(expmy+expy), 0.5*cos(z.x)*(expy-expmy));\n" +
			"}\n" +
			"vec2 complexCos(vec2 z) {\n" +
			"	float expy = exp(z.y);\n" +
			"	float expmy = exp(-z.y);\n" +
			"	return vec2(0.5*cos(z.x)*(expmy+expy), 0.5*sin(z.x)*(expmy-expy));\n" +
			"}\n" +
			"vec2 complexPlus(vec2 z1, vec2 z2) {\n" +
			"	return vec2(z1.x + z2.x, z1.y + z2.y);\n" +
			"}\n" +
			"vec2 complexMinus(vec2 z1, vec2 z2) {" +
			"	return vec2(z1.x - z2.x, z1.y - z2.y);\n" +
			"}" +
			"vec2 complexMult(vec2 z1, vec2 z2) {\n" +
			"	return vec2(z1.x * z2.x - z1.y * z2.y, z1.x * z2.y + z1.y * z2.x);\n" +
			"}\n" +
			"vec2 complexDiv(vec2 z1, vec2 z2) {\n" +
			"	if (z2.y == 0.0)\n" +
			"	{\n" +
			"		return vec2(z2.x / z2.x, z1.y / z2.x);\n" +
    		"	}\n" +
    		"	if (z2.x == 0.0)\n" +
    		"	{\n" +
    		"		return vec2(z1.y / z2.y, -(z1.x / z2.y));\n" +
    		"	}\n" +
    		"	float r2 = z2.x * z2.x + z2.y * z2.y;\n" +
    		"	return vec2((z1.x * z2.x + z1.y * z2.y) / r2, (z1.y * z2.x - z1.x * z2.y) / r2);\n" +
			"}\n" +
			"vec2 complexExp(vec2 z) {\n" +
			"	float expx = exp(z.x);\n" +
			"	return vec2(expx*cos(z.y), expx*sin(z.y));\n" +
			"}\n" +
			"vec2 complexLog(vec2 z) {\n" +
			"	float r = sqrt(z.x*z.x + z.y*z.y);\n" +
			"	float theta = atan(z.y, z.x);\n" +
			//"	if (theta < 0.0)\n" +
			//"		theta += 2.0 * PI;\n" +
			"	return vec2(log(r), theta);\n" +
			"}\n";
	
	private String fragmentShaderHSVtoRGB = 
			"vec4 HSVtoRGB(float h, float s, float v)\n" +
			"{\n" +    
			"	int i;\n" +
			"	float f, p, q, t;\n" +
			"	vec4 colrgb;\n" +
			"	if (s == 0.0)\n" +
			"	{\n" +        
			"		colrgb.x = colrgb.y = colrgb.z = v;\n" +
			"		colrgb.w = 1.0;\n" +
			"		return colrgb;\n" +
    		"	}\n" +
    		"	h /= 60.0;\n" + 
    		"	i = int(floor(h));\n" +
    		"	f = h - float(i);\n" +
    		"	p = v * (1.0 - s);\n" +
    		" 	q = v * (1.0 - s * f);\n" +
    		" 	t = v * (1.0 - s * (1.0 - f));\n" +
    		" 	if(i==0)\n" +	
    		"	{\n" +
    		"		colrgb.x = v;\n" +
    		"		colrgb.y = t;\n" +
    		"		colrgb.z = p;\n" +
    		"	}\n" +
    		"	else if(i==1)\n" +
    		"	{\n" +
    		" 		colrgb.x = q;\n" +
    		"		colrgb.y = v;\n" +
    		"		colrgb.z = p;\n" +
    		"	}\n" +
    		"	else if(i==2)\n" +
    		"	{\n" +
    		"		colrgb.x = p;\n" +
    		"		colrgb.y = v;\n" +
    		"		colrgb.z = t;\n" +
    		"	}\n" +
    		"	else if(i==3)\n" +
    		"	{\n" +
    		"		colrgb.x = p;\n" +
    		"		colrgb.y = q;\n" +
    		"		colrgb.z = v;\n" +
    		"	}\n" +
    		"	else if(i==4)\n" +
    		"	{\n" +
    		"		colrgb.x = t;\n" +
    		"		colrgb.y = p;\n" +
    		"		colrgb.z = v;\n" +
    		"	}\n" +
    		"	else if(i==5)\n" +
    		"	{\n" +
    		"		colrgb.x = v;\n" +
    		"		colrgb.y = p;\n" +
    		"		colrgb.z = q;\n" +
    		"	}\n" +
    		"	else\n" +
    		"	{\n" +
    		"		colrgb.x = 1.0;\n" +
    		"		colrgb.y = 1.0;\n" +
    		"		colrgb.z = 1.0;\n" +
    		"	}\n" +
    		"	colrgb.w = 1.0;\n" +
    		"	return colrgb;\n" +
			"}\n";
	
	private String complexToHSB =
			"vec3 complex2hsb(vec2 z) {\n" +
			"	float h = cArg(z);\n" +
			"	if (h < 0.0)\n" +
			"		h += 2.0 * PI;\n" +
			"	h = degrees(h);\n" +
			"	float s = abs(sin(2.0*PI*length(z)));\n" +
			"	float b = pow(abs(sin(2.0*PI*z.y)*sin(2.0*PI*z.x)), 0.25);\n" +
			"	float b2 = 0.5 * ((1.0-s) + b + sqrt( (1.0-s-b)*(1.0-s-b) + 0.01 ));\n" +
			"	return vec3(h, sqrt(s), b2);\n" +
			"}\n";
	
	private String complexToColor = 
			"vec4 complexToColor(vec2 z)\n" +
			"{\n" +
			"	vec3 hsb = complex2hsb(z);\n" +
			"	return HSVtoRGB(hsb.x, hsb.y, hsb.z);\n" + 
			"}\n";
	
	private String complexToBlackWhite = 
			"vec4 complexToBlackWhite(vec2 z) {\n" +
			"	int x = int(z.x*2.0);\n" +
			"	int y = int(z.y*2.0);\n" +
			"	float xh = float(x)/2.0;\n" +
			"	float yh = float(y)/2.0;\n" +
			"	if (x - int(2.0*floor(xh))==0) {\n" +
			"		if (y - int(2.0*floor(yh))==0) {\n" +
			"			return vec4(1.0, 1.0, 1.0, 1.0);\n" +
			"		}\n" +
			"		else {\n" +
			"			return vec4(0.0, 0.0, 0.0, 1.0);\n" +
			"		}\n" +
			"	}\n" +
			"	else {\n" +
			"		if (y - int(2.0*floor(yh))!=0) {\n" +
			"			return vec4(1.0, 1.0, 1.0, 1.0);\n" +
			"		}\n" +
			"		else {\n" +
			"			return vec4(0.0, 0.0, 0.0, 1.0);\n" +
			"		}\n" +
			"	}\n" +
			"}\n";
	
	public ShaderSourceGenerator() {
		srcVertexShader = defaultVertexShader;
		coloring = ColdSettings.Coloring.standard;
	}
	
	public void generate(ComplexExpression expr) {
		String func = expr.getExpression();
		if (func.equals("")) { 
			func = "z"; 
		}
		srcFragShader = "";
		srcFragShader += fragmentShaderHeader;
		for (String varName : expr.getVariables().keySet()) {
			srcFragShader += "uniform vec2 " + varName + ";\n";
		}
		srcFragShader += fragmentShaderComplexFuns;
		srcFragShader += fragmentShaderHSVtoRGB;
		srcFragShader += complexToHSB;
		srcFragShader += complexToBlackWhite;
		srcFragShader += complexToColor;
		srcFragShader += "void main() {\n";
		srcFragShader += "	vec2 z = vPosition;\n";
		srcFragShader += "	vec2 cfunc = " + func + ";\n";
		if (coloring == ColdSettings.Coloring.standard) {
			srcFragShader += "	gl_FragColor = complexToColor(cfunc);\n";
		} else if (coloring == ColdSettings.Coloring.blackwhite) {
			srcFragShader += "	gl_FragColor = complexToBlackWhite(cfunc);\n";
		} else {
			srcFragShader += "	gl_FragColor = complexToColor(cfunc);\n";
		}
		srcFragShader += "}\n";
	}

	public String getSrcVertexShader() {
		return srcVertexShader;
	}

	public String getSrcFragShader() {
		return srcFragShader;
	}

	public void setColoring(ColdSettings.Coloring c) {
		coloring = c;
	}
}
