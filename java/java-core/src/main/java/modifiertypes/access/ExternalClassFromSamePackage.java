package modifiertypes.access;

public class ExternalClassFromSamePackage {

	public static void main(String[] args) {
		AccessControlModifiers obj = new AccessControlModifiers();
		System.out.println("Access control test from ExternalClassFromSamePackage");
		System.out.println("validate access AccessControlModifiers.publicVariable: " + obj.publicVariable);
		System.out.println("validate access AccessControlModifiers.nomodifierVariable: " + obj.nomodifierVariable);
		System.out.println("validate access AccessControlModifiers.protectedVariable: " + obj.protectedVariable);
		System.out.println("validate access AccessControlModifiers.privateVariable: not allowed");
	}
	
}
