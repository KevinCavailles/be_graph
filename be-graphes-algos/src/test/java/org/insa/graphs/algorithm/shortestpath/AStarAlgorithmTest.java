package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.junit.After;
import org.junit.Test;

public class AStarAlgorithmTest extends algorithmTest{

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAStarShortest() {
		
		for(int i=0; i<dataShortest.length; i++) {
			AStarAlgorithm aStar = new AStarAlgorithm(dataShortest[i]);
			ShortestPathSolution solution = aStar.run();
			Path cheminSolution = solution.getPath();
			
			Graph graph = solution.getInputData().getGraph();
			 
			//Vérifie chemin valide
			assertTrue(cheminSolution.isValid());
			assertEquals(cheminSolution.getOrigin(), graph.get(tabOrigin[i]));
			assertEquals(cheminSolution.getDestination(), graph.get(tabDestination[i]));
			   
			 // List of nodes
			 ArrayList<Node> nodes = new ArrayList<Node>();
			 List<Arc> arcsSolution = cheminSolution.getArcs();
			 nodes.add(arcsSolution.get(0).getOrigin());
			 
			 for(int j=0;j<arcsSolution.size(); j++) {
		        nodes.add(arcsSolution.get(j).getDestination());	
		     }
			 
			 Path cheminShortest = Path.createShortestPathFromNodes(graph, nodes);
			 
			 //Vérifie que les chemins sont identiques (Arcs, taille, temps)
			 assertEquals(cheminSolution.getArcs(), cheminShortest.getArcs());
			 assertTrue(cheminSolution.getLength() == cheminShortest.getLength());
			 assertTrue(cheminSolution.getMinimumTravelTime() == cheminShortest.getMinimumTravelTime());
		}
	}
	
	@Test
	public void testAStarFastest() {
		
		for(int i=0; i<dataShortest.length; i++) {
			AStarAlgorithm aStar = new AStarAlgorithm(dataFastest[i]);
			ShortestPathSolution solution = aStar.run();
			Path cheminSolution = solution.getPath();
			
			Graph graph = solution.getInputData().getGraph();
			 
			//Vérifie chemin valide
			assertTrue(cheminSolution.isValid());
			assertEquals(cheminSolution.getOrigin(), graph.get(tabOrigin[i]));
			assertEquals(cheminSolution.getDestination(), graph.get(tabDestination[i]));
			   
			 // List of nodes
			 ArrayList<Node> nodes = new ArrayList<Node>();
			 List<Arc> arcsSolution = cheminSolution.getArcs();
			 nodes.add(arcsSolution.get(0).getOrigin());
			 
			 for(int j=0;j<arcsSolution.size(); j++) {
		        nodes.add(arcsSolution.get(j).getDestination());	
		     }
			 
			 Path cheminFastest = Path.createFastestPathFromNodes(graph, nodes);
			 
			 //Vérifie que les chemins sont identiques (Arcs, taille, temps)
			 assertEquals(cheminSolution.getArcs(), cheminFastest.getArcs());
			 assertTrue(cheminSolution.getLength() == cheminFastest.getLength());
			 assertTrue(cheminSolution.getMinimumTravelTime() == cheminFastest.getMinimumTravelTime());
			 
		}
	}
	
	@Test
	public void testDijkstraInfeasable() {
		
		 AStarAlgorithm aStar = new AStarAlgorithm(dataInfeasable);
		 ShortestPathSolution solution =  aStar.run();
		 Path cheminSolution = solution.getPath();
		 
		 //Vérifie chemin valide
		 assertEquals(cheminSolution, null);
		 assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
	}
	
	

}
