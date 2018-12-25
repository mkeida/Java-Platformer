package com.company.components;

import com.company.Game;
import com.company.engine.GameObject;
import com.company.engine.ObjectComponent;
import com.company.scenes.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

// Handles movement by user
public class RigidbodyComponent extends ObjectComponent
{
    // Game object
    GameObject gameObject;

    // Object temporary position variables
    double tempY = 0;
    double tempX = 0;

    // Collision
    boolean collisionTop;
    public boolean getCollisionFromTop()
    {
        return collisionTop;
    }

    // Horizontal force
    double hForce = 0;
    public double getHorizontalForce()
    {
        return hForce;
    }

    // Vertical force
    double vForce = 0;
    public double getVerticalForce()
    {
        return vForce;
    }

    // Mass
    double mass = 1;
    public double getMass()
    {
        return mass;
    }
    public void setMass(double mass)
    {
        this.mass = mass;
    }

    // Gravity
    double gravity = 1;
    double gIncrement = 0;
    public double getGravity()
    {
        return gravity;
    }
    public void setGravity(double gravity)
    {
        this.gravity = gravity;
    }

    // Previous position
    double prevX = 0;
    double prevY = 0;

    // Affected by any forces
    boolean solid = true;

    public boolean getSolid()
    {
        return solid;
    }

    public void setSolid(boolean solid)
    {
        this.solid = solid;
    }

    // Constructor
    public RigidbodyComponent(GameObject gameObject)
    {
        this.gameObject = gameObject;
    }

    // Initialize method
    @Override
    public void init(GameContainer container)
    {

    }

    // Update method
    @Override
    public void update(GameContainer container, int delta)
    {
        // Update player position
        tempX = gameObject.getX();
        tempY = gameObject.getY();

        // Reset collision
        collisionTop = false;

        // Iterate through all game objects
        for (GameObject object : Game.gameObjects)
        {
            // Current object bounds
            Rectangle r = new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight());

            if (solid)
            {
                // Check for collision
                if (gameObject.intersects(r))
                {
                    // Eliminate the collision with themselves
                    if (gameObject != object)
                    {
                        // Top
                        if (prevY + gameObject.getHeight() <= object.getY())
                        {
                            // Collision from top happened
                            collisionTop = true;
                            tempY = object.getY() - gameObject.getHeight();
                            gIncrement = 0;

                            // End collision detection
                            break;
                        }

                        // Bottom
                        if (prevY > object.getY() + object.getHeight())
                        {
                            tempY = object.getY() + object.getHeight();
                            vForce = 0;
                            gIncrement = 0;

                            // End collision detection
                            break;
                        }

                        // Left
                        if (prevX <= object.getX())
                        {
                            if (hForce > 0)
                            {
                                hForce = 0;
                            }
                            tempX = object.getX() - gameObject.getWidth();
                        }

                        // Right
                        if (prevX >= object.getX())
                        {
                            if (hForce < 0)
                            {
                                hForce = 0;
                            }
                            tempX = object.getX() + object.getWidth();
                        }
                    }
                }
            }
        }

        // Previous position
        prevX = tempX;
        prevY = tempY;

        // Gravity (vertical force)
        gIncrement +=  mass * gravity;

        // Fade both vertical and horizontal forces
        if (vForce > 0) { vForce -= gravity; }
        if (vForce < 0) { vForce += gravity; }
        if (hForce > 0) { hForce -= Scene.friction; }
        if (hForce < 0) { hForce += Scene.friction; }

        // Vertical and horizontal forces
        tempX += hForce;
        tempY += vForce;
        if (solid) { tempY += gIncrement; }

        // Set new values with increment
        gameObject.setY((int) tempY);
        gameObject.setX((int) tempX);
    }

    // Draw method
    @Override
    public void render(GameContainer container, Graphics graphics)
    {

    }

    // Add vertical force
    public void addVerticalForce(double force)
    {
        int y = gameObject.getY();
        gameObject.setY(y-1);
        vForce = force;
    }

    // Add horizontal force
    public void addHorizontalForce(double force)
    {
        if (hForce < 12 && hForce > -12)
        {
            hForce += force;
        }
    }
}
