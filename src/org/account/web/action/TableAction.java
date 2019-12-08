package org.account.web.action;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.Account;
import org.account.web.viewmodel.Table;

import com.opensymphony.xwork2.ModelDriven;

public class TableAction extends ActionBase implements ModelDriven<Table>{

	private static final long serialVersionUID = 1L;

	private Table table = new Table();
	
	@Override
	public Table getModel() {
		return this.table;
	}
	
	
	public String getInterior() {
		String d = arbitrate.getCurrentActiveStaff().getDepartment();
		String r = arbitrate.getCurrentActiveStaff().getRole();
		String n = arbitrate.getCurrentActiveStaff().getNumber();
		List<Account> as = arbitrate.getDepartmentAccounts(d);
		List<Table> tables = new ArrayList<Table>();
		for (Account account : as) {
			Table t = new Table();
			t.setAccount(account.getAccount());
			t.setDeeppwd(arbitrate.encrypt(account.getPassword(), account.getSalf()));
			t.setDepartment(d);
			t.setRole("");
			t.setExplain(account.getExplain());
			t.setValid(arbitrate.isValid(account.getAccount()));			
			t.setNo("");	
			tables.add(t);
		}
		
		return SUCESS;
	}
	public String addInterior() {
		String src = arbitrate.decode(table.getDeeppwd());
		String password = src.substring(0, src.length()-8);
		String salf = src.substring(src.length()-8, src.length()-1);
		arbitrate.addDepartmentAccount(arbitrate.getCurrentActiveStaff().getDepartment(), table.getAccount(), password, salf, table.getExplain());
		
		return getInterior();
	}
	
	public String alterInterior() {
		String src = arbitrate.decode(table.getDeeppwd());
		String password = src.substring(0, src.length()-8);
		String salf = src.substring(src.length()-8, src.length()-1);
		arbitrate.alterDepartmentAccount(arbitrate.getCurrentActiveStaff().getDepartment(), table.getAccount(), password, salf, table.getExplain());
		
		return getInterior();
	}
	
	public String removeInterior() {
		arbitrate.removeDepartmentAccount(arbitrate.getCurrentActiveStaff().getDepartment(), table.getAccount());
		return getInterior();
	}
	
	public String getSuper() {
		arbitrate.getDepartmentName(table.getNumber());
		List<Account> accounts = arbitrate.getDepartmentAccounts(arbitrate.getDepartmentName(table.getNumber()));
		List<Table> tables = new ArrayList<Table>();
 		for (Account account : accounts) {
 			List<String> nos = arbitrate.getAccountUsers(account.getAccount());
 			if( nos != null) {
 				for (String no : nos) {
 					Table t = new Table();
 					String d = arbitrate.getDepartmentName(no);
 					String r = arbitrate.getRoleName(no);
 					t.setAccount(account.getAccount());
 					t.setDeeppwd(arbitrate.encrypt(account.getPassword(), account.getSalf()));
 					t.setDepartment(d);
 					t.setRole(r);
 					t.setExplain(account.getExplain());
 					t.setValid(arbitrate.isValid(account.getAccount()));			
 					t.setNo(no);	
 					tables.add(t);
 				}	
 			}else {
 				Table t = new Table();
				t.setAccount(account.getAccount());
				t.setDeeppwd(arbitrate.encrypt(account.getPassword(), account.getSalf()));
				t.setDepartment(arbitrate.getCurrentActiveStaff().getNumber());
				t.setRole("");
				t.setExplain(account.getExplain());
				t.setValid(arbitrate.isValid(account.getAccount()));			
				t.setNo(arbitrate.getCurrentActiveStaff().getDepartment());	
				tables.add(t); 				
 			}
 			

		}
 		session.put("table_info", tables);
 		return SUCCESS;
	}
	
	public String addSuper() {
		this.addInterior();
		return getSuper();
	}

	public String alterSuper() {
		String src = arbitrate.decode(table.getDeeppwd());
		String password = src.substring(0, src.length()-8);
		String salf = src.substring(src.length()-8, src.length()-1);
		arbitrate.alterDepartmentAccount(table.getDepartment(), table.getAccount(), password, salf, table.getExplain());
		
		return getSuper();
	}
	
	
	public String removeSuper() {
		arbitrate.removeDepartmentAccount(table.getDepartment(), table.getAccount());
		return getSuper();
	}
	
}
