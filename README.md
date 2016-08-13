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

##Given Classes
```
GasPump
GasStation
GasType
GasTooExpensiveException
NotEnoughGasException
```

##New Methods
```Java
public interface GasStation {
...
...

double getAvailableGas(GasType type);

String getGasType(GasType type);

...
..
}
```

##Added Classes
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

```