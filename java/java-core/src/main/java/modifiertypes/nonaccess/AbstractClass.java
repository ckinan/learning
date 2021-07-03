package modifiertypes.nonaccess;

public abstract class AbstractClass {

	abstract void abstractPrint();
	
	protected void protectedPrint(){
		System.out.println("From protectedPrint()");
	}
	
	final void finalPrint(){
		System.out.println("From finalPrint()");
	}
}
