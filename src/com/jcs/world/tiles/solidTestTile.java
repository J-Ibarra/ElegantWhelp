package com.jcs.world.tiles;

import com.jcs.world.Tile;

/**
 * Created by Jcs on 15/8/2016.
 */
public class solidTestTile extends Tile {

    public solidTestTile(int id, String texture) {
        super(id, texture);
        setSolid();
    }
}
