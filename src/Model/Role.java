package Model;

public class Role {
	private int roleID;
	private String roleName;
	
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int RoleID) {
		this.roleID = RoleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String RoleName) {
		this.roleName = RoleName;
	}
	public void setRole(int RoleID,String RoleName) {
		this.roleID = RoleID;
		this.roleName = RoleName;
	}
}
