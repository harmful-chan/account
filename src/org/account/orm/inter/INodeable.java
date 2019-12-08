package org.account.orm.inter;

import java.util.List;

public interface INodeable {
	public List<String> getNodes();
	public List<String> getPrivates();
	public boolean isNode(String url);
	public boolean isAny(List<String> list, String url);
}
