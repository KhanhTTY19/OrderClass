package person3;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner inp = new Scanner(System.in);
		String s = new String();
		boolean valid = false;
		while(!valid) {
			try {
				int x;
				x = inp.nextInt();
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid inp");
				inp.next();
			}	
		}	
		
		
		String fileName = "/media/khanhtty/551473877464395A/oop-pj-152627/person3/src/person3/order.csv";
		System.out.print("11");
		writeCSV(fileName);
		
		
	}
	
	public static void writeCSV(String fileName) {
		Map<String, Integer> x = new HashMap<String, Integer>();
		x.put("1", 10);
		x.put("2", 1);
		Order ord1 = new Order("idpro4", x );
		FileWriter outFile = null;
		try {
			outFile = new FileWriter(fileName, true);
			
			for (Map.Entry<String, Integer> i : ord1.getProductList().entrySet())
			{	
				outFile.append(String.join(",", ord1.getID(),i.getKey(), i.getValue().toString(), "\n"));
				
			}
			
			outFile.close();
		}
		catch (Exception e) {
			System.out.print("Error");		
		}
	}
}
