// Author: Alexander Quinn
// Email: ajq3@pitt.edu
// Date: 11-05-12
// Course: Algorithm Implementation
// Project: Project 3, Traveling Salesman

public class Edge
{
    private City start;
    private City end;
    private int length;

    public Edge(City start, City end, int len)
    {
        this.start = start;
        this.end = end;
        this.length = len;
    }

    public Edge(City start, City end)
    {
        this.start = start;
        this.end = end;
        this.length = end.distance(start);
    }

    public City getStart()
    {
        return this.start;
    }

    public City getEnd()
    {
        return this.end;
    }

    public int getLength()
    {
        return this.length;
    }
}