package main;


import world.Tile;
import world.World;

/**
 * Created by CC on 22/07/2016.
 */
public class CollisionCheck{

    public static boolean hasCollided(int x, int y){
        Tile tile = World.getTile(x, -y);
        if (tile.isSolid() == true){
            System.out.println("X: " + x+ "Y: " + y);
            return true;
        }

        return false;
    }

}
