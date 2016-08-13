import main.java.net.bigpoint.assessment.gasstation.GasPump;
import main.java.net.bigpoint.assessment.gasstation.GasStation;
import main.java.net.bigpoint.assessment.gasstation.GasType;

public class MainProgram {
	
	public MainProgram() {
		// TODO Auto-generated constructor stub
	}
		
	public static void main(String[] args){
		System.out.println("Working fine");
		GasStationSimulator station1 = new GasStationSimulator();	
		
		GasPump dieselGasPump = new GasPump(GasType.DIESEL, 100);		
		GasPump regularGasPump = new GasPump(GasType.REGULAR, 300);
		GasPump superGasPump = new GasPump(GasType.SUPER, 500);
		
		station1.addGasPump(dieselGasPump);
		station1.addGasPump(regularGasPump);
		station1.addGasPump(superGasPump);
		
		// assign pumps to GasSation
		station1.setPrice(GasType.DIESEL, 60);
		station1.setPrice(GasType.REGULAR, 80);
		station1.setPrice(GasType.SUPER, 100);	
	
		// Start Selling Gas in Queue
		station1.startQueue(GasType.DIESEL, 40, 80);
		station1.startQueue(GasType.DIESEL, 30, 80);
		station1.startQueue(GasType.REGULAR, 120, 80);
		station1.startQueue(GasType.DIESEL, 50, 30);
		station1.startQueue(GasType.DIESEL, 60, 80);
		station1.startQueue(GasType.SUPER, 100, 50);
		station1.startQueue(GasType.SUPER, 70, 80);
		station1.startQueue(GasType.REGULAR, 200, 90);
		station1.startQueue(GasType.REGULAR, 120, 85);
		station1.startQueue(GasType.DIESEL, 50, 80);
		
		regularGasPump.acquireGas(80);
		
		station1.startQueue(GasType.SUPER, 200, 80);
		station1.startQueue(GasType.DIESEL, 200, 90);
		station1.startQueue(GasType.SUPER, 120, 85);
		station1.startQueue(GasType.SUPER, 100, 140);
		
		
		dieselGasPump.acquireGas(100);
		
		station1.startQueue(GasType.REGULAR, 160, 80);
		station1.startQueue(GasType.SUPER, 120, 190);
		station1.startQueue(GasType.SUPER, 40, 90);
		
		MainProgram mainProgram = new MainProgram();
		mainProgram.GenerateReport(station1);
	}
	
	public void GenerateReport(GasStation station){
		
		
		
		
		System.out.println("Gas Shortage " + station.getNumberOfCancellationsNoGas());
		System.out.println("Gas Deficit " + station.getNumberOfCancellationsTooExpensive());
		System.out.println("Pump Not Exist " + ((GasStationSimulator) station).getCanellationsOfPumpNotAvailable());
		System.out.println("Remaing Gas "+ station.getGasType(GasType.DIESEL) + " = " + station.getAvailableGas(GasType.DIESEL));
		System.out.println("Remaing Gas "+ station.getGasType(GasType.REGULAR) + " = " + station.getAvailableGas(GasType.REGULAR));
		System.out.println("Remaing Gas "+ station.getGasType(GasType.SUPER) + " = " + station.getAvailableGas(GasType.SUPER));
		System.out.println("Total Sales " + station.getNumberOfSales());
		System.out.println("Total Revenue " + station.getRevenue());
		
		for(int i=0; i<30; i++){
			System.out.print("----");
		}
	}
}
