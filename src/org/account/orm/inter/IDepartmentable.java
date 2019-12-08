package org.account.orm.inter;

import java.util.List;

public interface IDepartmentable {
	public List<String> getDepartmentNames();
	public String getDepartmentName(String number);
}
