package com.jcs;



import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;
/**
 * Created by Jcs on 14/8/2016.
 */
public class Shader {

    private int program;
    private int vs;
    private int fs;

    public Shader(String vShader, String fShader) throws Exception{
        program = glCreateProgram();

        vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs, IOUtil.ioReadFileAsString(vShader));
        glCompileShader(vs);
        if(glGetShaderi(vs,GL_COMPILE_STATUS) == GL_FALSE) {
            new RuntimeException("vShader no compiled! " + glGetShaderInfoLog(vs));
        }


        fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs, IOUtil.ioReadFileAsString(fShader));
        glCompileShader(fs);
        if(glGetShaderi(fs,GL_COMPILE_STATUS) == GL_FALSE) {
            new RuntimeException("fShader no compiled! " + glGetShaderInfoLog(vs));
        }

        glAttachShader(program, vs);
        glAttachShader(program, fs);

        glBindAttribLocation(program, 0, "vertices");
        glLinkProgram(program);
        if(glGetProgrami(program,GL_LINK_STATUS) == GL_FALSE) {
            new RuntimeException("Program no linked! " + glGetProgramInfoLog(program));
        }

        glValidateProgram(program);
        if (glGetProgrami(program,GL_VALIDATE_STATUS) == GL_FALSE) {
            new RuntimeException("Program no validated! " + glGetProgramInfoLog(program));
        }
    }

    public void bind() {
        glUseProgram(program);
    }




}
