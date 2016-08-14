package com.jcs;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL.*;

import static org.lwjgl.opengl.GL11.*;

public class Main {

    public Main() {
        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize!");
            System.exit(0);
        }

        long win = glfwCreateWindow(640, 480, "Elagant Whelp", 0, 0);

        glfwShowWindow(win);
        glfwMakeContextCurrent(win);

        createCapabilities();

        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();

            if(glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(win, true);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glBegin(GL_QUADS);
            glColor3f(1, 0, 0);
            glVertex2f(-0.5f, 0.5f);
            glColor3f(0, 1, 0);
            glVertex2f(0.5f, 0.5f);
            glColor3f(0, 0, 1);
            glVertex2f(0.5f, -0.5f);
            glColor3f(1, 1, 0);
            glVertex2f(-0.5f, -0.5f);
            glEnd();

            glfwSwapBuffers(win);
        }

        glfwDestroyWindow(win);
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Main();
    }
}
