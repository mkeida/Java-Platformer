package com.company.objects;

import com.company.components.RigidbodyComponent;
import com.company.engine.GameObject;
import com.company.components.SpriteComponent;
import com.company.R;
import org.newdawn.slick.*;

// Player class
public class Player extends GameObject
{
    // Game object components
    SpriteComponent spriteComponent;
    RigidbodyComponent rigidbodyComponent;

    // Input
    Input input;

    // Directions
    boolean left = false;
    boolean right = false;
    boolean jump = false;

    // Constructor
    public Player()
    {
        super(100, 100, 75, 96);
    }

    // Initialize
    @Override
    public void init(GameContainer container)
    {
        super.init(container);

        // Input
        input = container.getInput();

        // Sprite
        spriteComponent = new SpriteComponent(this, R.walkAnimationL);
        this.addObjectComponent(spriteComponent);

        // Rigid body
        rigidbodyComponent = new RigidbodyComponent(this);
        this.addObjectComponent(rigidbodyComponent);
    }

    // Update
    @Override
    public void update(GameContainer container, int delta)
    {
        super.update(container, delta);

        // Input
        input();

        // Check which direction is player moving
        if (rigidbodyComponent.getHorizontalForce() < 0)
        {
            left = true;
            right = false;
        }

        if (rigidbodyComponent.getHorizontalForce() > 0)
        {
            left = false;
            right = true;
        }

        // Check if player is in air
        if (rigidbodyComponent.getCollisionFromTop())
        {
            // Sprite
            // Player moving left
            if (left) { spriteComponent.setAnimation(R.walkAnimationL); }

            // Player moving right
            if (right)  { spriteComponent.setAnimation(R.walkAnimationR); }

            // Player not moving
            if (rigidbodyComponent.getHorizontalForce() == 0)
            {
                if (left) { spriteComponent.setAnimation(R.idleAnimationL); }
                if (right) { spriteComponent.setAnimation(R.idleAnimationR); }
            }
        }
        else
        {
            // Jump left
            if (left) { spriteComponent.setImage(R.jumpImageL); }

            // Jump right
            if (right) { spriteComponent.setImage(R.jumpImageR); }
        }
    }

    // Draw
    @Override
    public void render(GameContainer container, Graphics graphics)
    {
        super.render(container, graphics);
    }

    // Input
    private void input()
    {
        // Space
        if (input.isKeyDown(input.KEY_SPACE))
        {
            // Check if player is on ground
            if (rigidbodyComponent.getCollisionFromTop())
            {
                // Move with player up
                rigidbodyComponent.addVerticalForce(-30);
            }
        }

        // Left
        if (input.isKeyDown(input.KEY_A))
        {
            // Move with player to left
            rigidbodyComponent.addHorizontalForce(-1);
        }

        // Right
        if (input.isKeyDown(input.KEY_D))
        {
            // Move with player to right
            rigidbodyComponent.addHorizontalForce(+1);
        }
    }
}
