package com.jcs;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public Main() {
        glfwInit();

        long win = glfwCreateWindow(640,480,"Elagant Whelp",0,0);

        glfwShowWindow(win);

        while(!glfwWindowShouldClose(win)){
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
