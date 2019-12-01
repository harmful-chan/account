package org.account.orm;

import org.account.orm.services.IRoleable;
import org.hibernate.Session;

import com.oracle.webservices.internal.api.message.PropertySet.Property;

public class AllocateManagement implements IRoleable {

	
	protected Session session;
	
	public AllocateManagement(){
		this.session = HibernateUtil.getSession();
	}
	@Override
	public String getRole(String number) {
		return null;
	}
	@Override
	public int Award(String number) {
		// TODO Auto-generated method stub
		return 0;
	}
}
