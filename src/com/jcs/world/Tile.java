package com.jcs.world;

import com.jcs.world.tiles.solidTestTile;
import com.jcs.world.tiles.testTile;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static final Tile test = new testTile(255, "testTexture.png");
    public static final Tile test2 = new testTile(254, "testTexture2.png");
    public static final Tile testSolid = new solidTestTile(253, "solidTexture.png");


    private final int id;
    private final String texture;

    private boolean isSolid = false;

    public Tile(int id, String texture) {
        this.id = id;
        this.texture = texture;

        if (tiles[id] != null)
            throw new RuntimeException("Duplicated tile id!");

        tiles[id] = this;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public Tile setSolid() {
        isSolid = true;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getTexture() {
        return texture;
    }
}
