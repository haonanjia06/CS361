public class ParserDemo {

	public static void main(String[] args) {

		// TO COMPLETED
		// Change the path!
		TokenStream tStream = new TokenStream("C:\Users\Administrator\Desktop\prog1.kay");

		ConcreteSyntax cSyntax = new ConcreteSyntax(tStream);
		Program p = cSyntax.program();
		System.out.println(p.display());
		System.out.println("test");

	}
}