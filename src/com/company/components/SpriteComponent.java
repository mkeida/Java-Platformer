package com.company.components;

import com.company.engine.ObjectComponent;
import com.company.engine.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

public class SpriteComponent extends ObjectComponent
{
    // GameObject
    GameObject gameObject;

    // Animation
    private Animation animation = null;

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
        this.image = null;
    }

    // Image
    private Image image = null;

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
        this.animation = null;
    }

    // Constructors
    public SpriteComponent(GameObject gameObject, Animation animation)
    {
        this.gameObject = gameObject;
        this.animation = animation;
    }

    public SpriteComponent(GameObject gameObject, Image image)
    {
        this.gameObject = gameObject;
        this.image = image;
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
        if (animation != null)
        {
            animation.update(delta);
        }
    }

    // Draw method
    @Override
    public void render(GameContainer container, Graphics graphics)
    {
        // Image
        if (image != null)
        {
            image.draw(gameObject.getX(), gameObject.getY());
        }

        // Animation
        if (animation != null)
        {
            animation.draw(gameObject.getX(), gameObject.getY());
        }
    }
}
