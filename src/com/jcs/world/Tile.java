package com.jcs.world;

import com.jcs.world.tiles.testTile;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static final Tile test = new testTile(0, "testTexture.png");

    private final int id;
    private final String texture;

    public Tile(int id, String texture) {
        this.id = id;
        this.texture = texture;

        if (tiles[id] != null)
            throw new RuntimeException("Duplicated tile id!");

        tiles[id] = this;
    }

    public int getId() {
        return id;
    }

    public String getTexture() {
        return texture;
    }
}