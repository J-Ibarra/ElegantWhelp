package com.jcs;

import com.jcs.entity.Player;
import com.jcs.world.Tile;
import com.jcs.world.World;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import javax.swing.text.PlainDocument;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    public Main() throws Exception {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);

        long win = glfwCreateWindow(640, 480, "Elagant Whelp", 0, 0);

        if (win == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                win,
                (vidmode.width() - 640) / 2,
                (vidmode.height() - 480) / 2
        );

        glfwMakeContextCurrent(win);
        glfwSwapInterval(1);
        glfwShowWindow(win);

        createCapabilities();

        Camera camera = new Camera(640, 480);
        TileRenderer renderer = new TileRenderer();

        Shader shader = new Shader("shader.vs", "shader.fs");

        World world = new World(24, 24);

        world.setTile(Tile.test2, 0, 0);
        world.setTile(Tile.test2, 23, 0);
        world.setTile(Tile.test2, 0, 23);
        world.setTile(Tile.test2, 23, 23);
        Player player = new Player();
        world.add(player);

        glEnable(GL_TEXTURE_2D);

        int ups = 0;
        int fps = 0;

        glfwSetTime(0);
        double lastTimer = glfwGetTime();

        while (!glfwWindowShouldClose(win)) {


            glfwPollEvents();
            if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(win, true);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            ups++;
            world.update(win);
            fps++;
            world.render(renderer, shader, camera);

            glfwSwapBuffers(win);

            if (glfwGetTime() - lastTimer > 1) {
                oneSecond(ups, fps);
                ups = fps = 0;
                lastTimer += 1;
            }
        }

        glfwDestroyWindow(win);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void oneSecond(int ups, int fps) {
        System.out.println("ups: " + ups + ", fps: " + fps);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
