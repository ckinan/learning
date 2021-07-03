package modifiertypes.access.subpackage;

import modifiertypes.access.AccessControlModifiers;

public class ExternalClassFromDifferentPackage {

	public static void main(String[] args) {
		AccessControlModifiers obj = new AccessControlModifiers();
		System.out.println("Access control test from ExternalClassFromDifferentPackage");
		System.out.println("validate access AccessControlModifiers.publicVariable: " + obj.publicVariable);
		System.out.println("validate access AccessControlModifiers.nomodifierVariable: not allowed");
		System.out.println("validate access AccessControlModifiers.protectedVariable: not allowed");
		System.out.println("validate access AccessControlModifiers.privateVariable: not allowed");
	}

}
