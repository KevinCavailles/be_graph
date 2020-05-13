package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.After;
import org.junit.Before;

public class algorithmTest {

	 protected Graph graph, graphPolynesie, graphBelgique;

	    
    final String hauteGaronne = "/Users/Kevin/Desktop/programmation/java_files/Maps/europe/france/haute-garonne.mapgr";
    final String polynesie = "/Users/Kevin/Desktop/programmation/java_files/Maps/europe/france/french-polynesia.mapgr";
    final String belgique = "/Users/Kevin/Desktop/programmation/java_files/Maps/europe/belgique/belgium.mapgr";
    
    final static int taille = 5;
    
    protected ShortestPathData dataShortest[];
    protected ShortestPathData dataFastest[];
    protected ShortestPathData dataInfeasable;
    protected int tabOrigin[], tabDestination[];
    
	   

	@Before
	public void setUp() throws Exception {
	
		
		List<ArcInspector> arcList = ArcInspectorFactory.getAllFilters();
		ArcInspector arcShortestAllRoad = arcList.get(0);
		ArcInspector arcFastestAllRoad = arcList.get(2);

        try (GraphReader readerHauteGaronne = new BinaryGraphReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream(hauteGaronne))))) {

            // TODO: Read the graph.
            graph = readerHauteGaronne.read();
        }
        
        try (GraphReader readerPolynesie = new BinaryGraphReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream(polynesie))))) {

            // TODO: Read the graph.
            graphPolynesie = readerPolynesie.read();
        }
        
        try (GraphReader readerBelgique = new BinaryGraphReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream(belgique))))) {

            // TODO: Read the graph.
            graphBelgique = readerBelgique.read();
        }
        
        dataShortest = new ShortestPathData[taille];
        dataFastest = new ShortestPathData[taille];
        tabOrigin = new int[taille]; tabDestination = new int[taille];
        
        tabOrigin[0] = 10991; tabOrigin[1] = 102582; tabOrigin[2]= 81460; tabOrigin[3]= 942294;  tabOrigin[4]= 10228;
        
        tabDestination[0] = 89149; tabDestination[1]= 84718; tabDestination[2] = 129594; tabDestination[3] = 412806 ; tabDestination[4]= 666164 ;
        
        
        dataShortest[0] = new ShortestPathData(graph, graph.get(tabOrigin[0]), graph.get(tabDestination[0]), arcShortestAllRoad);
        dataShortest[1] = new ShortestPathData(graph, graph.get(tabOrigin[1]), graph.get(tabDestination[1]), arcShortestAllRoad);
        dataShortest[2] = new ShortestPathData(graph, graph.get(tabOrigin[2]), graph.get(tabDestination[2]), arcShortestAllRoad);
        dataShortest[3] = new ShortestPathData(graphBelgique, graphBelgique.get(tabOrigin[3]), graphBelgique.get(tabDestination[3]), arcShortestAllRoad);
        dataShortest[4] = new ShortestPathData(graphBelgique, graphBelgique.get(tabOrigin[4]), graphBelgique.get(tabDestination[4]), arcShortestAllRoad);
       
        dataFastest[0] = new ShortestPathData(graph, graph.get(tabOrigin[0]), graph.get(tabDestination[0]) , arcFastestAllRoad);
        dataFastest[1] = new ShortestPathData(graph, graph.get(tabOrigin[1]), graph.get(tabDestination[1]), arcFastestAllRoad);
        dataFastest[2] = new ShortestPathData(graph, graph.get(tabOrigin[2]), graph.get(tabDestination[2]), arcFastestAllRoad);
        dataFastest[3] = new ShortestPathData(graphBelgique, graphBelgique.get(tabOrigin[3]), graphBelgique.get(tabDestination[3]), arcFastestAllRoad);
        dataFastest[4] = new ShortestPathData(graphBelgique, graphBelgique.get(tabOrigin[4]), graphBelgique.get(tabDestination[4]), arcFastestAllRoad);

        
        
        dataInfeasable = new ShortestPathData(graphPolynesie, graphPolynesie.get(2984), graphPolynesie.get(10467), arcFastestAllRoad);
	}

	@After
	public void tearDown() throws Exception {
	}

}
