package it.polito.tdp.extflightdelays.model;

public class FabioVolo implements Comparable<FabioVolo>
{
	private long origin, destination;
	private double distance;
	
	public FabioVolo(long origin, long destination, double distance)
	{
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}
	public long getOrigin()
	{
		return origin;
	}
	public long getDestination()
	{
		return destination;
	}
	public double getDistance()
	{
		return distance;
	}
	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (destination ^ (destination >>> 32));
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (origin ^ (origin >>> 32));
		return result;
	}
	@Override public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		FabioVolo other = (FabioVolo) obj;
		if (destination != other.destination) return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance)) return false;
		if (origin != other.origin) return false;
		return true;
	}
	@Override public String toString()
	{
		return origin + " " + destination + " " + distance + "\n";
	}
	
	
	@Override public int compareTo(FabioVolo e2)
	{
		return (int) (this.origin - e2.origin) == 0 ? (int) (this.destination - e2.destination) : (int) (this.origin - e2.origin);
	}
}
