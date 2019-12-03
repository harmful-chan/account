package org.account.orm.services;


import java.util.List;

import org.account.orm.model.SimpleStaff;

public interface IActiveStaffable {
	public SimpleStaff getCurrentActiveStaff();
	public int setCurrentActiveStaff(String number);
	public boolean isActiveStaff(String number);
	public List<String> getActiveStaffs();
	public int addActiveStaff(String number);
	public int removeActiveStaff(String number);
}
