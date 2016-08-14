package com.jcs;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Model {
    private int drawCount;
    private int vId;
    private int tId;
    private int iId;

    public Model(float[] vertices, float[] texture, int[] indices) {
        drawCount = indices.length;

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);

        tId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(texture), GL_STATIC_DRAW);

        iId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindTexture(GL_ARRAY_BUFFER, 0);


    }

    public void render() {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);


        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iId);
        glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindTexture(GL_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
    }

    private FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer fb = org.lwjgl.BufferUtils.createFloatBuffer(data.length);
        fb.put(data).flip();
        return fb;
    }


    private IntBuffer createIntBuffer(int[] data) {
        IntBuffer fb = org.lwjgl.BufferUtils.createIntBuffer(data.length);
        fb.put(data).flip();
        return fb;
    }
}
