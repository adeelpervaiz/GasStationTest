
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import main.java.net.bigpoint.assessment.gasstation.GasPump;
import main.java.net.bigpoint.assessment.gasstation.GasStation ;
import main.java.net.bigpoint.assessment.gasstation.GasType;
import main.java.net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import main.java.net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import main.java.net.bigpoint.assessment.gasstation.exceptions.PumpNotExistException;


public class GasStationSimulator implements GasStation {
	
	private HashMap<GasType, Double> prices ;
	
	Collection<GasPump> pumps ;
	private double totalRevenue ;
	private int canellationsOfGasNotAvailable ;
	private int canellationsOfGasExpensive ;
	private int canellationsOfPumpNotAvailable ;
	private int numberOfSales ;
	
	GasStationSimulator(){
		prices = new HashMap<GasType, Double>();
		pumps = new ArrayList<GasPump>();
		totalRevenue = 0 ;
	}
	
	
	/**
	 * @param type
	 *            The type of gas the customer wants to buy
	 * @param amountInLiters
	 *            The amount of gas the customer wants to buy. Nothing less than this amount is acceptable!
	 * @param maxPricePerLiter
	 *            The maximum price the customer is willing to pay per liter
	 */
	public void startQueue(GasType type, double amountInLiters, double maxPricePerLiter){
		try {
			this.buyGas(type, amountInLiters, maxPricePerLiter);
		} catch (NotEnoughGasException e) {
			System.out.println("Excp : " + e.getLocalizedMessage() + "\n\n");
		} catch (GasTooExpensiveException e) {
			System.out.println("Excp : " + e.getLocalizedMessage() + "\n\n");
		}
	}
	
	
	@Override
	public String getGasType(GasType type){
		if(type == GasType.DIESEL){
			return "Diesel" ;
		}
		else if(type == GasType.REGULAR){
			return "Regular" ;			
		}
		else if(type == GasType.SUPER){
			return "Super" ;		
		}
		return "Unknown" ;		
	}
	

	@Override
	public void addGasPump(GasPump pump) {
		for(GasPump addedPump : pumps){
			if(addedPump.getGasType().equals(pump.getGasType())){
				
			}
		}
		pumps.add(pump);
	}

	@Override
	public Collection<GasPump> getGasPumps() {
		return pumps;
	}

	@Override
	public double buyGas(GasType type, double amountInLiters, double maxPricePerLiter)
			throws NotEnoughGasException, GasTooExpensiveException {
		
		boolean pumpExist = false  ;
		
		for(GasPump pump : pumps){
			if(pump.getGasType().equals(type)){
				
				pumpExist = true ;
				
				System.out.println("Sell Gas Type : " + this.getGasType(type)+ " @ "+getPrice(type)+"/Litre");
				System.out.println("Remaing Gas In Amount : " + pump.getRemainingAmount());
				System.out.println("Purchasing Gas In Amount : " + amountInLiters);
				
				if(maxPricePerLiter < getPrice(type)){
					System.out.println("### CANNOT SELL GAS @ "+ maxPricePerLiter +"/Litre : TOO EXPENSIVE ####");
					this.setCanellationsOfGasExpensive(this.getCanellationsOfGasExpensive() + 1) ;
					throw new GasTooExpensiveException("Gas is Expensive");
				}
				else if(pump.getRemainingAmount() < amountInLiters){
					System.out.println("### CANNOT SELL : NOT ENOUGH GAS ####");
					this.setCanellationsOfGasNotAvailable(this.getCanellationsOfGasNotAvailable() + 1) ;
					throw new NotEnoughGasException("Gas Not Available");
					
				}
				else {
					pump.pumpGas(amountInLiters);
					this.totalRevenue += amountInLiters ;
					setNumberOfSales(this.getNumberOfSales() + 1);
					break ;
				}
			}
		}
		
		if(pumpExist==false){
			System.out.println("### CANNOT SELL : PUMP NOT EXIST ####");
			this.setCanellationsOfPumpNotAvailable(this.getCanellationsOfPumpNotAvailable() + 1) ;
			try {
				throw new PumpNotExistException("We don't sell this type of Gas");
			} catch (PumpNotExistException e) {
				System.out.println("Excp : " + e.getLocalizedMessage() + "\n\n");
			}
		}
		
		return 0;
	}

	@Override
	public double getRevenue() {
		return totalRevenue ;
	}
	
	@Override
	public double getAvailableGas(GasType type){
		
		for(GasPump pump : pumps){
			if(pump.getGasType().equals(type)) {
				return pump.getRemainingAmount() ;
			}
		}
		
		return 0 ;
	}

	

	@Override
	public int getNumberOfCancellationsNoGas() {
		return this.getCanellationsOfGasNotAvailable();
	}

	@Override
	public int getNumberOfCancellationsTooExpensive() {
		return this.getCanellationsOfGasExpensive() ;
	}

	@Override
	public double getPrice(GasType type) {
		return prices.get(type);
	}

	@Override
	public void setPrice(GasType type, double price) {
		prices.put(type, price);
	}

	/**
	 * Get Gas Short Cancellations Count 
	 */
	public int getCanellationsOfGasNotAvailable() {
		return canellationsOfGasNotAvailable;
	}
	
	/**
	 * Set Gas Short Cancellations Count
	 * @param canellationsOfGasNotAvailable
	 */

	public void setCanellationsOfGasNotAvailable(int canellationsOfGasNotAvailable) {
		this.canellationsOfGasNotAvailable = canellationsOfGasNotAvailable;
	}

	/**
	 * Get Gas Expensive Cancellations Count 
	 */
	public int getCanellationsOfGasExpensive() {
		return canellationsOfGasExpensive;
	}

	/**
	 * Set Gas Expensive Cancellations Count
	 * @param  canellationsOfGasExpensive
	 */
	public void setCanellationsOfGasExpensive(int canellationsOfGasExpensive) {
		this.canellationsOfGasExpensive = canellationsOfGasExpensive;
	}


	/**
	 * Get Pump Not available Cancel Count 
	 */
	public int getCanellationsOfPumpNotAvailable() {
		return canellationsOfPumpNotAvailable;
	}


	/**
	 * Set Pump not available Count
	 * @param  canellationsOfPumpNotAvailable
	 */
	public void setCanellationsOfPumpNotAvailable(int canellationsOfPumpNotAvailable) {
		this.canellationsOfPumpNotAvailable = canellationsOfPumpNotAvailable;
	}


	@Override
	public int getNumberOfSales() {
		return this.numberOfSales;
	}
	
	public void setNumberOfSales(int numberOfSales) {
		System.out.println("**** GAS SOLD **** \n");
		this.numberOfSales = numberOfSales;
	}
}
