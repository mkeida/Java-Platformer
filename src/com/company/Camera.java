package com.company;

import com.company.engine.GameObject;
import com.company.scenes.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class Camera
{
    // Camera X position
    public static int x = 0;

    // Camera Y position
    public static int y = 0;

    // Camera X scale
    public static float sx = 1;

    // Camera Y scale
    public static float sy = 1;

    // View
    public static Rectangle view = new Rectangle(0, 0, 0, 0);

    // Extended view
    public static Rectangle extendedView = new Rectangle(0, 0, 0, 0);

    // GameObject followed by camera
    public static GameObject o = null;

    // Update camera
    public static void update(GameContainer container, int delta)
    {
        // View variables
        view = new Rectangle(Camera.x, Camera.y, container.getWidth(), container.getHeight());
        extendedView = new Rectangle(Camera.x - 70, Camera.y - 70, container.getWidth() + 140, container.getHeight() + 140);
        x = o.getX() - (container.getWidth()  / 2) + (o.getWidth()  / 2);
        y = o.getY() - (container.getHeight() / 2) + (o.getHeight() / 2);

        // Limit positions
        if (x < 0) { x = 0; }
        if (y + view.getHeight() > Scene.sceneHeight) { y = (int)(1500 - view.getHeight()); }
    }

    // Follow game object
    public static void followGameObject(GameObject gameObject)
    {
        o = gameObject;
    }
}
