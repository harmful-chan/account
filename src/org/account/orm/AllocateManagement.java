package org.account.orm;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.account.orm.model.*;
import org.account.orm.services.*;
import org.account.util.HibernateUtil;
import org.account.util.JDBCUtil;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Query;

import com.sun.javafx.scene.layout.region.Margins.Converter;

public class AllocateManagement implements IRoleable, IPermissionable, INodeable {

	public AllocateManagement(){
		
	}
	@Override
	public String getRoleName(String number) {
		String roleName = "";
		try {
			
			ResultSet rs = JDBCUtil.getStatement().executeQuery("select role_id from cmp_staff where number='"+number+"';");
			if(rs.next()) {
				 int roleId = rs.getInt("role_id");
				 ResultSet rs1 = JDBCUtil.getStatement().executeQuery("select name from sys_role where id='"+roleId+"';");
				 if(rs1.next()) {
					 roleName = rs1.getString("name");
				 }
			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		return roleName;
	}
	@Override
	public int Award(String number, String roleName) {
		int ret = -1;
		try {
			ResultSet rs = JDBCUtil.getStatement().executeQuery("select id from sys_role where name='"+roleName+"';");
			if(rs.next()) {
				 int roleId = rs.getInt("id");
				 ret = JDBCUtil.getStatement().executeUpdate("update cmp_staff set role_id='"+roleId+"' where number='"+number+"';");
			}
		}catch(Exception e) {
			return -1;
		}finally {
			JDBCUtil.close();
		}
		
		return ret;
	}
	@Override
	public int[] getPermission(String roleName) {
		List<Integer> ret = null;
		try {
			ResultSet rs = JDBCUtil.getStatement().executeQuery("select id from sys_role where name='"+roleName+"';");
			if(rs.next()) {
				 int roleId = rs.getInt("id");
				 ResultSet rs1 = JDBCUtil.getStatement().executeQuery("select permission_id from sys_role_permission WHERE role_id='"+roleId+"';");
				 ret = new ArrayList<Integer>();
				 while(rs1.next()) {
					 int permissionId = rs1.getInt("permission_id");
					 ResultSet rs2 = JDBCUtil.getStatement().executeQuery("select code from sys_permission WHERE id='"+permissionId+"';");
					 if(rs2.next()) {
						 ret.add(rs2.getInt("code"));
					 }
				 }

			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		
		int[] ret1 = new int[ret.size()];
		for (int i = 0; i < ret1.length; i++) {
			ret1[i] = ret.get(i);
		}
		return ret1;
	}
	@Override
	public List<String> getNodes() {
		List<String> ret = null;
		try {
			ResultSet rs = JDBCUtil.getStatement().executeQuery("select node from sys_node;");
			ret = new ArrayList<String>();
			while(rs.next()) {
				 String node = rs.getString("node");
				 ret.add(node);
			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		
		return ret;
	}
	@Override
	public List<String> getPrivates() {
		List<String> ret = null;
		try {
			ResultSet rs = JDBCUtil.getStatement().executeQuery("select node from sys_node where isPrivate='true';");
			ret = new ArrayList<String>();
			while(rs.next()) {
				 String node = rs.getString("node");
				 ret.add(node);
			}
		}catch(Exception e) {
			return null;
		}finally {
			JDBCUtil.close();
		}
		
		return ret;
	}
	@Override
	public boolean isNode(String url) {
		return url.indexOf("http://localhost:8080/account") >= 0;
	}
	@Override
	public boolean isAny(List<String> list, String url) {
		// TODO Auto-generated method stub
		list.contains(url);
		return false;
	}
}
