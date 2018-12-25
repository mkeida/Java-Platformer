package com.company;

import com.company.engine.GameObject;
import com.company.objects.Player;
import com.company.scenes.Scene;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import java.util.ArrayList;

public class Game extends BasicGame
{
    // Dev tools
    public  static  boolean devTools = false;

    // Input
    Input input;

    // Holds all game objects
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    // Player
    Player player;

    // Constructor
    public Game()
    {
        // Game title
        super("Game");
    }

    // Initialize method
    @Override
    public void init(GameContainer container) throws SlickException
    {
        // Create player
        player = new Player();
        player.setX(640);
        player.setY(Scene.sceneHeight - 300);
        player.add();

        // Camera
        Camera.followGameObject(player);

        // Gets input
        input = container.getInput();

        // Load resources
        R.loadResources();

        // Instantiate all prepared game objects from scene
        Scene.instantiate();

        // Initialize all game objects
        for (GameObject object : gameObjects)
        {
            object.init(container);
        }
    }

    // Update method
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        // Toggle dev tools
        if (input.isKeyPressed(input.KEY_P))
        {
            if (devTools) { devTools = !devTools; } else { devTools = true; }
        }

        // Camera
        Camera.update(container, delta);

        // Scene
        Scene.update(container, delta);

        // Update all visible game objects
        for (GameObject object : gameObjects)
        {
            // If game object is not in view
            if (!Camera.view.contains(object.getX(), object.getY()))
            {
                continue;
            }

            // Update game object
            object.update(container, delta);
        }
    }

    // Draw method
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException
    {
        // Camera
        graphics.translate(-Camera.x, -Camera.y);
        graphics.scale(Camera.sx, Camera.sy);

        // Render scene background
        Scene.renderBackground(container, graphics);

        // Render all game objects
        for (GameObject object : gameObjects)
        {
            // Check if game object is not in extended view.
            if (!Camera.extendedView.intersects(new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight())))
            {
                continue;
            }

            object.render(container, graphics);
        }

        // Render scene
        Scene.render(container, graphics);
    }
}