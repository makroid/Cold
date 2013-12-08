package com.example.cold;


import java.util.HashMap;

import android.opengl.GLES20;
import android.util.Log;


public final class Shader {
	
	private ShaderSourceGenerator shaderGenerator = new ShaderSourceGenerator();
	
	// Shader program handles.
	private int mIdProgram = 0;
	private int mIdShaderFragment = 0;
	private int mIdShaderVertex = 0;
	// HashMap for storing uniform/attribute handles.
	private final HashMap<String, Integer> mShaderHandleMap = new HashMap<String, Integer>();

	public void deleteProgram() {
		GLES20.glDeleteShader(mIdShaderFragment);
		GLES20.glDeleteShader(mIdShaderVertex);
		GLES20.glDeleteProgram(mIdProgram);
		mIdProgram = mIdShaderVertex = mIdShaderFragment = 0;
	}

	/**
	 * Get id for given handle name. This method checks for both attribute and
	 * uniform handles.
	 * 
	 * @param name
	 *            Name of handle.
	 * @return Id for given handle or -1 if none found.
	 */
	public int getHandle(String name) {
		if (mShaderHandleMap.containsKey(name)) {
			return mShaderHandleMap.get(name);
		}
		int handle = GLES20.glGetAttribLocation(mIdProgram, name);
		if (handle == -1) {
			handle = GLES20.glGetUniformLocation(mIdProgram, name);
		}
		if (handle == -1) {
			// This line comes handy if you see repeating 'not found'
			// messages on LogCat - usually for typos otherwise annoying to
			// spot from shader code.
			Log.d("GlslShader", "Could not get attrib location for " + name);
		} else {
			mShaderHandleMap.put(name, handle);
		}
		return handle;
	}

	/**
	 * Get array of ids with given names. Returned array is sized to given
	 * amount name elements.
	 */
	public int[] getHandles(String... names) {
		int[] res = new int[names.length];
		for (int i = 0; i < names.length; ++i) {
			res[i] = getHandle(names[i]);
		}
		return res;
	}

	/**
	 * Helper method for compiling a shader.
	 * 
	 * @param shaderType
	 *            Type of shader to compile
	 * @param source
	 *            String presentation for shader
	 * @return id for compiled shader
	 */
	private int loadShader(int shaderType, String source) throws Exception {
		int shader = GLES20.glCreateShader(shaderType);
		if (shader != 0) {
			GLES20.glShaderSource(shader, source);
			checkGlError("glShaderSource");
			GLES20.glCompileShader(shader);
			checkGlError("glCompileShader");
			int[] compiled = new int[1];
			GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
			if (compiled[0] == 0) {
				String error = GLES20.glGetShaderInfoLog(shader);
				GLES20.glDeleteShader(shader);
				throw new Exception(error);
			}
		}
		return shader;
	}

	/**
	 * Generates and compiles vertex and fragment shaders and links them 
	 * into a program one
	 * can use for rendering.
	 */
	public void setProgram(ComplexExpression cExpr)
			throws Exception {
		shaderGenerator.generate(cExpr);		
	
		mIdShaderVertex = loadShader(GLES20.GL_VERTEX_SHADER, shaderGenerator.getSrcVertexShader());
		mIdShaderFragment = loadShader(GLES20.GL_FRAGMENT_SHADER, shaderGenerator.getSrcFragShader());
		checkGlError("glLoadShader");
		int program = GLES20.glCreateProgram();
		checkGlError("glCreateProgram");
			
		if (program != 0) {
			GLES20.glAttachShader(program, mIdShaderVertex);
			GLES20.glAttachShader(program, mIdShaderFragment);
			checkGlError("glAttachShader");
			GLES20.glLinkProgram(program);
			checkGlError("glLinkProgram");
			int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
			checkGlError("glGetProgramiv");
			if (linkStatus[0] != GLES20.GL_TRUE) {
				System.out.println("Error in shader link");
				String error = GLES20.glGetProgramInfoLog(program);
				deleteProgram();
				throw new Exception(error);
			}
		}			
		mIdProgram = program;		
		mShaderHandleMap.clear();
	}

	public void useProgram() {				
		GLES20.glUseProgram(mIdProgram);
		checkGlError("glUseProgram");
	}
	
	private void checkGlError(String op) {
		int error;
		while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
			Log.e("ColdShader", op + ": glError " + error);
			throw new RuntimeException(op + ": glError " + error);
		}
	}
	
	public ShaderSourceGenerator getShaderSourceGenerator() {
		return shaderGenerator;
	}
}

