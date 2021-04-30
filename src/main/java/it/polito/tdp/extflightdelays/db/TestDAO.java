package it.polito.tdp.extflightdelays.db;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.extflightdelays.model.FabioVolo;

public class TestDAO {

	public static void main(String[] args) {

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();

		List<FabioVolo> lista = new ArrayList<>(dao.getFlightsSet(500));
		lista.sort((e1,e2)->(int) (e1.getOrigin()-e2.getOrigin()));
		System.out.println(lista.size());
		
		for (FabioVolo	f : lista)
		{
			System.out.println(f);
		}
	}
}
