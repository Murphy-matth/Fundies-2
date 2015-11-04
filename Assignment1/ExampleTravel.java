interface habitation {
	boolean ShuttleAllowed();
	boolean TransporterAllowed();
}

interface transportation {
	boolean ValidTravel(habitation to, habitation from);
}
public class ExampleTravel {

}


class Planet implements habitation {
	String name;
	int population;
	int spaceports;
	
	Planet(String name, int population, int spaceports) {
		this.name = name;
		this.population = population;
		this.spaceports = spaceports;
	}
	
	public boolean ShuttleAllowed() {
		return this.spaceports!= 0;
	}
	
	public boolean TransporterAllowed() {
		return true;
	}
}

class SpaceStation implements habitation {
	String name;
	int population;
	int transporterpads;
	
	SpaceStation(String name, int population, int transporterpads) {
		this.name = name;
		this.population = population;
		this.transporterpads = transporterpads;
	}
	
	public boolean ShuttleAllowed() {
		return true;
	}
	
	public boolean TransporterAllowed() {
		return this.transporterpads != 0;
	}
}

class Transporter implements transportation {
	habitation to;
	habitation from;
	
	Transporter(habitation to, habitation from) {
		if(ValidTravel(to, from)) {
			this.to = to;
			this.from = from;
		}
		else {
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}
	public boolean ValidTravel(habitation to, habitation from) {
		return true;
	}
}

class Shuttle implements transportation {
	habitation to;
	habitation from;
	int fuel;
	int capacity;
	
	Shuttle(habitation to, habitation from, int fuel, int capacity) {
		if(ValidTravel(to, from)) {
			this.to = to;
			this.from = from;
			fuel = this.fuel = fuel;
			this.capacity = capacity;
		}
		else {
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}
	
	public boolean ValidTravel(habitation to, habitation from) {
		return to.ShuttleAllowed() && from.ShuttleAllowed();
	}
}

class SpaceElevator implements transportation {
	habitation to;
	habitation from;
	
	SpaceElevator(habitation to, habitation from) {
		if(ValidTravel(to, from)) {
			this.to = to;
			this.from = from;
		}
		else {
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}
	
	public boolean ValidTravel(habitation to, habitation from) {
		return !(to.getClass() == from.getClass());
	}

}
