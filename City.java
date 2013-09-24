// Author: Alexander Quinn
// Email: ajq3@pitt.edu
// Date: 11-05-12
// Course: Algorithm Implementation
// Project: Project 3, Traveling Salesman

import java.lang.Math;
import java.util.ArrayList;

public class City
{
    private int x;
    private int y;
    private int name;
    private ArrayList<City> children = new ArrayList<City>();
    private boolean visited = false;
    private Edge shortestEdge;

    public City(int x, int y, int name)
    {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int distance(City start)
    {
        // Computing distance from start to this city and rounding to an integer
        double distance = Math.sqrt(Math.pow(this.x - start.getX(),2) + Math.pow(this.y - start.getY(),2));
        return (int)Math.round(distance);
    }

    public void addChild(City child)
    {
        this.children.add(child);
    }

    public void setShortestEdge(City c)
    {
        this.shortestEdge = new Edge(this, c);
    }

    public void setShortestEdge(City c, int len)
    {
        this.shortestEdge = new Edge(this, c, len);
    }

    public void setVisited(boolean v)
    {
        this.visited = v;
    }

    public ArrayList<City> getChildren()
    {
        return this.children;
    }

    public int getPriority()
    {
        return this.shortestEdge.getLength();
    }

    public Edge getShortestEdge()
    {
        return this.shortestEdge;
    }

    public boolean visited()
    {
        return this.visited;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public String getName()
    {
        return String.format("%d", this.name);
    }
}