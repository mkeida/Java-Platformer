package com.company.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Entity
{
    // Init method
    public abstract void init(GameContainer container) throws SlickException;

    // Update method
    public abstract void update(GameContainer container, int delta) throws SlickException;

    // Draw method
    public abstract void render(GameContainer container, Graphics graphics) throws SlickException;
}
