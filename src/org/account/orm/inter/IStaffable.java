package org.account.orm.inter;

import org.account.orm.model.Staff;

public interface IStaffable {
	public Staff getStaff(String number);
	public int addStaff(String number, String firstName, String lastName, String city, String province, String country, String zipCode, String entryDate);
	public int alterStaff(String number, String firstName, String lastName, String city, String province, String country, String zipCode, String entryDate);
	public int removeStaff(String number);
}
