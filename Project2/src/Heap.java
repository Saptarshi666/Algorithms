/*
 * Name: <your name>
 * EID: <your EID>
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.lang.Math;

public class Heap {
    private ArrayList<GasStation> minHeap;
    int MinDist;
    private ArrayList<Integer> heap_indexes = new ArrayList<>();
    public Heap() {
        minHeap = new ArrayList<GasStation>();
    }

    /**
     * buildHeap(ArrayList<GasStation> stations)
     * Given an ArrayList of GasStation, build a min-heap keyed on each GasStation's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param stations
     */
    public void buildHeap(ArrayList<GasStation> stations) {
        // TODO: implement this method
    	minHeap.clear();
    	heap_indexes.clear();
    	for(int i = 0; i<stations.size(); i++)
    		heap_indexes.add(0);
   for(int i = 0 ; i<stations.size(); i++)
	  insertNode(stations.get(i));
  /* int size = minHeap.size() ;
   for(int i = (int) Math.floor(size/2); i>= 0; i--)
	   heapify(i,minHeap);*/
   
    	/*for(int i = 0; i<stations.size(); i++)
    {
    	if(stations.get(i).distance == 0)
    		minHeap.add(0, stations.get(i));
    	else {
    		minHeap.add(stations.get(i));
    	}
    	
    }
    for (int i = 0; i<minHeap.size(); i++)
    {
    	minHeap.get(i).heap_index = i;
    }*/
    }
   public int size() {
	   return minHeap.size();
   }
   public ArrayList<GasStation> returnHeap(){
	   return minHeap;
   }
	 
    /**
     * insertNode(GasStation in)
     * Insert a GasStation into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the GasStation to insert.
     */
    public void insertNode(GasStation in) {
        // TODO: implement this method
    	int heap_index = minHeap.size();
    		minHeap.add(in);
    		heapifyUp(heap_index,in);
    	
    	
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public GasStation findMin() {
        // TODO: implement this method
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public GasStation extractMin() {
        // TODO: implement this method
    	if (minHeap.size() == 0)
    		return null;
        GasStation a = minHeap.get(0);
        if(minHeap.size()!=1)
        {
        minHeap.set(0, minHeap.get(minHeap.size()-1));
        minHeap.remove(minHeap.size()-1);
        heap_indexes.set(a.getID(), -1);
        heapifyDown(0);
        return a;
        }
        else
        {
        	 minHeap.remove(minHeap.size()-1);
             heap_indexes.set(a.getID(), -1);
             return a;
        }
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        // TODO: implement this method
    	minHeap.set(index, minHeap.get(minHeap.size()-1));
    	minHeap.remove(minHeap.size()-1);
    	heapifyDown(index);
    }

    /**
     * changeKey(GasStation r, int newDist)
     * Changes minDist of GasStation s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the GasStation in the heap that needs to be updated.
     * @param newDist - the new cost of GasStation r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(GasStation r, double newDist) {
        // TODO: implement this method
    	int a = heap_indexes.get(r.getID());
    	if(a!=-1) {
    	minHeap.get(a).distance = newDist;
    	heapifyUp(a,r);
    	heapifyDown(a);}
    	
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getID() + " ";
        }
        return output;
    }

    /** Heapify up(index, GasStation in)
     * given the index of the GasStation and heapify it up
     * O(log(n))
     * **/
    public void heapifyUp(int index, GasStation in){
        // TODO: implement this method
    	if(index > 0)
    	{
    		int parent_index = 0;
    		if(index%2 == 0)
    			parent_index = (index -2)/2;
    		else
    			parent_index = (index-1)/2;
    		if(minHeap.get(parent_index).distance > minHeap.get(index).distance)
    		{
    			GasStation swap = minHeap.get(parent_index);
    			minHeap.set(parent_index, in);
    			minHeap.set(index, swap);
    			int a = minHeap.get(parent_index).getID();
    			heap_indexes.set(a, parent_index);
    			int b = minHeap.get(index).getID();
    			heap_indexes.set(b, index);
    		}
    		else
    		{
    			int a = minHeap.get(index).getID();
    			heap_indexes.set(a, index);
    		}
    		heapifyUp(parent_index,in);
    	}
    }

    /** Heapify down(index)
     * given the index of the GasStation and heapify it down
     * O(log(n))
     * **/
    public void heapifyDown(int index){
        // TODO: implement this method
    	int j = 0 ;
    	/*if(minHeap.size()== 1)
    	{
    		heap_indexes.set(minHeap.get(index).getID(), index);
    		return;
    	}*/
     if (((index+1)*2)>minHeap.size())
     {
    	 heap_indexes.set(minHeap.get(index).getID(), index);
    	 return;
     }
     else if (((index+1)*2)<minHeap.size()) {
    	 int left = (2*index) + 1;
    	 int right = (2*index) + 2;
    	 if(minHeap.get(left).distance < minHeap.get(right).distance)
    		 j = left;
    	 else
    		 j = right;
    	 
     }
     else if (((index+1)*2) == minHeap.size())
     {
    	 j = (2*index) + 1 ;
     }
     if(minHeap.get(j).distance<minHeap.get(index).distance)
     {
    	 GasStation a = minHeap.get(index);
    	 minHeap.set(index, minHeap.get(j));
    	 minHeap.set(j, a);
    	 int b = minHeap.get(index).getID();
    	 int c = minHeap.get(j).getID();
    	 heap_indexes.set(b, index);
    	 heap_indexes.set(c, j);
    	 heapifyDown(j);
     }
     else
     {
    	 heap_indexes.set(minHeap.get(index).getID(), index);
     }
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<GasStation> toArrayList() {
        return minHeap;
    }
}
