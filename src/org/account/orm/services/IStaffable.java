package org.account.orm.services;

public interface IStaffable {
	public String getStaff(String number);
	public int addStaff(String number, String firstName, String lastName, String city, String province, String country, String zipCode, String entryDate, String department, String role);
	public int alterStaff(String number, String firstName, String lastName, String city, String province, String country, String zipCode, String entryDate, String department, String role);
	public int removeStaff(String number);
}
