package org.account.orm.services;

import org.account.orm.model.SimpleStaff;

public interface IActiveStaffable {
	public SimpleStaff getCurrentActiveStaff();
	public int setCurrentActiveStaff();
	public boolean isActiveStaff(String number);
	public String[] getActiveStaffs();
	public int addActiveStaff(String number);
	public int removeActiveStaff(String number);
}
