package com.company.scenes.environment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import java.util.ArrayList;

public class Environment
{
    // Weather
    // Rain
    static boolean rain = false;

    // List of rain drops
    public static ArrayList<RainDrop> rainDrops = new ArrayList<>();

    // Simple counter
    static int counter = 0;

    // Load environment
    public static void instantiate()
    {

    }

    // Update environment
    public static void update(GameContainer container, int delta)
    {
        counter++;

        // Generate rain drops
        if (rain && (counter % 1) == 0)
        {
            // Creates rain particle
            RainDrop rd = new RainDrop(container);
            rainDrops.add(rd);

            counter = 0;
        }

        // Update all rain drops
        for (RainDrop rd : rainDrops)
        {
            rd.update(container, delta);
        }
    }

    // Render environment
    public static void render(GameContainer container, Graphics graphics)
    {
        // Render all rain drops
        for (RainDrop rd : rainDrops)
        {
            rd.render(container, graphics);
        }
    }
}
