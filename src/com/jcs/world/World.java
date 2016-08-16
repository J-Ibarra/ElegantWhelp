package com.jcs.world;

import com.jcs.Camera;
import com.jcs.Shader;
import com.jcs.TileRenderer;
import com.jcs.entity.Player;
import org.joml.Matrix4f;

/**
 * Created by Jcs on 14/8/2016.
 */
public class World {
    public int width;
    public int height;
    public int[] tiles;

    public Matrix4f world;
    public float scale = 32;

    public Player player;

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

    public void update(long win) {
        player.update(win,this);
    }

    public void render(TileRenderer renderer, Shader shader, Camera camera) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //if(x!=0 || y!=0)
                renderer.renderTile(tiles[x + y * width], x, -y, shader, world, camera);
            }
        }

        player.render(shader, camera, this);
    }

    public void add(Player p) {
        player = p;
    }

    public void setTile(Tile t, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            tiles[x + y * width] = t.getId();
    }
}
