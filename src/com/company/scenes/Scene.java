package com.company.scenes;

import com.company.R;
import com.company.objects.Grass;
import com.company.scenes.environment.Environment;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

// Handles level design
public class Scene
{
    // Gravity
    public static int gravity = 1;

    // Wind speed
    public static float windSpeed = 0.2f;

    // Friction
    public static float friction = 0.5f;

    // Scene height
    public static int sceneHeight = 1500;

    // World biomes
    public enum Biomes
    {
        Base(1),
        Snow(2);

        private final int value;
        Biomes(int value)
        {
            this.value = value;
        }
        public int getValue()
        {
            return this.value;
        }
    }

    // Load scene
    public static void instantiate()
    {
        // Environment
        Environment.instantiate();

        // Render first zone
        generateZone(0, Biomes.Base);
    }

    // Update scene
    public static void update(GameContainer container, int delta)
    {
        // Environment
        Environment.update(container, delta);
    }

    // Render scene
    public static void render(GameContainer container, Graphics graphics)
    {
        // Background color
        graphics.setBackground(new Color(214, 243, 248));

        // Environment
        Environment.render(container, graphics);
    }

    // Render scene background
    public static void renderBackground(GameContainer container, Graphics graphics)
    {
        // Render background image
        for (int i = 0; i < 100; i++)
        {
            graphics.drawImage(R.bgGrassLand, i * R.bgGrassLand.getWidth(), Scene.sceneHeight - R.bgGrassLand.getHeight());
        }
    }

    // Render zone
    // Each zone is 20 block wide and 50 blocks long
    public static void generateZone(int start, Biomes biom)
    {
        for (int x = 0; x < 25; x++)
        {
            Grass b = new Grass(1);
            b.setX(x * 70);
            b.setY(1500 - 70);
            b.add();
        }
    }
}
