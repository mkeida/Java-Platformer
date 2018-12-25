package com.company;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

// Main class
public class Main
{
    // Game container
    public static AppGameContainer container;

    // Main method
    public static void main(String[] arguments) throws SlickException
    {
        // Initialize game and set up game window
        container = new AppGameContainer(new Game());
        container.setDisplayMode(1280, 720, false);
        container.setMaximumLogicUpdateInterval(60);
        container.setTargetFrameRate(60);
        container.setAlwaysRender(true);
        container.setShowFPS(true);
        container.start();
    }
}