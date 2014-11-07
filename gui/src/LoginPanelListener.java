package src;

public interface LoginPanelListener {
	public boolean isValidAdminLogin(String username, char[] password);
	public boolean isValidLogin(String username, char[] password);
}
