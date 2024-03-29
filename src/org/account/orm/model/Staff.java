package org.account.orm.model;

public class Staff {
    private Integer id;
    private String number;
    private String firstName;
    private String lastName;
    private String city;
    private String province;
    private String country;
    private String zipCode;
    private String entryDate;
    private Account account;
    private Department department;
    private Role role;
	
	public Staff(){
		
	}
	
	public Staff(String number, String firstName, String lastName, String city, String province, String country, String zipCode, String entryDate) {
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.province = province;
		this.country = country;
		this.zipCode = zipCode;
		this.entryDate = entryDate;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", number=" + number + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", city=" + city + ", province=" + province + ", country=" + country + ", zipCode=" + zipCode
				+ ", entryDate=" + entryDate + ", account=0000000000, department=director, role=root];";
	}
	
	
}
