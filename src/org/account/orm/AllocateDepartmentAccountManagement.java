package org.account.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.account.orm.model.Account;
import org.account.orm.services.IAccountable;
import org.account.orm.services.IDepAccLinkagetable;
import org.account.util.JDBCUtil;

public class AllocateDepartmentAccountManagement extends AllocateManagement implements IDepAccLinkagetable, IAccountable{
	
	private static final int TIME_OUT = 30;
	
	public AllocateDepartmentAccountManagement() {
		super();
	}

	@Override
	public List<Account> getDepartmentAccounts(String department) {

		List<Account> ret = new ArrayList<Account>();
		try {
			String sql = "select * from sys_account where id in " +
				 	"(select account_id from cmp_account_department where department_id in " +
				 		"(select id from cmp_department where name='"+department+"'));";
	
			ResultSet accounds = JDBCUtil.getStatement().executeQuery(sql);
			while(accounds.next()) {
				Account a = new Account(accounds.getString("account"), 
						accounds.getString("password"), 
						accounds.getString("salf"), 
						accounds.getString("explain"));
				ret.add(a);
			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	@Override
	public int addDepartmentAccount(String department, String account, String password, String salf, String explain) {
		try {
			if( 0 < JDBCUtil.getStatement().executeUpdate("insert into sys_account(account, password, salf, explain) values('"+account+"', '"+password+"', '"+salf+"', '"+explain+"');")) {
				ResultSet dr = JDBCUtil.getStatement().executeQuery("select id from cmp_department where name='"+department+"';");
				ResultSet ar = JDBCUtil.getStatement().executeQuery("select id from sys_account where account='"+account+"';");
				int dId = 0, aId = 0;
				if(ar.next()) 
					aId = ar.getInt("id");
				if(dr.next()) 
					dId = dr.getInt("id");
				
				return JDBCUtil.getStatement().executeUpdate("insert into cmp_account_department(account_id, department_id) values('"+aId+"', '"+dId+"');");
			}
			return -1;
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}

	
	@Override
	public int alterDepartmentAccount(String department, String account, String password, String salf, String explain) {
		try {
			
			ResultSet ar = JDBCUtil.getStatement().executeQuery("select id from sys_account where account='"+account+"';");
			int aId = -1;
			if(ar.next()) 
				aId = ar.getInt("id");
			return JDBCUtil.getStatement().executeUpdate("update sys_account set password='"+password+"', salf='"+salf+"', explain='"+explain+"' where id='"+aId+"';");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}

	@Override
	public int removeDepartmentAccount(String department, String account) {
		try {
			ResultSet dr = JDBCUtil.getStatement().executeQuery("select id from cmp_department where name='"+department+"';");
			ResultSet ar = JDBCUtil.getStatement().executeQuery("select id from sys_account where account='"+account+"';");
			int dId = 0, aId = 0;
			if(ar.next()) 
				aId = ar.getInt("id");
			if(dr.next()) 
				dId = dr.getInt("id");
			return JDBCUtil.getStatement().executeUpdate("delete from cmp_account_department where account_id='"+aId+"' and department_id='"+dId+"';");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
	}

	@Override
	public String getAccountPassword(String account) {
		String ret = "";
		try {
			ResultSet ar = JDBCUtil.getStatement().executeQuery("select password from sys_account where account='"+account+"';");
			if(ar.next()) 
				ret = ar.getString("password");
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	@Override
	public List<String> getAccountUsers(String account) {
		String sql = "select number from cmp_staff where account_id in (select id from sys_account where account='"+account+"');";
		List<String> ret = new ArrayList<String>();
		try {
			ResultSet ar = JDBCUtil.getStatement().executeQuery(sql);
			while(ar.next()) {
				ret.add(ar.getString("number"));
			}
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	@Override
	public int addAccount(String account, String password, String salf, String explain) {
		
		int ret = -1;
		try {
			ret = JDBCUtil.getStatement().executeUpdate("insert into sys_account(account, password, salf, explain) values('"+account+"', '"+password+"', '"+salf+"', '"+explain+"');");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	@Override
	public int removeAccount(String account) {
		int ret = -1;
		try {
			ret = JDBCUtil.getStatement().executeUpdate("delete from sys_account where account='"+account+"';");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	private int compareDate(String s1, String s2) {
		return Integer.parseInt(s1) - Integer.parseInt(s2);
	}
	
	@Override
	public boolean isValid(String account) {
		boolean flag = false;
		try {
			ResultSet ar = JDBCUtil.getStatement().executeQuery("select salf from sys_account where account='"+account+"';");
			if(ar.next()) {
				String salf = ar.getString("salf");
				String currSalf = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
				flag = compareDate(currSalf, salf) < TIME_OUT;
			}
		} catch (SQLException e) {
			return false;
		}finally {
			JDBCUtil.close();
		}
		return flag;
	}

	@Override
	public int updateValid(String account) {
		int ret = -1;
		try {
			String currSalf = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
			ret = JDBCUtil.getStatement().executeUpdate("update sys_account set salf='"+currSalf+"' where account='"+account+"';");
		} catch (SQLException e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}

	@Override
	public String getSalt(String account) {
		String ret = "";
		try {
			ResultSet ar = JDBCUtil.getStatement().executeQuery("select salf from sys_account where account='"+account+"';");
			if(ar.next()) 
				ret = ar.getString("salf");
		} catch (SQLException e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return ret;
	}
	
}
 