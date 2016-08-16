package com.jcs;

import com.jcs.entity.Player;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Camera {
    public Vector3f position;
    public Matrix4f projection;
    public final float left, right, bottom, top;

    public Camera(int width, int height) {
        position = new Vector3f();
        left = -width/2;
        right = width/2;
        bottom = -height/2;
        top = height/2;
        projection = new Matrix4f().setOrtho2D(left, right, bottom, top);
    }

    public void setPosition(Vector3f vec) {
        position = vec;
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }

    public void addPosition(Vector3f vec) {
        position.add(vec);
    }

    public void addPosition(float x, float y, float z) {
        position.add(x, y, z);
    }

    public Vector3f getPosition() {
        return position;
    }

    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f().setTranslation(position);
        projection.mul(target, target);
        return target;
    }
}
