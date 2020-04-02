package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	public DijkstraAlgorithm(ShortestPathData data) {
		super(data);
	}

	@Override
	protected ShortestPathSolution doRun() {
		final ShortestPathData data = getInputData();
		Graph graph = data.getGraph();

		final int nbNodes = graph.size();

		// Create the heap and a static array to store the labels
		org.insa.graphs.algorithm.utils.PriorityQueue<Label> labelsHeap = new BinaryHeap<>();
		Label labelsList[] = new Label[nbNodes];
		Arrays.fill(labelsList, null);

		//Put the origin in the heap
		Node origin = data.getOrigin();
		labelsList[origin.getId()] = new Label(data.getOrigin(), 0, null);
		labelsHeap.insert(new Label(origin, 0, null));

		// Notify observers about the first event (origin processed).
		notifyOriginProcessed(data.getOrigin());

		// Initialize array of predecessors.
		Arc[] predecessorArcs = new Arc[nbNodes];

		Label current, next;
	

		// While the heap has elements and the destination has not been reached and marked
		while (!labelsHeap.isEmpty() 
				&& (labelsList[data.getDestination().getId()] == null || !labelsList[data.getDestination().getId()].isMarked() )) {
			
			// Remove the min 
			current = labelsHeap.findMin();
//System.out.println("cout  :"+current.getCost());
			try {
				labelsHeap.remove(current);
			} catch (Exception e) {
				System.out.println("fatal error");
			}
			
			current.setMark();

			// Iterate over the arc of the removed element
			for (Arc arc : graph.get(current.getCurrent().getId()).getSuccessors()) {
				if (!data.isAllowed(arc)) {
					continue;
				}

//System.out.println("origine : "+arc.getOrigin().getId()+" destination : "+ arc.getDestination().getId());
				next = labelsList[arc.getDestination().getId()];
			
				//If the destination of an arc does not exist or is not marked
				if ( next != null && next.isMarked()) {
					continue;
				}
				
				// Either create it or check if the associated cost can be reduced
				if (next == null) {
					next = new Label(arc.getDestination(),current.getCost() + data.getCost(arc), arc);
					
					labelsList[arc.getDestination().getId()] = next;
					labelsHeap.insert(next);
					
				
				}else{
					if (next.getCost() > current.getCost() + data.getCost(arc)) {
						next.setCost(current.getCost() + data.getCost(arc));
						next.setFather(arc);
					}
				}
				notifyNodeReached(arc.getDestination());

			}
//System.out.println("");
		}

		
		ShortestPathSolution solution = null;

		//Check if the destination has been reached from the source
		if (labelsList[data.getDestination().getId()] == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {
			// The destination has been found, notify the observers.
			notifyDestinationReached(data.getDestination());

			// Create the path from the array of predecessors...
			ArrayList<Arc> arcs = new ArrayList<>();
			Arc arc = labelsList[data.getDestination().getId()].getFather();
			while (arc != null) {
				arcs.add(arc);
				arc = labelsList[arc.getOrigin().getId()].getFather();
			}

			// Reverse the path...
			Collections.reverse(arcs);

			// Create the final solution.
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
		}

		return solution;
	}

}
