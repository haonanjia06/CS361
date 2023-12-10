// TokenStream.java

// Implementation of the Scanner for KAY

// This code DOES NOT implement a scanner for KAY yet. You have to complete
// the code and also make sure it implements a scanner for KAY - not something
// else, 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TokenStream {

	// READ THE COMPLETE FILE FIRST
	// You will need to adapt it to KAY, NOT JAY

	// Instance variables 
	private boolean isEof = false; // is end of file
	private char nextChar = ' '; // next character in input stream
	private BufferedReader input;

	// This function was added to make the demo file work
	public boolean isEoFile() {
		return isEof;
	}

	// Constructor
	// Pass a filename for the program text as a source for the TokenStream.
	public TokenStream(String fileName) {
		try {
			input = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			// System.exit(1); // Removed to allow ScannerDemo to continue
			// running after the input file is not found.
			isEof = true;
		}
	}

	public Token nextToken() { // Main function of the scanner
								// Return next token with its type and value.
		Token t = new Token();
		t.setType("Other"); // For now it is Other. You will update it in the code
		t.setValue("");

		// First check for whitespaces and bypass them
		skipWhiteSpace();

		// Then check for a comment, and bypass it
		// but remember that / may also be a division operator.
		while (nextChar == '/') {
			// The use of while (instead of if) prevents the 2nd line to be printed when
			// there are two comment lines in a row.
			nextChar = readChar();
			if (nextChar == '/') { // If / is followed by another /
				// skip rest of line - it's a comment.
				// TODO TO BE COMPLETED
				//if this fails check with !eof refer to skipWhiteSpace()
				while (!isEndOfLine(nextChar)){
					nextChar = readChar();
				}
				return nextToken();
			} else { 
				// A slash followed by anything else must be an operator.
				t.setValue("/");
				t.setType("Operator");
				return t;
			}
		}

		// Then check for an operator; this part of the code should recover 2-character
		// operators as well as 1-character ones.
		if (isOperator(nextChar)) {
			t.setType("Operator");
			t.setValue(t.getValue() + nextChar);
			switch(nextChar) {
			// TODO TO BE COMPLETED OR CHANGED WHERE NEEDED TO IMPLEMENT KAY
			//changed to match operators, only first character was there before
			case ':':
			//:=
			nextChar = readChar();
				if(nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				}
				else {
					t.setType("Other");
				}
			return t;
			case '<':
				// <=
				nextChar = readChar();
				if(nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				}
				return t;
			case '>':
				// >=
				nextChar = readChar();
				if(nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				}
				return t;
			case '=':
				// ==
				nextChar = readChar();
				if (nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
				}
				return t;
			//case '!':
			case '!':
				// !=
				nextChar = readChar();
				if (nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				}
				return t;
			case '|':
				// Look for ||
				nextChar = readChar();
				if (nextChar == '|') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
				}
				return t;
			case '&':
				// Look for &&
				nextChar = readChar();
				if (nextChar == '&') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
				}
				return t;
			default: // all other operators
				nextChar = readChar();
				return t;
		}
	}

		// Then check for a separator
		//separator can only be followed by another separator or space
		if (isSeparator(nextChar)) {
			// TODO TO BE COMPLETED
 			t.setValue(String.valueOf(nextChar));
			t.setType("Separator");
 			nextChar = readChar();
			 if(!isSeparator(nextChar)){
				return t;          
			}
			if (isEndOfToken(nextChar)) {// If token is valid, returns.
                return t;
            }
			} 
		 

		// Then check for an identifier, keyword, or literal (True or False).
		if (isLetter(nextChar)) {
			// Set to an identifier
			 t.setType("Identifier");
			while ((isLetter(nextChar) || isDigit(nextChar))) {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
			// Now see if this is a keyword
			if (isKeyword(t.getValue())) {
				t.setType("Keyword");
			} else if (t.getValue().equals("True") || t.getValue().equals("False")) {
				t.setType("Literal");
			}
			if (isEndOfToken(nextChar)) { // If token is valid, returns.
				return t;
			}
		}

		if (isDigit(nextChar)) { // check for integer literals
			t.setType("Literal");
			while (isDigit(nextChar)) {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
			// An Integer-Literal is to be only followed by a space,
			// an operator, or a separator.
			if (isEndOfToken(nextChar)) {// If token is valid, returns.
				return t;
			}
		}

		t.setType("Other");
		
		if (isEof) {
			return t;
		}

		// Makes sure that the whole unknown token (Type: Other) is printed.
		while (!isEndOfToken(nextChar)) {
			t.setValue(t.getValue() + nextChar);
			nextChar = readChar();
		}
		
		
		// Finally check for whitespaces and bypass them
		skipWhiteSpace();
		
		return t;
	}
	
	private char readChar() {
		int i = 0;
		if (isEof){
			return (char) 0;
		}
		System.out.flush();
		try {
			i = input.read();
		} catch (IOException e) {
			System.exit(-1);
		}
		if (i == -1) {
			isEof = true;
			return (char) 0;
		}
		return (char) i;
	}

	private boolean isKeyword(String s) {
		// TODO TO BE COMPLETED
		//should be good
		if(s.equals("while") || s.equals("if")|| s.equals("bool") || s.equals("else") || s.equals("integer")	|| s.equals("main")){
			return true;
		}else {
			return false;
		}
	}

	private boolean isSeparator(char c) {
		// TODO TO BE COMPLETED
		if(c == '(' || c == ')' || c == '{' || c == '}' || c == ';' || c == ','){
			return true;
		}else{
			return false;
		}
	}

	private boolean isOperator(char c) {
		// Checks for characters that start operators
		// TODO TO BE COMPLETED
		if(c == '|'||c == '&'||c == '!'||c == '='||c == '<'||c == '>'||c == '/'||c == '*'||c == '-'||c == '+'||c == ':'){
			return true;
		} else {
			return false;
		}
	}

	private boolean isLetter(char c) {
		if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'){
			return true;
		}
		else{ 
			return false;
		}
	}

	private boolean isDigit(char c) {
		// TODO TO BE COMPLETED
		if(c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'||c=='0'){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isWhiteSpace(char c) {
		if (c == ' ' || c == '\t' || c == '\r' || c == '\n' || c == '\f'){
			return true;
		}
		else{
			return false;
		}
	}

	private boolean isEndOfLine(char c) {
		if (c == '\r' || c == '\n' || c == '\f'){
			return true;
		}
		else {
			return false;
		}
	}

	private boolean isEndOfToken(char c) { // Is the value a seperate token?
		if (isWhiteSpace(nextChar) || isOperator(nextChar) || isSeparator(nextChar) || isEof){
			return true;
		}
		else {
			return false;
		}
	}

	private void skipWhiteSpace() {
		// check for whitespaces, and bypass them
		while (!isEof && isWhiteSpace(nextChar)) {
			nextChar = readChar();
		}
	}

	public boolean isEndofFile() {
		return isEof;
	}
}