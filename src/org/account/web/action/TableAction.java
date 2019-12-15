package org.account.web.action;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.bean.*;
import org.account.web.bean.TableContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

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
	
	
	
	/**
	 * ��tables��Ӳ��������˺�
	 * @param departmentName
	 * @throws Exception 
	 */
	private void joinInterior(Department department) throws Exception {
		List<Account>  accounts = info.getShareAccountList(department.getName());
		
		for (Account account : accounts) {
			TableContext t = new TableContext();
			t.setAccountNumber(account.getNumber());
			t.setDeeppwd(secret.encode(account.getPassword(), account.getSalf()));
			t.setOwnerNumber("---");
			t.setOwnerRole("---");
			t.setOwnerDepartment(department.getExplain());
			t.setExplain(account.getExplain());
			t.setValid("");			
			tables.add(t);
		}
		
	}
	
	@SkipValidation
	public String getInterior() throws Exception {
		Staff activeStaff = active.getCurrent();
		joinInterior(activeStaff.getDepartment());
		session.put("table_info", tables);
		flashUrl(activeStaff);
		return SUCCESS;
	}
	
	
	public String alterInterior() throws Exception {
		Department  department = active.getCurrent().getDepartment();
		//�������
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//����Account
		Account account = new Account();
		account.setNumber(tableContext.getAccountNumber());
		account.setPassword(password);
		account.setSalf(salf);
		account.setExplain(tableContext.getExplain());
		
		//Account ���� �޸�
		//Account ������ ���
		info.addShareAccount(account, department.getName());
		return getInterior();
	}
	
	
	public String removeInterior() throws Exception {
		Staff activeStaff = active.getCurrent();
		info.removeShareAccount(tableContext.getAccountNumber(), activeStaff.getDepartment().getName());
		return getInterior();
	}
	
	/**
	 * �ѳ����˺�Ա����Ϣ��ӽ�tables
	 * @throws Exception 
	 */
	private void joinSuper() throws Exception {
		List<Staff> holdStaffs = info.getHoldStaffList();
		for (Staff staff : holdStaffs) {
			Account a = staff.getAccount();
			TableContext t = new TableContext();
			t.setAccountNumber(a.getNumber());
			t.setDeeppwd(secret.encode(a.getPassword(), a.getSalf()));
			t.setExplain(a.getExplain());
			t.setOwnerNumber(staff.getNumber());
			t.setOwnerRole(staff.getRole().getExplain());
			t.setOwnerDepartment(staff.getDepartment().getExplain());
			t.setValid(secret.isValid(a) ? "��Ч" : "��Ч");
			tables.add(t);
		}
	}
	
	@SkipValidation
	public String getSuper() throws Exception {
		joinSuper();
		session.put("table_info", tables);
		Staff activeStaff = active.getCurrent();
		flashUrl(activeStaff);
		return SUCCESS;
	}
	

	public String alterSuper() throws Exception {
		//�������
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//����Account��Ϣ
		String ownerNumber = this.tableContext.getOwnerNumber();
		Account newAccount = new Account();
		newAccount.setNumber(this.tableContext.getAccountNumber());
		newAccount.setPassword(password);
		newAccount.setSalf(salf);
		newAccount.setExplain(this.tableContext.getExplain());
		//���ڣ��޸�
		//�����ڣ����
		info.addHoldStaff(ownerNumber, newAccount);
		return getSuper();
	}
	
	
	public String removeSuper() throws Exception {
		info.removeHoldStaffAccount(tableContext.getOwnerNumber());
		return getSuper();
	}
	
	
	
	
	@SkipValidation
	public String getRoot() throws Exception {
		
		
		//������гֺ�Ա��
		joinSuper();
		//������в���
		List<Department> departments = info.getDepartments();
		for (Department department : departments) {
			joinInterior(department);
			
		}
		
		
		session.put("table_info", tables);
		Staff activeStaff = active.getCurrent();
		flashUrl(activeStaff);
		return SUCCESS;
	}
	
	/**
	 * ���Ƿ��빤��ȷ�������ʲô�˺�
	 * @return
	 * @throws Exception 
	 */
	public String alterRoot() throws Exception {
		//��¼�û�
		Staff activeStaff = active.getCurrent();
		//�������
		String password = secret.decodePassword(tableContext.getDeeppwd());
		String salf = secret.decodeSalf(tableContext.getDeeppwd());
		//Account��Ϣ
		String ownerNumber = this.tableContext.getOwnerNumber();
		Account newAccount = new Account();
		newAccount.setNumber(this.tableContext.getAccountNumber());
		newAccount.setPassword(password);
		newAccount.setSalf(salf);
		newAccount.setExplain(this.tableContext.getExplain());
		
		if(tableContext.getOwnerNumber().isEmpty()) {    //��ӱ������˺�
			info.addShareAccount(newAccount, activeStaff.getDepartment().getName());
		}else {
			info.addHoldStaff(ownerNumber, newAccount);
		}
		return getRoot();
	}
	
	public String removeRoot() throws Exception {
		if(info.isHoldStaff(tableContext.getOwnerNumber())) {    //Ա�������˺�
			info.removeHoldStaffAccount(tableContext.getOwnerNumber());
		}else {    //���Ź����˺�
			List<Department> departments = info.getDepartments();
			for (Department department : departments) {
				if(info.isShareAccount(tableContext.getAccountNumber(), department.getName())) {
					info.removeShareAccount(tableContext.getAccountNumber(), department.getName());
				}
				
			}
		}
		return getRoot();
	}
	
}
