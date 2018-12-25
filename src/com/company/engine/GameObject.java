package com.company.engine;

import com.company.Camera;
import com.company.Game;
import com.company.scenes.Scene;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import java.util.ArrayList;

public class GameObject extends Entity
{
    // X position
    private int x = 0;
    public int getX() { return  x; }
    public void setX (int x) { this.x = x; }

    // Y position
    private int y = 0;
    public int getY() { return  y; }
    public void setY (int y) { this.y = y; }

    // Width
    private int width = 0;
    public int getWidth() { return  width; }
    public void setWidth(int width) { this.width = width; }

    // Height
    private int height = 0;
    public int getHeight() { return  height; }
    public void setHeight(int height) { this.height = height; }

    // Object component list
    ArrayList<ObjectComponent> objectComponents = new ArrayList<>();
    public ArrayList getObjectComponents() { return objectComponents; }
    public void setObjectComponents(ArrayList objectComponents) { this.objectComponents = objectComponents; }

    // Constructor
    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Initialize
    @Override
    public void init(GameContainer container)
    {
        // Iterate through all components of game object and utilize init methods
        for(ObjectComponent component : objectComponents)
        {
            component.init(container);
        }
    }

    // Update
    @Override
    public void update(GameContainer container, int delta)
    {
        // Iterate through all components of game object and utilize update methods
        for(ObjectComponent component : objectComponents)
        {
            component.update(container, delta);
        }
    }

    // Draw
    @Override
    public void render(GameContainer container, Graphics graphics)
    {
        // Render game object developer info (background)
        if (Game.devTools)
        {
            // Object bounds
            graphics.setColor(new Color(255, 0, 0, 50));
            graphics.fillRect(x, y, width, height);
        }

        // Iterate through all components of game object and utilize render methods
        for (ObjectComponent component : objectComponents)
        {
            component.render(container, graphics);
        }

        // Render game object developer info (foreground)
        if (Game.devTools)
        {
            // Object border
            graphics.setLineWidth(1);
            graphics.setColor(new Color(255, 0, 0));
            graphics.drawRect(x, y, width - 1, height - 1);

            // Object position
            graphics.drawString(String.valueOf(x), x + 2, y + 2);
            graphics.drawString(String.valueOf(y), x + 2, y + 12);
        }
    }

    // Intersection
    public boolean intersects(Rectangle rectangle)
    {
        // GameObject rectangle
        Rectangle eRect = new Rectangle(x, y, width, height);

        // Check for collision
        if (eRect.intersects(rectangle))
        {
            return true;
        }

        return false;
    }

    // Add game object
    public void add()
    {
        Game.gameObjects.add(this);
    }

    // Remove game object
    public void remove()
    {
        Game.gameObjects.remove(this);
    }

    // Add object component
    public void addObjectComponent(ObjectComponent component)
    {
        objectComponents.add(component);
    }
}
