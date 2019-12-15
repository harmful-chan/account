package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.bean.Staff;

public class ActiveServer<T extends Staff> {
	private List<T> activeList;
	private T currentActive;
	public ActiveServer(){
		this.activeList = new ArrayList<T>();
	}
	public T getCurrent() {
		return this.currentActive;
	}
	public void setCurrent(T t) {
		this.currentActive = t;
	}
	public boolean isActive(T t) {
		Staff s = (Staff)t;
		List<Staff> ss = (List<Staff>)activeList;
		boolean flag = false;
		for (Staff staff : ss) {
			if(staff.getNumber().equals(s.getNumber())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void addActive(T t) {
		if(!isActive(t)) {
			this.activeList.add(t);	
		}
	}
	public List<T> getActiveList(){
		return this.activeList;
	}
	public void removeActive(T t) {
		this.activeList.remove(t);
	}
}
