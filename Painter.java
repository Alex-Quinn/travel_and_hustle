// Author: Alexander Quinn
// Email: ajq3@pitt.edu
// Date: 11-05-12
// Course: Algorithm Implementation
// Project: Project 3, Traveling Salesman

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Painter extends JPanel
{
    private final Color BG_COLOR = Color.orange;
    private final Color CITY_COLOR = Color.black;
    private final Color MST_COLOR = Color.blue;
    private final Color MST_WALK_COLOR = Color.red;
    private final int CITY_DIAMETER = 5;
    private final int EDGE_DRAW_OFFSET = 2;

    private int width;
    private int height;
    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<Edge> mstEdges = new ArrayList<Edge>();
    private ArrayList<Edge> mstWalkEdges = new ArrayList<Edge>();
    
    public Painter(int w, int h, ArrayList<City> cities)
    {
        this.width = w;
        this.height = h;
        setPreferredSize(new Dimension(this.width, this.height));

        // Copy cities to painter
        for (City c : cities)
        {
            this.cities.add(c);
        }
    }

    public void drawMstEdge(Edge e)
    {
        this.mstEdges.add(e);
    }

    public void drawTourEdge(Edge e)
    {
        this.mstWalkEdges.add(e);
    }

    public void removeMST()
    {
        this.mstEdges = new ArrayList<Edge>();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(0,0,width,height);

        // Draw cities
        g.setColor(CITY_COLOR);
        for (City c : this.cities)
        {
            int x = c.getX();
            int y = c.getY();
            g.fillOval(x, y, CITY_DIAMETER, CITY_DIAMETER);
            g.drawString(c.getName(), x, y);
        }

        // Draw MST edges
        g.setColor(MST_COLOR);
        for (Edge e : this.mstEdges)
        {
            g.drawLine(e.getStart().getX()+EDGE_DRAW_OFFSET,
                       e.getStart().getY()+EDGE_DRAW_OFFSET,
                       e.getEnd().getX()+EDGE_DRAW_OFFSET, 
                       e.getEnd().getY()+EDGE_DRAW_OFFSET);
        }

        // Draw MST Walk edges
        g.setColor(MST_WALK_COLOR);
        for (Edge e : this.mstWalkEdges)
        {
            g.drawLine(e.getStart().getX()+EDGE_DRAW_OFFSET,
                       e.getStart().getY()+EDGE_DRAW_OFFSET,
                       e.getEnd().getX()+EDGE_DRAW_OFFSET, 
                       e.getEnd().getY()+EDGE_DRAW_OFFSET);
        }
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }
}
