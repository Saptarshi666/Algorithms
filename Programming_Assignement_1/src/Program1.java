/*
 * Name: Saptarshi Mondal
 * EID: sm72999
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 *
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution. However, do not add extra import statements to this file.
 */
public class Program1 extends AbstractProgram1 {

    /**
     * Determines whether a candidate Matching represents a solution to the stable matching problem.
     * Study the description of a Matching in the project documentation to help you with this.
     */
    @Override
    public boolean isStableMatching(Matching problem) {
        /* TODO implement this function */
    	int m = problem.getCityCount();
    	LinkedList<Integer> q = new LinkedList<>();
    	int n = problem.getDoctorCount();
    	ArrayList<ArrayList<Integer>> citiespreflist = problem.getCityPreference();
    	ArrayList<ArrayList<Integer>> doctorpreflist = problem.getDoctorPreference();
    	ArrayList<Integer> cities_pos = problem.getCityPositions();
    	ArrayList<Integer> doc_matching = problem.getDoctorMatching();
    	for(int i = 0; i<doc_matching.size(); i++)
    	{
    		int j = doc_matching.get(i);
    		if(j!=-1)
    		{
    		ArrayList<Integer> citypreflist = citiespreflist.get(j);
    		for(int k = 0; citypreflist.get(k)!=i; k++)
    		{
    				int l = citypreflist.get(k);
    			if(doc_matching.get(l) == -1)
    				return false;
    			else if(doc_matching.get(l)!=j)
    			{
    				ArrayList<Integer> docpreflist = doctorpreflist.get(l);
    				int o = doc_matching.get(l);
    				int b = -1, c= -1;
    				for(int p = 0; p<docpreflist.size(); p++)
    				{
    					if(docpreflist.get(p) == j)
    						b = p;
    					if(docpreflist.get(p) == o)
    						c = p;
    				}
    				if(b<c)
    					return false; 					
    			}
    			
    		}
    	}
    	}
    	return true;
    }

    /**
     * Determines a solution to the stable matching problem from the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMatchingGaleShapley_cityoptimal(Matching problem) {
        /* TODO implement this function */
    	int m = problem.getCityCount();
    	LinkedList<Integer> q = new LinkedList<>();
    	int n = problem.getDoctorCount();
    	ArrayList<ArrayList<Integer>> citiespreflist = problem.getCityPreference();
    	ArrayList<ArrayList<Integer>> doctorpreflist = problem.getDoctorPreference();
    	ArrayList<Integer> cities_pos = problem.getCityPositions();
    	ArrayList<Integer> doc_matching = new ArrayList<Integer>();
    	//ArrayList<Integer> doc_matching1 = problem.getDoctorMatching();
    	ArrayList<ArrayList<Integer>> doctorpreflistinv = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0; i< n; i++)
    		doc_matching.add(-1);
    	for(int i = 0; i<doctorpreflist.size(); i++)
    	{
    		ArrayList<Integer> docprefinv = doctorpreflist.get(i);
    		ArrayList<Integer> prefinv = new ArrayList<>();
    		for(int j = 0 ; j<docprefinv.size(); j++)
    			prefinv.add(-1);
    		for(int j = 0; j <docprefinv.size(); j++)
    		{
    			int a = docprefinv.get(j);
    			prefinv.set(a, j);;
    		}
    		doctorpreflistinv.add(prefinv);
    		
    	}
    	for(int i = 0; i< m ; i++)
    		q.add(i);
    	while(!q.isEmpty())
    	{
    		int a = q.remove();
    		int city_pos = cities_pos.get(a);
    		ArrayList<Integer> city_pref = citiespreflist.get(a);
    		int i = 0;
    		while(city_pos!=0)
    		{
    			int b = city_pref.get(i);
    			int c = doc_matching.get(b);
    			if(c==-1)
    			{
    				doc_matching.set(b, a);
    				city_pos = city_pos -1;
    			}
    			else
    			{
    				ArrayList<Integer> docpref = doctorpreflistinv.get(b);
    			    int d = docpref.get(a);
    			    int e = docpref.get(c);
    			    if(d>e)
    			    {
    			    	doc_matching.set(b, a);
        				city_pos = city_pos -1;
        				int f = cities_pos.get(c);
        				f = f+ 1;
        				cities_pos.set(c, f);
        				if(!q.contains(c))
        				{
        					q.add(c);
        				}
    			    }
    				
    			}
    			i++;
    		}
    		citiespreflist.set(a, city_pref);
    		cities_pos.set(a, city_pos);
    	}
    	problem.setDoctorMatching(doc_matching);
        return problem;
    }
   

    /**
     * Determines a solution to the stable matching problem from the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMatchingGaleShapley_doctoroptimal(Matching problem) {
        /* TODO implement this function */
    	int m = problem.getCityCount();
    	LinkedList<Integer> q = new LinkedList<>();
    	int n = problem.getDoctorCount();
    	ArrayList<ArrayList<Integer>> citiespreflist = problem.getCityPreference();
    	ArrayList<ArrayList<Integer>> doctorpreflist = problem.getDoctorPreference();
    	ArrayList<Integer> cities_pos = problem.getCityPositions();
    	ArrayList<Integer> doc_matching = new ArrayList<Integer>();
    	ArrayList<ArrayList<Integer>> doctors_selected = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0 ; i<m ; i++)
    	{
    		ArrayList<Integer> temp = new ArrayList<Integer>();
    		doctors_selected.add(temp);
    	}
    	for(int i = 0; i< n; i++)
    		doc_matching.add(-1);
    	
    	int num_pref = 0;
    	for(int i = 0; i<doctorpreflist.size(); i++)
    	{
    		num_pref = num_pref+ doctorpreflist.get(i).size();
    	}
    	for(int i = 0; i< n ; i++)
    		q.add(i);
    	while(!q.isEmpty() && num_pref!=0)
    	{
    		int a = q.remove();
    		ArrayList<Integer> doctor_pref = doctorpreflist.get(a);
    		for(int i=0; i<doctor_pref.size(); i++ )
    		{
    			int city = doctor_pref.get(i);
    			int pos = cities_pos.get(city);
    			if(pos > 0)
    			{
    				doc_matching.set(a,city);
    				ArrayList<Integer> c = doctors_selected.get(city);
    				c.add(a);
    				doctors_selected.set(city, c);
    				pos = pos -1;
    				cities_pos.set(city, pos);
    				num_pref = num_pref -1 ;
    				break;
    			}
    			else
    			{
    				ArrayList<Integer> c = doctors_selected.get(city);
    				ArrayList<Integer> citypref = citiespreflist.get(city);
    				int flag = 0;
    				int pos3 = -6;
    			    for(int i1 = 0; i1<citypref.size(); i1++)
    			    {
    			    	if(citypref.get(i1)== a)
    			    		{
    			    		pos3 = i1;
    			    		break;
    			    		}
    			    }
    				for(int k = citypref.size()-1; k> pos3; k--)
    				{
    					int doccheck = citypref.get(k);
    					if(c.contains(doccheck))
    					{
    						doc_matching.set(a,city);
    						doc_matching.set(doccheck, -1);
    	    				c.remove(c.indexOf(doccheck));
    	    				c.add(a);
    	    				doctors_selected.set(city, c);
    	    				
    	    				q.add(doccheck);
    	    				flag = 1;
    	    				break;
    					}
    				}
    					
    				num_pref = num_pref -1 ;
    				if(flag!=0)
						break;
    			}
    		}
    	}
    	problem.setDoctorMatching(doc_matching);
        return problem;
    }
}