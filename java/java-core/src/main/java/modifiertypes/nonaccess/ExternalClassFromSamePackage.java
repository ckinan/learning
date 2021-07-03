package modifiertypes.nonaccess;

public class ExternalClassFromSamePackage 
	extends NonAccessControlModifiers 
	// extends FinalClass // Cannot subclass FinalClass
	{

	public static void main(String[] args) {
		NonAccessControlModifiers obj = new NonAccessControlModifiers();
		System.out.println("NonAccessControlModifiers.staticCounter = " + NonAccessControlModifiers.staticCounter);
		System.out.println("new NonAccessControlModifiers().staticCounter = " + obj.staticCounter);
		System.out.println("new NonAccessControlModifiers().finalCounter = " + obj.finalCounter);
		
		ExternalClassFromSamePackage obj2 = new ExternalClassFromSamePackage();
		obj2.accessMethod();
	}
	
	void accessMethod(){
		abstractPrint();
		accessFinalPrint();
		finalPrint();
	}

}
