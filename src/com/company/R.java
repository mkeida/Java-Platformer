package com.company;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

// Resources class
public class R
{
    // Player resources
    public static Animation walkAnimationL = null;
    public static Animation walkAnimationR = null;
    public static Animation idleAnimationL = null;
    public static Animation idleAnimationR = null;
    public static Image jumpImageL = null;
    public static Image jumpImageR = null;

    // Background resources
    public static Image bgGrassLand = null;

    // Grass block
    public static Image grass = null;
    public static SpriteSheet tGrass = null;

    // Load resources
    public static void loadResources() throws SlickException
    {
        try
        {
            // Player resources
            SpriteSheet ssMoveL = new SpriteSheet("res/player/player_move_l.png", 75, 96);
            SpriteSheet ssMoveR = new SpriteSheet("res/player/player_move_r.png", 75, 96);
            SpriteSheet ssIdleL = new SpriteSheet("res/player/player_idle_l.png", 75, 96);
            SpriteSheet ssIdleR = new SpriteSheet("res/player/player_idle_r.png", 75, 96);
            jumpImageR = new Image("res/player/player_jump_r.png");
            jumpImageL = new Image("res/player/player_jump_l.png");
            walkAnimationL = new Animation(ssMoveL, 75);
            walkAnimationR = new Animation(ssMoveR, 75);
            idleAnimationL = new Animation(ssIdleL, 1000);
            idleAnimationR = new Animation(ssIdleR, 1000);

            // Background resources
            bgGrassLand = new Image("res/backgrounds/bg_grasslands.png");

            // Grass resources
            grass = new Image("res/blocks/block_grass_base.png");
            Image img = new Image("res/blocks/tileset_grass.png");
            tGrass = new SpriteSheet(img, 70, 70, 1, 1);

        }
        catch (Exception e)
        {
            Log.info("Error in loading resources.");
        }
    }
}
