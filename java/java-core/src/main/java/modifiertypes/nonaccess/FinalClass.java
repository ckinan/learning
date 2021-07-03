package modifiertypes.nonaccess;

public final class FinalClass extends AbstractClass{

	public static void main(String[] args) {
		System.out.println("Main from FinalClass");
		FinalClass obj = new FinalClass();
		obj.abstractPrint();
	}

	@Override
	void abstractPrint() {
		// TODO Auto-generated method stub
		System.out.println("Override protectedPrint() in FinalClass");
		protectedPrint();
	}
	
}
