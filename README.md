# GasStationTest

The goal is to implement the interface of a gas station which has multiple gas pumps:

* Each gas pump provides a specific amount of gas of a certain gas type.
* Additionally, customers are requesting gas with different requirements regarding the amount, type of gas and price they are willing to pay for it. 
* This implementation has to take all this into account and serve gas when available. 


##Assumptions
- Need to show if the gasType is available, if not Raise Exception
- Need to check if customer Offering to pay Price for Per Litre is Higher than our set price.
- Generate Report at the end how many cancellations of Gas happened
- Categorize Cancelleation Types in Numbers
- Geenrate Report and Show Revenue.
- Each Gas Stations will only one pump for each Gas Type.

## Solution
- This solution made to keep in mind that every gas station will only have on pump for each Gas Type.
- This solution will not work, if we someone try to add 2 or more pumps for same Gas Type.
- I avoided added multiple gas types to reduce work, It can easily be done by some modification, but wasn't required in the job.
- I only added one Type of Pump for Each Gas, will not work if we add multiple pumps of same type, because I am checking only one pump type in loop if matches, and Gas NotAvailable, it raises exception, so adding multiple type of Pump will require to different implementation which is not yet Implemented.

##Given Classes
```
GasPump
GasStation
GasType
GasTooExpensiveException
NotEnoughGasException
```

##New Methods
To Gracefully handle the required job, I have added these two methods
```Java
public interface GasStation {
...
...

double getAvailableGas(GasType type);	// will give available gas for given Type

String getGasType(GasType type);		// will give Name of Gas Type in String, to print some beautiful Messages.

...
..
}
```

##Added Classes
- GasStationSimulator : Simulates the GasStation WorkFlow
- MainProgram : Starts Simulation and Generate Report
- PumpNotExistException : Added new Exception if Customer wants to buy Gas But That Gas Type Pump not exist


```Java
public class GasStationSimulator implements GasStation {
...
...

	GasStationSimulator(){
		prices = new HashMap<GasType, Double>();
		pumps = new ArrayList<GasPump>();
		totalRevenue = 0 ;
	}

...
..
}

public class MainProgram {
	
	public MainProgram() {
		// TODO Auto-generated constructor stub
	}
		
	public static void main(String[] args){
		System.out.println("Working fine");
		GasStationSimulator station1 = new GasStationSimulator();
		
		...
		...
	}
	
	...
	...
}

public class PumpNotExistException extends Exception {
...
..
...
}

```


##TODOs
- Add Implementation to handle Multiple Pumps of Same Type
- Add Implementation to configure, if Station has one pump of each type or multiple can exist
- Add Exception if customer set Single Pump for each type, and tries to add another, Should Raise Exception.