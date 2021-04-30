package it.polito.tdp.extflightdelays.model;

import java.util.Collection;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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
		//vertice = aeroporto
		Graph<Airport, DefaultWeightedEdge> fabio = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiunge vertici 
		Graphs.addAllVertices(fabio, dao.loadAllAirports());
		
		//aggiunge edges
		for (FabioVolo f : dao.getFlightsSet(distMin))
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
		
		return s;
	}
}
