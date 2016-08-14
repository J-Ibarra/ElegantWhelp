package com.jcs;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Model {
    private int drawCount;
    private int vId;
    private int tId;

    public Model(float[] vertices, float[] texture) {
        drawCount = vertices.length / 3;

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);

        tId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(texture), GL_STATIC_DRAW);

    }

    public void render() {
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, drawCount);


        glBindTexture(GL_ARRAY_BUFFER, 0);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }

    private FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer fb = org.lwjgl.BufferUtils.createFloatBuffer(data.length);
        fb.put(data).flip();
        return fb;
    }
}
