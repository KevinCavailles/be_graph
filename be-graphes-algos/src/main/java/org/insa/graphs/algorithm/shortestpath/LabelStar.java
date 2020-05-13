package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
	
	private Double coutEstime;

	public LabelStar(Node sommetCourant, double cout, double coutEstime, Arc pere) {
		super(sommetCourant, cout, pere);
		this.coutEstime = coutEstime;
	}
	
	@Override
	public double getTotalCost(){
		return this.getCost() + this.coutEstime;
	}
}
