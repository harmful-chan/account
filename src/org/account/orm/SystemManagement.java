package org.account.orm;

import java.util.*;

import org.account.orm.model.SimpleStaff;
import org.account.orm.services.IActiveStaffable;

public class SystemManagement extends CompanyManagement implements IActiveStaffable {

	private Map<String, SimpleStaff> activeStaff = new HashMap<String, SimpleStaff>();
	private SimpleStaff currentActiveStaff;
	@Override
	public SimpleStaff getCurrentActiveStaff() {
		return this.currentActiveStaff;
	}

	@Override
	public int setCurrentActiveStaff(String number) {
		String r = getRoleName(number);
		String a = getAccount(number);
		String d = getDepartmentName(number);
		int[] ps = getPermission(r);
		
		if( r != null && a != null && d != null && ps != null ) {
			this.currentActiveStaff = new SimpleStaff(number, a, d, r ,ps);	
			
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public boolean isActiveStaff(String number) {
		return this.activeStaff.get(number) != null;
	}

	@Override
	public int addActiveStaff(String number) {
		String r = getRoleName(number);
		String a = getAccount(number);
		String d = getDepartmentName(number);
		int[] ps = getPermission(r);
		
		if( r != null && a != null && d != null && ps != null ) {
			SimpleStaff ss = new SimpleStaff(number, a, d, r ,ps);	
			this.activeStaff.put(number, ss);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public int removeActiveStaff(String number) {
		return this.activeStaff.remove(number) != null ? 1 : -1 ;
	}

	@Override
	public List<String> getActiveStaffs() {
		
		List<String> ret = new ArrayList<String>();
		
		for (String string : this.activeStaff.keySet()) {
			ret.add(string);
		}
		return ret;
	}


}
