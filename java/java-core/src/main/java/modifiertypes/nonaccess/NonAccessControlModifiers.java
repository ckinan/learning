package modifiertypes.nonaccess;

public class NonAccessControlModifiers extends AbstractClass {

	// variables: static, final, synchronized
	// classes: final, abstract
	// methods: static, final, abstract, synchronized
	// --> volatile, transient
	static int staticCounter = 0;
	final int finalCounter = -1;

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			NonAccessControlModifiers obj = new NonAccessControlModifiers();
			System.out.println("staticCounter = " + obj.staticCounter + ", " + NonAccessControlModifiers.staticCounter);
			NonAccessControlModifiers.addStaticCounter();
		}
		NonAccessControlModifiers obj2 = new NonAccessControlModifiers();
		System.out.println("finalCounter = " + obj2.finalCounter);
		// finalCounter++; --> cannot be changed
		obj2.protectedPrint();
		obj2.abstractPrint();
		obj2.accessFinalPrint();
	}

	static void addStaticCounter() {
		staticCounter++;
	}

	@Override
	void abstractPrint() {
		System.out.println("abstractPrint() implementation");
	}

	protected void protectedPrint() {
		System.out.println("Override protectedPrint()");
		super.protectedPrint();
	}

	/*
	 * void finalPrint(){ // Cannot be overridden
	 * 
	 * }
	 */
	
	void accessFinalPrint(){
		finalPrint();
	}
}
