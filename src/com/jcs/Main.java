package com.jcs;

import com.jcs.world.Tile;
import com.jcs.world.World;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL.*;

import static org.lwjgl.opengl.GL11.*;

public class Main {

    public Main() throws Exception {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize!");
            System.exit(0);
        }

        long win = glfwCreateWindow(640, 480, "Elagant Whelp", 0, 0);

        glfwShowWindow(win);
        glfwMakeContextCurrent(win);

        createCapabilities();

        Camera camera = new Camera(640, 480);
        TileRenderer renderer = new TileRenderer();

        Shader shader = new Shader("shader.vs", "shader.fs");

        World world = new World(64, 64);

        world.setTile(Tile.test2, 0, 0);
        world.setTile(Tile.test2, 63, 0);
        world.setTile(Tile.test2, 0, 63);
        world.setTile(Tile.test2, 63, 63);

        glEnable(GL_TEXTURE_2D);

        float scale = 1f;

        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();

            float ma = 1f;

            if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(win, true);
            }

            if (glfwGetKey(win, GLFW_KEY_LEFT_SHIFT) == GLFW_TRUE)
                ma = 3f;

            if (glfwGetKey(win, GLFW_KEY_Q) == GLFW_TRUE)
                scale += (0.1f * ma);
            if (glfwGetKey(win, GLFW_KEY_E) == GLFW_TRUE)
                scale -= (0.1f * ma);

            if (glfwGetKey(win, GLFW_KEY_W) == GLFW_TRUE)
                camera.addPosition(0f, 0.1f * ma, 0f);
            if (glfwGetKey(win, GLFW_KEY_S) == GLFW_TRUE)
                camera.addPosition(0f, -0.1f * ma, 0f);
            if (glfwGetKey(win, GLFW_KEY_A) == GLFW_TRUE)
                camera.addPosition(-0.1f * ma, 0f, 0f);
            if (glfwGetKey(win, GLFW_KEY_D) == GLFW_TRUE)
                camera.addPosition(0.1f * ma, 0f, 0f);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            world.render(renderer, shader, new Matrix4f().scale(scale), camera);

            glfwSwapBuffers(win);
        }

        glfwDestroyWindow(win);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
