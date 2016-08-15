package com.jcs.world;

import com.jcs.Camera;
import com.jcs.Shader;
import com.jcs.TileRenderer;
import org.joml.Matrix4f;

/**
 * Created by Jcs on 14/8/2016.
 */
public class World {
    public int width;
    public int height;

    public int[] tiles;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        tiles = new int[width * height];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = Tile.test.getId();
        }
    }

    public void render(TileRenderer renderer, Shader shader, Matrix4f world, Camera camera) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                renderer.renderTile(tiles[x + y * width], x, -y, shader, world, camera);
            }
        }
    }

    public void setTile(Tile t, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            tiles[x + y * width] = t.getId();
    }
}
