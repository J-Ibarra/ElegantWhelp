package com.jcs;

import org.joml.Matrix4f;

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
        Shader shader = new Shader("shader.vs", "shader.fs");
        Texture texture = new Texture("testTexture.png");

        Matrix4f projection = new Matrix4f().ortho2D(-640 / 2, 640 / 2, -480 / 2, 480 / 2).scale(64);

        glEnable(GL_TEXTURE_2D);


        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();

            if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(win, true);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            shader.bind();
            shader.setUniform("sampler", 0);
            shader.setUniform("projection",projection);
            texture.bind(0);
            model.render();

            glfwSwapBuffers(win);
        }

        glfwDestroyWindow(win);
        glfwTerminate();
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
