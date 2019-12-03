package org.account.orm.services;

import java.util.List;

public interface IDepartmentable {
	public List<String> getDepartmentNames();
	public String getDepartmentName(String number);
}
