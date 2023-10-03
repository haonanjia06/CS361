/**
 * 
 */
package mystack;

/**
 * @author haonan jia
 *
 */

// Stack using generics

// Complete the provided code

public class MyStack<T> {

	private class MyNode<T1> {
		T1 val;
		MyNode<T1> next;

		MyNode(T1 v, MyNode<T1> n) {
			val = v;
			next = n;
		}
	}

	private MyNode<T> theStack = null;

	/**
	 * 
	 */
	public MyStack() {
	}

	public T pop() {
		if ( theStack == null ) {
			return null;
		}
		T valueToReturn = theStack.val;
		theStack = theStack.next; 
		return valueToReturn;
	}

	public void push(T v) {
		MyNode<T> node = new MyNode<T>(v, theStack);
		if ( theStack == null) {
			theStack = node;
			return;
		}
		MyNode<T> newHead = node;
		theStack = newHead;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO To complete
		MyStack<Integer> myStack = new MyStack<Integer>();
		myStack.push(1);
		myStack.push(2);
		myStack.pop();
		myStack.push(5);
		
		// TODO To complete
		MyStack<Person> myPerson = new MyStack<Person>();
		Person p1 = new Person("Maksym", "Karunos");
		Person p2 = new Person("Christelle", "Scharff");
		myPerson.push(p1);
		myPerson.push(p2);
		myPerson.pop();

	}

}
