package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

public class ActiveServer<T> {
	private List<T> activeList = new ArrayList<T>();
	private T currentActive;

	public T getCurrent() {
		return this.currentActive;
	}
	public void setCurrent(T t) {
		this.currentActive = t;
	}
	public boolean isActive(T t) {
		
		return this.activeList.contains(t);
	}
	
	public void addActive(T t) {
		this.activeList.add(t);
	}
	public List<T> getActiveList(){
		return this.activeList;
	}
	public void removeActive(T t) {
		this.activeList.remove(t);
	}
}
