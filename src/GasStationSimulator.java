
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import main.java.net.bigpoint.assessment.gasstation.GasPump;
import main.java.net.bigpoint.assessment.gasstation.GasStation ;
import main.java.net.bigpoint.assessment.gasstation.GasType;
import main.java.net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import main.java.net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;


public class GasStationSimulator implements GasStation {
	
	private HashMap<GasType, Double> prices ;
	
	Collection<GasPump> pumps ;
	private double totalRevenue ;
	private int canellationsOfGasNotAvailable ;
	private int canellationsOfGasExpensive ;
	
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
			System.out.println("Exp : " + e.getLocalizedMessage() + "\n\n");
		} catch (GasTooExpensiveException e) {
			System.out.println("Exp : " + e.getLocalizedMessage() + "\n\n");
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
		// TODO Auto-generated method stub
		pumps.add(pump);
	}

	@Override
	public Collection<GasPump> getGasPumps() {
		// TODO Auto-generated method stub
		return pumps;
	}

	@Override
	public double buyGas(GasType type, double amountInLiters, double maxPricePerLiter)
			throws NotEnoughGasException, GasTooExpensiveException {
		// TODO Auto-generated method stub
		for(GasPump pump : pumps){
			
			if(pump.getGasType().equals(type)){
				
				System.out.println("Sell Gas Type : " + this.getGasType(type)+ " @ "+getPrice(type)+"/Litre");
				System.out.println("Remaing Gas In Amount : " + pump.getRemainingAmount());
				System.out.println("Purchasing Gas In Amount : " + amountInLiters);
				
				if(maxPricePerLiter < getPrice(type)){
					System.out.println("### CANNOT SELL GAS @ "+ maxPricePerLiter +"/Litre : TOO EXPENSIVE ####");
					this.setCanellationsOfGasExpensive(this.getCanellationsOfGasExpensive() + 1) ;
					throw new GasTooExpensiveException("Expensive");
				}
				else if(pump.getRemainingAmount() < amountInLiters){
					System.out.println("### CANNOT SELL : NOT ENOUGH GAS ####");
					this.setCanellationsOfGasNotAvailable(this.getCanellationsOfGasNotAvailable() + 1) ;
					throw new NotEnoughGasException("Not Available");
					
				}
				else {
					pump.pumpGas(amountInLiters);
					
					this.totalRevenue += amountInLiters ;
					
					System.out.println("**** GAS SOLD **** \n");
					break ;
				}
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
	public int getNumberOfSales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfCancellationsNoGas() {
		// TODO Auto-generated method stub
		return this.getCanellationsOfGasNotAvailable();
	}

	@Override
	public int getNumberOfCancellationsTooExpensive() {
		// TODO Auto-generated method stub
		return this.getCanellationsOfGasExpensive() ;
	}

	@Override
	public double getPrice(GasType type) {
		// TODO Auto-generated method stub
		return prices.get(type);
	}

	@Override
	public void setPrice(GasType type, double price) {
		// TODO Auto-generated method stub
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
}
