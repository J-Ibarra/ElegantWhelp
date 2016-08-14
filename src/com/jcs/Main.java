package com.jcs;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL.*;

import static org.lwjgl.opengl.GL11.*;

public class Main {

    public Main() throws Exception {
        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize!");
            System.exit(0);
        }

        long win = glfwCreateWindow(640, 480, "Elagant Whelp", 0, 0);

        glfwShowWindow(win);
        glfwMakeContextCurrent(win);

        createCapabilities();

        float[] vertices = new float[]{
                -0.5f, 0.5f, 0f,
                0.5f, 0.5f, 0f,
                0.5f, -0.5f, 0f,
                -0.5f, -0.5f, 0f,
        };

        float[] tex = new float[]{
                0, 0,
                1, 0,
                1, 1,
                0, 1,
        };

        int[] indices = new int[]{
                0, 1, 2,
                2, 3, 0,
        };

        Model model = new Model(vertices, tex, indices);
        Shader shader = new Shader("shader.vs","shader.fs");
        Texture texture = new Texture("testTexture.png");
        glEnable(GL_TEXTURE_2D);


        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();

            if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(win, true);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //texture.bind();
            shader.bind();
            shader.setUniform("sampler", 0);
            texture.bind(0);
            model.render();
            /*texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex2f(-0.5f, 0.5f);
            glTexCoord2f(0, 1);
            glVertex2f(0.5f, 0.5f);
            glTexCoord2f(1, 1);
            glVertex2f(0.5f, -0.5f);
            glTexCoord2f(1, 0);
            glVertex2f(-0.5f, -0.5f);
            glEnd();*/

            glfwSwapBuffers(win);
        }

        glfwDestroyWindow(win);
        glfwTerminate();
    }

    public static void main(String[] args) throws Exception{
        new Main();
    }
}
