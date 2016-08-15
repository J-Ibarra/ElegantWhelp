package com.jcs;

import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jcs on 14/8/2016.
 */
public class TileRenderer {

    public static Map<String, Texture> tileTextures = new HashMap<String, Texture>();

    static {
        for (int i = 0; i < Tile.tiles.length; i++) {
            if (Tile.tiles[i] != null) {
                if (!tileTextures.containsKey(Tile.tiles[i].getTexture())) {
                    String tex = Tile.tiles[i].getTexture();
                    if (tex != null)
                        tileTextures.put(tex, new Texture(tex));
                }
            }
        }
    }

    private Model model;

    public TileRenderer() {

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
    }

    public void renderTile(int id, int x, int y, Shader shader, Matrix4f world, Camera camera) {
        if (Tile.tiles[id] != null) {
            shader.bind();
            if (Tile.tiles[id].getTexture() != null) {
                tileTextures.get(Tile.tiles[id].getTexture()).bind(0);

                Matrix4f tileMatrix = new Matrix4f().translate(x, y, 0);
                Matrix4f target = new Matrix4f();
                camera.getProjection().mul(world, target);
                target.mul(tileMatrix);
                shader.setUniform("sampler", 0);
                shader.setUniform("projection", target);
                model.render();
            }
        }
    }
}
