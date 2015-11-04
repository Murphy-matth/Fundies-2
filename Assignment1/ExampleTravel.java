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
		name = this.name;
		population = this.population;
		spaceports = this.spaceports;
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
		name = this.name;
		population = this.population;
		transporterpads = this.transporterpads;
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
			to = this.to;
			from = this.from;
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
			to = this.to;
			from = this.from;
			fuel = this.fuel;
			capacity = this.capacity;
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
			to = this.to;
			from = this.from;
		}
	}
	
	public boolean ValidTravel(habitation to, habitation from) {
		return !(to.getClass() == from.getClass());
	}

}
