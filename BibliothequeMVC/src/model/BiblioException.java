/**
 * 
 */
package model;

/**
 * @author JulA2973
 * @version 1.1
 */
public class BiblioException extends Exception {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BiblioException(String message, Throwable cause) {
		super(message,cause);
	}
	public BiblioException(String message) {
		super(message);
	}
	public BiblioException() {
		super();
	}
	/*public static void main(String[] args) {
		BiblioException e1= new BiblioException("Biblio App issue");
		System.out.println(e1);
		BiblioException e2 = new BiblioException();
		System.out.println(e2);
	}*/
}
