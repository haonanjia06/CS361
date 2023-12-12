/**
 * @author scharffc
 * 
 */
public class ScannerDemo{

	// Oh! This needs to be changed !
	private static String file1 = "C:\Users\Administrator\Desktop\prog1.kay";
	private static int counter = 1;

	public static void main(String args[]) {
		TokenStream ts = new TokenStream(file1);
		Token tk;
		System.out.println(file1);
		while (!ts.isEndofFile()) {
			tk = ts.nextToken();
			// TODO TO BE COMPLETED
			// tk is not defined on the line below
			 System.out.println("Token " + counter++ + " - Type: " + tk.getType() + " - Value: " + tk.getValue());
		}
	}
}
