package com.jcs.world;

import com.jcs.Camera;
import com.jcs.Shader;
import com.jcs.TileRenderer;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Jcs on 14/8/2016.
 */
public class World {
    public int width;
    public int height;
    public int[] tiles;

    public Matrix4f world;
    public float scale = 32;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        tiles = new int[width * height];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = Tile.test.getId();
        }

        world = new Matrix4f().identity();
        world.scale(scale);
    }

    public void render(TileRenderer renderer, Shader shader, Camera camera) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                renderer.renderTile(tiles[x + y * width], x, y, shader, world, camera);
            }
        }
    }

    public void correctCamera(Camera camera, int w, int h) {
        Vector3f pos = camera.getPosition();
        float ww = -width * scale;
        float hh = -height * scale;

        if (pos.x > scale / 2)
            pos.x = scale / 2;

        if (pos.y > scale / 2)
            pos.y = scale / 2;

        if (pos.x < ww + w + scale / 2)
            pos.x = ww + w + scale / 2;

        if (pos.y < hh + h + scale / 2)
            pos.y = hh + h + scale / 2;

    }

    public void setTile(Tile t, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            tiles[x + y * width] = t.getId();
    }
}
