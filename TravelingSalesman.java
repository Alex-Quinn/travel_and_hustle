// Author: Alexander Quinn
// Email: ajq3@pitt.edu
// Date: 11-05-12
// Course: Algorithm Implementation
// Project: Project 3, Traveling Salesman

import java.util.ArrayList;

public class TravelingSalesman
{
    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<Edge> minSpanningTree = new ArrayList<Edge>();
    private int mstWeight = 0;
    private int mstTourLength = 0;

    public TravelingSalesman(ArrayList<City> c)
    {
        for (City city : c)
        {
            this.cities.add(city);
        }
    }

    public ArrayList<Edge> computeMST()
    {
        // A simple version of Prim's algorithm to find
        // Euclidean minimum spanning tree

        // Set start vertex
        City start = cities.get(0);
        start.setShortestEdge(start);

        // Need an array to keep track of which cities haven't been visited
        ArrayList<City> remainingCities = new ArrayList<City>();

        // Set priorities based on start vertex
        for (City c : cities)
        {
            int startPriority = c.distance(start);
            c.setShortestEdge(start, startPriority);
            remainingCities.add(c);
        }

        City current = start;
        remainingCities.remove(start);

        while (remainingCities.size() > 0)
        {
            City nextStep = findLowestPriority(remainingCities);
            minSpanningTree.add(nextStep.getShortestEdge());
            
            // Make sure we're adding children based on the root of the nextMove element
            // not the current node.
            City startOfNextMove = nextStep.getShortestEdge().getEnd();
            startOfNextMove.addChild(nextStep);
            
            mstWeight += nextStep.getPriority();
            
            // Move spanning tree one space
            current = nextStep;
            // Remove next step from remaining cities
            remainingCities.remove(nextStep);

            // Update all priorities based on the last move made
            for (City s : remainingCities)
            {
                // Calculate a new priority based on the new move
                int potentialPriority = s.distance(nextStep);
                // If the new priority is better than the last then change priority to lower
                if (potentialPriority < s.getPriority())
                {
                    s.setShortestEdge(nextStep, potentialPriority);
                }
            }
        }

        return minSpanningTree;
    }

    public ArrayList<Edge> walkMST()
    {
        ArrayList<City> citiesFound = new ArrayList<City>();
        
        // Add first city
        citiesFound.add(cities.get(0));

        // Perform depth first traversal with all cities found stored in order
        depthFirstTraversal(citiesFound, cities.get(0));

        // Remove all repeated cities
        removeDuplicates(citiesFound);

        // Make tour from remaining cities
        return makeTour(citiesFound);
    }

    private void depthFirstTraversal(ArrayList<City> citiesFound, City root)
    {
        root.setVisited(true);
        for (City adjacent : root.getChildren())
        {
            if (!adjacent.visited())
            {
                citiesFound.add(adjacent);
                depthFirstTraversal(citiesFound, adjacent);
            }
        }
    }

    private void removeDuplicates(ArrayList<City> orig)
    {
        ArrayList<City> uniqueCities = new ArrayList<City>();

        for (int i = 0; i < orig.size(); i++)
        {
            City current = orig.get(i);
            if (uniqueCities.contains(current))
            {
                orig.remove(i);
            }
            else
            {
                uniqueCities.add(current);
            }
        }
    }

    private ArrayList<Edge> makeTour(ArrayList<City> cityPath)
    {
        ArrayList<Edge> tour = new ArrayList<Edge>();
        for (int i = 1; i < cityPath.size(); i++)
        {
            City first = cityPath.get(i-1);
            City second = cityPath.get(i);
            tour.add(new Edge(first, second));
            mstTourLength += first.distance(second);
        }
        return tour;
    }

    public int getWeightMST()
    {
        return this.mstWeight;
    }

    public int getTourLength()
    {
        return this.mstTourLength;
    }

    public ArrayList<City> getCities()
    {
        return this.cities;
    }

    private City findLowestPriority(ArrayList<City> remainingCities)
    {
        City lowestPriority = remainingCities.get(0);

        for (City nextCity : remainingCities)
        {
            if (nextCity.getPriority() < lowestPriority.getPriority())
            {
                lowestPriority = nextCity;
            }
        }

        return cities.get(cities.indexOf(lowestPriority));
    }
}