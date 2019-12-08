package org.account.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.account.orm.inter.IStaffable;
import org.account.orm.model.Staff;
import org.account.util.JDBCUtil;

public class CompanyManagement extends AllocateDepartmentAccountManagement implements IStaffable {

	@Override
	public Staff getStaff(String number) {

		Staff ret = null;
		try {
			ResultSet ss = JDBCUtil.getStatement().executeQuery("select * from cmp_staff where number='"+number+"';");
			if(ss.next()) {
				Staff staff = new Staff();
				ret = staff;
			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}



	@Override
	public int removeStaff(String number) {
		try {
			return JDBCUtil.getStatement().executeUpdate("delete from cmp_staff where number='"+number+"';");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}



	@Override
	public int alterStaff(String number, String firstName, String lastName, String city, String province,
			String country, String zipCode, String entryDate) {
		
		try {
			String sql = "update cmp_staff set number='"+number+"', firstName='"+firstName+"', lastName='"+
				lastName+"', city='"+city+"', province='"+province+"', country='"+
					country+"', zipCode='"+zipCode+"', entryDate='"+entryDate+"';";
			return JDBCUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}



	@Override
	public int addStaff(String number, String firstName, String lastName, String city, String province, String country,
			String zipCode, String entryDate) {
		try {
			String sql = "insert into cmp_staff(number, firstName, lastName, city, province, country, zipCode, entryDate) " +
					"values('"+number+"', '"+firstName+"', '"+lastName+"', '"+city+"', '"+province+"', '"+country+"', '"+zipCode+"', '"+entryDate+"')";
			return JDBCUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}


}
