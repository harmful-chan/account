package org.account.web.action;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.Account;
import org.account.orm.model.Department;
import org.account.orm.model.Role;
import org.account.orm.model.Staff;
import org.account.web.model.TableContext;

import com.opensymphony.xwork2.ModelDriven;

public class TableAction extends ActionBase implements ModelDriven<TableContext>{

	private static final long serialVersionUID = 1L;

	private TableContext tableContext = new TableContext();
	List<TableContext> tables;
	@Override
	public TableContext getModel() {
		return this.tableContext;
	}
	
	public TableAction(){
		this.tables = new ArrayList<TableContext>();
	}
	public String getInterior() {
		Department  department = staffInfo.getDepartment(active.getCurrent());
		List<Account>  accounts = secret.getShare(department.getName());
		
		for (Account account : accounts) {
			TableContext t = new TableContext();
			t.setAccountNumber(account.getNumber());
			t.setDeeppwd(encryption.encode(account.getPassword(), account.getSalf()));
			t.setOwnerNumber("Share");
			t.setOwnerDepartment(department.getName());
			t.setOwnerRole("Share");
			t.setExplain(account.getExplain());
			t.setValid(secret.isValid(account));			
			tables.add(t);
		}
		session.put("table_info", tables);
		return SUCCESS;
	}
	
	public String addInterior() {
		return getInterior();
	}
	
	public String alterInterior() {
		Department  department = staffInfo.getDepartment(active.getCurrent());
		
		//密码判定
		String deeppwd = tableContext.getDeeppwd();
		String deep = encryption.decode(deeppwd);
		String salf = deep.substring(deep.length()-17, deep.length());
		String password = deep.replaceAll(salf, "");
		
		//Account信息
		Account account = new Account();
		account.setNumber(tableContext.getAccountNumber());
		account.setPassword(password);
		account.setExplain(tableContext.getExplain());
		
		//Account 存在 修改
		//Account 不存在 添加
		secret.addShare(account, department.getName());
		return getInterior();
	}
	
	public String removeInterior() {
		Department  department = staffInfo.getDepartment(active.getCurrent());
		secret.removeShare(tableContext.getAccountNumber(), department.getName());
		return getInterior();
	}
	
	public String getSuper() {
		
		List<Staff> holdStaffs = staffInfo.getHold();
		for (Staff staff : holdStaffs) {
			Account a = staff.getAccount();
			TableContext t = new TableContext();
			t.setAccountNumber(a.getNumber());
			t.setDeeppwd(encryption.encode(a.getPassword(), a.getSalf()));
			t.setExplain(a.getExplain());
			t.setOwnerNumber(staff.getNumber());
			t.setOwnerRole(staff.getRole().getName());
			t.setOwnerDepartment(staff.getDepartment().getName());
			t.setValid(secret.isValid(a));
			tables.add(t);
		}
		session.put("table_info", tables);
		return SUCCESS;
	}
	
	public String addSuper() {

		return getSuper();
	}

	public String alterSuper() {
		//密码判定
		String deeppwd = tableContext.getDeeppwd();
		String deep = encryption.decode(deeppwd);
		String salf = deep.substring(deep.length()-17, deep.length());
		String password = deep.replaceAll(salf, "");
		
		//Account信息
		String ownerNumber = this.tableContext.getOwnerNumber();
		Account newAccount = new Account();
		newAccount.setNumber(this.tableContext.getAccountNumber());
		newAccount.setPassword(encryption.decode(this.tableContext.getDeeppwd()));
		newAccount.setExplain(this.tableContext.getExplain());
		staffInfo.addHold(ownerNumber, newAccount);

		return getSuper();
	}
//	
//	
//	public String removeSuper() {
//		return getSuper();
//	}
	
}
