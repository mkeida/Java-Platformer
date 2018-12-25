package com.company.objects;

import com.company.R;
import com.company.components.RigidbodyComponent;
import com.company.components.SpriteComponent;
import com.company.engine.GameObject;
import org.newdawn.slick.*;

// Grass class
public class Grass extends GameObject
{
    // Game object components
    SpriteComponent spriteComponent;
    RigidbodyComponent rigidbodyComponent;

    // Grass type
    int type = 0;

    // Constructor
    public Grass(int type)
    {
        super(0, 0, 70, 70);

        // Type of block
        this.type = type;
    }

    // Initialize
    @Override
    public void init(GameContainer container)
    {
        super.init(container);

        // Set game object components
        // Sprite
        spriteComponent = new SpriteComponent(this, R.tGrass.getSubImage(type, 0));
        this.addObjectComponent(spriteComponent);

        // Rigid body
        rigidbodyComponent = new RigidbodyComponent(this);
        rigidbodyComponent.setSolid(false);
        this.addObjectComponent(rigidbodyComponent);
    }

    // Update
    @Override
    public void update(GameContainer container, int delta)
    {
        super.update(container, delta);
    }

    // Draw
    @Override
    public void render(GameContainer container, Graphics graphics)
    {
        super.render(container, graphics);
    }
}
