package org.account.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.account.orm.bean.*;
import org.account.orm.impl.AccountImpl;
import org.account.orm.impl.DepartmentImpl;
import org.account.orm.impl.RoleImpl;
import org.account.orm.impl.StaffImpl;
import org.account.util.HibernateUtil;

public class InfoServer {
	
	public InfoServer() {
	}
	
	/**
	 * 获取员工全部信息
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public Staff getStaffInfo(String staffNumber){
		Staff ret = (Staff)HibernateUtil.queryOnly("FROM Staff s JOIN FETCH s.role r JOIN FETCH s.department d WHERE s.number='"+staffNumber+"'");;
		return ret;	
	}
	
	
	
	/**
	 * 获取持有账号员工全部信息
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public Staff getHoldStaffInfo(String staffNumber){
		Staff ret = (Staff)HibernateUtil.queryOnly("FROM Staff s INNER JOIN FETCH s.role INNER JOIN FETCH s.department  JOIN FETCH s.account  WHERE s.number='"+staffNumber+"'");;
		return ret;	
	}
	
	
	
	/**
	 * 修改员工信息
	 * @param staff
	 * @throws Exception 
	 */
	public void updateStaffInfo(Staff staff) throws Exception {
		Staff s = getStaffInfo(staff.getNumber());
		s.setCity(staff.getCity());
		s.setEmail(staff.getEmail());
		s.setEntryDate(staff.getEntryDate());
		s.setProvince(staff.getProvince());
		s.setCity(staff.getCity());
		s.setZipCode(staff.getZipCode());
		HibernateUtil.update(s);
	}
	
	
	public List<Department> getDepartments() throws Exception{
		List<Department> ret = (List<Department>)HibernateUtil.queryList("FROM Department");
		if(ret == null) throw new Exception("InfoServer: getDepartments");
		return ret;
	}
	
	/**
	 * 获取部门信息
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public Department getDepartmentInfo(String departmentName) throws Exception{		
		Department ret = (Department)HibernateUtil.queryOnly("FROM Department d INNER JOIN FETCH d.staffs JOIN FETCH d.accounts WHERE  d.name='"+departmentName+"'");
		if(ret == null) throw new Exception("InfoServer: getDepartments");
		return ret;
	}
	
	/**
	 * 获取角色信息
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public Role getRoleInfo(String roleName) throws Exception {
		Role ret = (Role)HibernateUtil.queryOnly("FROM Role r WHERE r.name='"+roleName+"'");
		if(ret == null) throw new Exception("InfoServer: getRoleInfo");
		return ret;
	}
	
	/**
	 * 获取员工权限码集
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public List<Permission> getStaffPermissions(String number) throws Exception{
		
		List<Object[]> objectsList =  (List<Object[]>)HibernateUtil.queryList("FROM Staff s LEFT JOIN s.role r LEFT JOIN r.permissions  WHERE s.number='"+number+"'");
		if(objectsList == null) throw new Exception("InfoServer: getStaffPermissions");
		List<Permission> ret = new ArrayList<Permission>();
		objectsList.forEach(o -> ret.add((Permission)o[2]));
		return ret;
	}
	
	/**
	 * 获取员工权限内资源
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public List<Node> getStaffNodes(String number) throws Exception{
		List<Object[]> objectsList =  (List<Object[]>)HibernateUtil.queryList("FROM Staff s LEFT JOIN s.role r LEFT JOIN r.permissions p LEFT JOIN p.nodes  WHERE s.number='"+number+"'");
		if(objectsList == null) throw new Exception("InfoServer: getStaffNodes");
		List<Node> ret = new ArrayList<Node>();
		objectsList.forEach(o -> ret.add((Node)o[3]));
		return ret;
	}
	
	
	
	
	
	
	
	/**
	 * 判断员工是否持有登录账号
	 * @param number
	 * @return
	 */
	public boolean isHoldStaff(String staffNumber) {
		Staff ret = getHoldStaffInfo(staffNumber);
		return ret != null ;
	}
	
	/**
	 * 获取持有账号员工列表
	 * @return
	 * @throws Exception 
	 */
	public List<Staff> getHoldStaffList() throws Exception{
		List<Object[]> objectsList = (List<Object[]>)HibernateUtil.queryList("FROM  Staff s, Account a, Role r, Department d WHERE a.id=s.account.id and r.id=s.role.id and d.id=s.department.id");
		if(objectsList == null) throw new Exception("InfoServer: getHoldStaffList");
		List<Staff> holds = new ArrayList<Staff>();
		objectsList.forEach(o -> holds.add((Staff)o[0]));
		return holds;
	}
	
	public List<Staff> getNotHoldStaffList() throws Exception{
		List<Staff> objectsList = (List<Staff>)HibernateUtil.queryList("FROM Staff s JOIN FETCH s.role  JOIN FETCH s.department WHERE s.account is null");;
		if(objectsList == null) throw new Exception("InfoServer: getNotHoldStaffList");
		return objectsList;	
	}
	
	/**
	 * 获取员工持有账号
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public Account getHoldStaffAccount(String staffNumber) throws Exception {
		Staff staff = getHoldStaffInfo(staffNumber);
		return staff.getAccount();
	}
	

	
	/**
	 * 添加员工持有账号，账号不存在新建，在更新
	 * @param number
	 * @param account
	 * @throws Exception 
	 */
	public void addHoldStaff(String staffNumber, Account newAccount) throws Exception {
		Staff staff;
		try {
			staff = getStaffInfo(staffNumber);
			Account account = (Account)HibernateUtil.queryOnly("FROM Account a WHERE a.number='"+newAccount.getNumber()+"'");
			if(account != null) {
				staff.setAccount(account);
			}else {
				staff.setAccount(newAccount);
				HibernateUtil.save(newAccount);
			}
			HibernateUtil.update(staff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("InfoServer: getNotHoldStaffList");
		}
	}
	
	/**
	 * 移除员工持有账号
	 * @param number
	 * @param accountNumber
	 * @throws Exception 
	 */
	public void removeHoldStaffAccount(String staffNumber) throws Exception {
		Staff staff = getHoldStaffInfo(staffNumber);
		if(staff.getAccount() != null) {
			staff.setAccount(null);	
		}
		
		HibernateUtil.update(staff);
	}
	

	
	
	
	
	/**
	 * 判断账号是否属于部门
	 * @param accountNumber
	 * @param departmentName
	 * @return
	 * @throws Exception 
	 */
	public boolean isShareAccount(String accountNumber,String departmentName) throws Exception {
		List<Account> shareAccount = getShareAccountList(departmentName);

		boolean flag = false;
		
		for (Account account : shareAccount) {
			if(account.getNumber().equals(accountNumber)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	
	/**
	 * 获取部门共享Account
	 * @param department
	 * @return
	 * @throws Exception 
	 */
	public List<Account> getShareAccountList(String departmentName) throws Exception{
		Department department = getDepartmentInfo(departmentName);
		return new ArrayList<Account>(department.getAccounts());
	}
	
	/**
	 * 把Account添加进部门中
	 * @param account
	 * @param departments
	 * @throws Exception 
	 */
	public void addShareAccount(Account account, String... departmentNames) throws Exception {
		
		for (String departmentName : departmentNames) {
			
			Department department = getDepartmentInfo(departmentName);
			if(!isShareAccount(account.getNumber(), departmentName)) {
				department.getAccounts().add(account);
				HibernateUtil.save(account);
				HibernateUtil.update(department);
			}
			
		}
	}
	
	
	/**
	 * 吧账号从部门移除
	 * @param accountNumber
	 * @param departmentName
	 * @throws Exception 
	 */
	public void removeShareAccount(String accountNumber, String departmentName) throws Exception {
		Department department = getDepartmentInfo(departmentName);
		List<Account> shareAccount = new ArrayList<Account>(department.getAccounts());
		for (Account account : shareAccount) {
			if(account.getNumber().equals(accountNumber)) {
				department.getAccounts().remove(account);
				//account.getDepartments().remove(department);
				HibernateUtil.update(department);
				//HibernateUtil.update(account);
				
				break;
			}
			
		}
		
	}
	
}
