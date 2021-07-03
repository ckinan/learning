package modifiertypes.access.subpackage;

import modifiertypes.access.AccessControlModifiers;

public class SubClassFromDifferentPackage extends AccessControlModifiers{

	public static void main(String[] args) {
		SubClassFromDifferentPackage obj = new SubClassFromDifferentPackage();
		obj.print();
	}
	
	private void print(){
		System.out.println("Access control test from ExternalClassFromSamePackage");
		System.out.println("validate access AccessControlModifiers.publicVariable: " + publicVariable);
		System.out.println("validate access AccessControlModifiers.nomodifierVariable: not allowed");
		System.out.println("validate access AccessControlModifiers.protectedVariable: " + protectedVariable);
		System.out.println("validate access AccessControlModifiers.privateVariable: not allowed");
	}

}
