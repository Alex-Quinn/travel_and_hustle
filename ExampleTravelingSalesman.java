// Author: Alexander Quinn
// Email: ajq3@pitt.edu
// Date: 11-05-12
// Course: Algorithm Implementation
// Project: Project 3, Traveling Salesman

import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.Date;

public class ExampleTravelingSalesman   
{
    public static int SLEEP = 0;

    public static void main(String[] args)
    {
        final int WIDTH = 1000;
        final int HEIGHT = 700;
        
        int numberOfCities = Integer.parseInt(args[0]);

        // Create random cities
        ArrayList<City> cities = new ArrayList<City>() ;
        Random randomGenerator = new Random();
        for (int i = 0; i < numberOfCities; i++)
        {
            int x = randomGenerator.nextInt(WIDTH);
            int y = randomGenerator.nextInt(HEIGHT);
            City c = new City(x,y, i);
            cities.add(c);
        }

        Setup painter and paint cities
        Painter p = new Painter(WIDTH, HEIGHT, cities);
        JFrame frame = new JFrame("Euclidean Traveling Salesman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(p);
        frame.pack();
        frame.setVisible(true);

        sleep(SLEEP);

        // Compute minimum spanning tree
        TravelingSalesman ts = new TravelingSalesman(cities);
        ArrayList<Edge> mst = ts.computeMST();
        ArrayList<Edge> mstWalk = ts.walkMST();

        System.out.printf("MST weight: %d\n", ts.getWeightMST());
        System.out.printf("Tour length: %d\n", ts.getTourLength());

        // Display minimum spanning tree
        for (Edge e : mst)
        {
            p.drawMstEdge(e);
            p.repaint();
        }

        // Draw walk
        for (Edge e : mstWalk)
        {
            sleep(SLEEP);
            p.drawTourEdge(e);
            p.repaint();
        }

        Remove MST
        p.removeMST();
        p.repaint();
    }

    public static void sleep(long milliseconds)
    {
        Date d;
        long start, now;
        d = new Date();
        start = d.getTime();
        do
        {
            d = new Date();
            now = d.getTime();
        } while ((now - start) < milliseconds);
    }
}
