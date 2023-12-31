/*
 * Name: <your name>
 * EID: <your EID>
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Program2 {
    private ArrayList<GasStation> stations;  // this is a list of all Cities, populated by Driver class
    private Heap minHeap;

    // additional constructor fields may be added, but don't delete or modify anything already here
    public Program2() {
        minHeap = new Heap();
        stations = new ArrayList<GasStation>();
    }

    /**
     * findAllReachableStations(GasStation start, int init_size)
     *
     * @param start     - the starting GasStation.
     * @param init_size - the initial tank size obtained
     * @return the list of all gas stations we can reach from start
     */
    public ArrayList<GasStation> findAllReachableStations(GasStation start,int init_size) {
        // TODO: implement this method
        ArrayList<GasStation> station_input = new ArrayList<GasStation>();
    	ArrayList<GasStation> station_visited = new ArrayList<GasStation>();
    	for (int i = 0 ; i<stations.size(); i ++)
    	{
    		if(stations.get(i).getID() == start.getID())
    			stations.get(i).distance = 0;
    		else
    			stations.get(i).distance = Double.MAX_VALUE;
    		station_input.add(stations.get(i));
    	}
    	minHeap.buildHeap(station_input);
    	while(minHeap.size()!=0)
    	{
    		GasStation nearest = minHeap.extractMin();
    		if(init_size < nearest.distance)
    			break;
    		else
    		{
    		station_visited.add(nearest);
    		init_size = init_size + nearest.getUpgradeValue();
    		}
    		ArrayList<GasStation> heap_returned =  minHeap.returnHeap();
    		for(int i = 0; i<heap_returned.size(); i++)
    		{
    			double x = heap_returned.get(i).getXcoordinate();
    			double y = heap_returned.get(i).getYcoordinate();
    			double start_x = nearest.getXcoordinate();
    			double start_y = nearest.getYcoordinate();
    			double distance = ((start_y - y)*(start_y - y)) + ((start_x-x)*(start_x-x));
    			distance = Math.sqrt(distance);
    			if(heap_returned.get(i).distance > distance)
    			minHeap.changeKey(heap_returned.get(i), distance);
    		}
    	}
    	return station_visited;
    }
   /* public ArrayList<GasStation> helper(ArrayList<GasStation> station_visited, ArrayList<GasStation> station_input, GasStation start, int init_size)
    {
    	station_input.clear();
    	for(int i = 0 ; i<stations.size(); i++)
    	{
    		if(stations.get(i).getID() == start.getID())
    			stations.get(i).visited = true;
    	}
    	for(int i = 0; i<stations.size(); i++)
    	{
    		if(stations.get(i).visited == false)
    		{
    			double x = stations.get(i).getXcoordinate();
    			double y = stations.get(i).getYcoordinate();
    			double start_x = start.getXcoordinate();
    			double start_y = start.getYcoordinate();
    			double distance = ((start_y - y)*(start_y - y)) + ((start_x-x)*(start_x-x));
    			distance = Math.sqrt(distance);
    			stations.get(i).distance = distance;
    			station_input.add(stations.get(i));
    		}
    	}
    	minHeap.buildHeap(station_input);
    	while(init_size > 0)
    	{
    	GasStation a = minHeap.extractMin();
    	if(a.distance <= init_size + a.getUpgradeValue() )
    	{
    		station_visited.add(a);
    		station_visited = helper(station_visited, station_input, a, init_size + a.getUpgradeValue()- (int)a.distance);
    	}
    	}
    	return station_visited;
    		
    }*/

    /**
     * findMinimumTankSize()
     * @param start  - the starting GasStation
     * @param dest   - the destination Gas Station
     * @return The minimum gas tank size needed at the beginning of the trip
     */
    public int findMinimumTankSize(GasStation start, GasStation dest) {
        // TODO: implement this method
     int tank_size = 0;
     double tank_diff = 0;
     int distance1 = 0; // total distance travelled by the car
     if(start.getID() == dest.getID())
    	 return tank_size;
     ArrayList<GasStation> station_input = new ArrayList<GasStation>();
     for (int i = 0 ; i<stations.size(); i ++)
 	{
 		if(stations.get(i).getID() == start.getID())
 			stations.get(i).distance = 0;
 		else
 			stations.get(i).distance = Double.MAX_VALUE;
 		station_input.add(stations.get(i));
 	}
     minHeap.buildHeap(station_input);
     while(minHeap.size()!=0)
 	{
 		GasStation nearest = minHeap.extractMin();
 		if(tank_size >= nearest.distance) {
 		if(nearest.getID() == dest.getID())
 		{
 			//distance1 = distance1 + (int)nearest.distance;
 			break;
 		}
 			
 		else
 		{
 		tank_size = tank_size + nearest.getUpgradeValue();
 		//distance1 = distance1 + (int)nearest.distance;
 		}
 		}
 		else
 		{
 			if((tank_diff+tank_size) < nearest.distance)
 			{
 				double tank_diff1 = nearest.distance - (tank_size) - tank_diff;
 			tank_diff = tank_diff + tank_diff1;
 			}
 			if(nearest.getID() == dest.getID())
 	 		{
 	 			//distance1 = distance1 + (int)nearest.distance;
 	 			break;
 	 		}
 	 			
 	 		else
 	 		{
 	 		tank_size = tank_size + nearest.getUpgradeValue();
 	 		//distance1 = distance1 + (int)nearest.distance;
 	 		}
 		}
 		ArrayList<GasStation> heap_returned =  minHeap.returnHeap();
 		for(int i = 0; i<heap_returned.size(); i++)
 		{
 			double x = heap_returned.get(i).getXcoordinate();
 			double y = heap_returned.get(i).getYcoordinate();
 			double start_x = nearest.getXcoordinate();
 			double start_y = nearest.getYcoordinate();
 			double distance = ((start_y - y)*(start_y - y)) + ((start_x-x)*(start_x-x));
 			distance = Math.sqrt(distance);
 			if(heap_returned.get(i).distance > distance)
 			minHeap.changeKey(heap_returned.get(i), distance);
 		}
 	}
       return (int) Math.ceil(tank_diff);
    }

    //return the gas station id and its upgrade value
    //this function can be altered for your debugging purpose
    public String toString() {
        String o = "";
        for (GasStation v : stations) {
            boolean first = true;
            o += "Gas Station ";
            o += v.getID();
            o += " has upgrade value ";
            o += v.getUpgradeValue();
            o += System.getProperty("line.separator");

        }

        return o;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<GasStation> getAllStations() {
        return stations;
    }


    // used by Driver.java and sets cities to reference an ArrayList of all RestStops
    public void setAllNodesArray(ArrayList<GasStation> x) {
        stations = x;
    }
}
