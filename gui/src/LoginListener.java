package src;

public interface LoginListener {
	public void userHasLoggedIn(String userName);
	public void userHasLoggedOut();
	public void adminHasLoggedIn();
}
