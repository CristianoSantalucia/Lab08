package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model 
{
	ExtFlightDelaysDAO dao; 
	
	public Model()
	{ 
		dao = new ExtFlightDelaysDAO();
	}
	
	public StringBuilder creaGrafoFabio(long distMin)
	{
		StringBuilder s = new StringBuilder();

		Graph<Airport, DefaultWeightedEdge> fabio = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiunge vertici 
		Graphs.addAllVertices(fabio, dao.loadAllAirports());  
		
		//aggiunge edges
		for (FabioVolo f : dao.getVoliSet(distMin))
		{
			Airport origin = new Airport();
			Airport dest = new Airport();
			origin.setId((int) f.getOrigin());
			dest.setId((int) f.getDestination());
			Graphs.addEdge(fabio,origin,dest,f.getDistance());
		}
		
		s.append("TOT VERTICES: ");
		s.append(fabio.vertexSet().size());
		s.append("\nTOT EDEGES: ");
		s.append(fabio.edgeSet().size());
		s.append("\n");
		
		List<FabioVolo> lista = new ArrayList<>(dao.getFlightsSet(distMin));
		Collections.sort(lista);
		for (FabioVolo f : lista)
			s.append(f);
		return s;
	}
}
