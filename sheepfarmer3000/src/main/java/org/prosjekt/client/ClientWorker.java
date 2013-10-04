package org.prosjekt.client;

import org.prosjekt.helperclasses.NetPackage;
import org.prosjekt.helperclasses.NetPackageEnum;

public class ClientWorker {

	
	public static NetPackage unpackNetPackage(NetPackage<T> pkg) {
		
		if (pkg.getOperation() == NetPackageEnum.SUCCESS) {
			System.out.println('Success');
		} else if (pkg.getOperation() == NetPackageEnum.LOGINFAILED) {
			System.out.println('Login Failed');
		} else if (pkg.getOperation() == NetPackageEnum.LOGINSUCCESSFUL) {
			System.out.println('Login Successful');
		}
		
		return null;
		
	}
}
