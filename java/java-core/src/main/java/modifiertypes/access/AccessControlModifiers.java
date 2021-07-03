package modifiertypes.access;

public class AccessControlModifiers {

	// public, protected, no-modifier, private
	public String publicVariable = "publicVariable (allowed)";
	protected String protectedVariable = "protectedVariable (allowed)";
	String nomodifierVariable = "nomodifierVariable (allowed)";
	private String privateVariable = "privateVariable (allowed)";

	public static void main(String[] args) {
		SomePrivateClass obj = new AccessControlModifiers().new SomePrivateClass();
		obj.publicPrint();
		obj.protectedPrint();
		obj.nomodifierPrint();
		obj.privatePrint();
	}

	// no-modifier class
	class SomePrivateClass {
		public void publicPrint(){
			realPrint();
		}
		protected void protectedPrint(){
			realPrint();
		}
		void nomodifierPrint(){
			realPrint();
		}
		private void privatePrint(){
			realPrint();
		}
		private void realPrint(){
			System.out.println("I am in SomePrivateClass: " + publicVariable);
			System.out.println("I am in SomePrivateClass: " + protectedVariable);
			System.out.println("I am in SomePrivateClass: " + nomodifierVariable);
			System.out.println("I am in SomePrivateClass: " + privateVariable);
			System.out.println("----");
		}
	}

}
