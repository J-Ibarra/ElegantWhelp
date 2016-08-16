package com.jcs.entity;

import com.jcs.Camera;
import com.jcs.Model;
import com.jcs.Shader;
import com.jcs.Texture;
import com.jcs.world.World;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;

/**
 * Created by Jcs on 15/8/2016.
 */
public class Player {

    public Model model;
    public Texture texture;
    public Vector3f pos;
    public Vector3f scale;

    public Player() {
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

        model = new Model(vertices, tex, indices);
        texture = new Texture("Player.png");

        pos = new Vector3f();
        scale = new Vector3f(32, 32, 1);
    }

    public void update(long win, World world) {

        float ma = 0.3f;
        if (glfwGetKey(win, GLFW_KEY_LEFT_SHIFT) == GLFW_TRUE)
            ma = 0.6f;

        if (glfwGetKey(win, GLFW_KEY_W) == GLFW_TRUE)
            pos.add(0f, 0.1f * ma, 0f);
        if (glfwGetKey(win, GLFW_KEY_S) == GLFW_TRUE)
            pos.add(0f, -0.1f * ma, 0f);
        if (glfwGetKey(win, GLFW_KEY_A) == GLFW_TRUE)
            pos.add(-0.1f * ma, 0f, 0f);
        if (glfwGetKey(win, GLFW_KEY_D) == GLFW_TRUE)
            pos.add(0.1f * ma, 0f, 0f);


    }

    public Matrix4f getProjection(Matrix4f target) {
        target.scale(scale);
        target.translate(pos);
        return target;
    }

    public void render(Shader shader, Camera camera, World world) {
        setLook(camera, world);
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", getProjection(camera.getProjection()));
        texture.bind(0);
        model.render();
    }

    private void setLook(Camera camera, World world) {
        camera.position.x = -pos.x * world.scale;
        camera.position.y = -pos.y * world.scale;

        if (camera.left < camera.position.x - world.scale / 2) {
            camera.position.x = camera.left + world.scale / 2;
        }

        if (camera.right > camera.position.x + world.width * world.scale - world.scale / 2) {
            camera.position.x = camera.right - world.width * world.scale + world.scale / 2;
        }

        if (camera.top > camera.position.y + world.scale / 2) {
            camera.position.y = camera.top - world.scale / 2;
        }

        if (camera.bottom < camera.position.y - world.height * world.scale + world.scale / 2) {
            camera.position.y = camera.bottom + world.height * world.scale - world.scale / 2;
        }

    }
}
