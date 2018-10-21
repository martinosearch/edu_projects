package security;

public class CurrentUser extends User {
	private String type;
	private static CurrentUser instance;
	private boolean hasAccess;

	public boolean isHasAccess() {
		return hasAccess;
	}

	public void setHasAccess(boolean hasAccess) {
		this.hasAccess = hasAccess;
	}

	public static CurrentUser getInstance() {
		if (instance == null)
			instance = new CurrentUser();

		return instance;
	}
}
