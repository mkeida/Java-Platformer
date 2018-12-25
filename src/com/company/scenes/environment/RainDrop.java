package com.company.scenes.environment;

import com.company.Camera;
import com.company.scenes.Scene;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

public class RainDrop
{
    // Color variants
    Color colors[] = {
            new Color(180, 231, 220),
            new Color(154, 204, 214),
            new Color(123, 152, 169),
            new Color(128, 156, 188),
            new Color(124, 153, 183)
    };

    // Color of raindrop
    Color c = new Color(123, 152, 169);

    // Length of raindrop
    int length = 0;

    // Mass of raindrop
    int mass = 0;

    // Width of raindrop
    int width = 0;

    // Direction change
    int dirChange = 0;

    // Color opacity
    double opacity = 1;

    // Position
    float x = 0;
    float y = 0;

    // Counter
    int counter = 0;

    // Constructor
    public RainDrop(GameContainer container)
    {
        // Random values
        Random r = new Random();
        this.c = colors[r.nextInt(colors.length)];
        this.x = Camera.x + (- Scene.windSpeed * container.getHeight()) + r.nextInt((int)(container.getWidth() + (Scene.windSpeed * container.getHeight())));
        this.mass = 10 + r.nextInt(10);
        this.length = 20 + r.nextInt(30);
        this.width = 1 + r.nextInt(4);
        this.opacity = r.nextDouble();
    }

    // Update environment
    public void update(GameContainer container, int delta)
    {
        // Counter tick
        counter++;

        // Direction change
        if (counter % 100 == 0)
        {
            Random r = new Random();
            dirChange = -1 + r.nextInt(+2);
            counter = 0;
        }

        // Update position
        x += Scene.windSpeed * mass + (dirChange * 2);
        y += Scene.gravity * mass;
    }

    // Render environment
    public void render(GameContainer container, Graphics graphics)
    {
        // Set color
        graphics.setColor(new Color(c.r, c.g, c.b, (float) opacity));

        // Set width
        graphics.setLineWidth(width);

        // Draw raindrop
        graphics.drawGradientLine(x, y, new Color(c.r, c.g, c.b, 0), x +(Scene.windSpeed * length), y + (Scene.gravity * length), new Color(c.r, c.g, c.b, (float) opacity));

        // Reset width
        graphics.resetLineWidth();
    }
}
