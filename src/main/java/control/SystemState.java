package control;

// this is singelton battona 
// know evreything about the system

public class SystemState
{
	 private int id;
	 private String type;
	 private boolean isLogged ;
	 private boolean isSearch;
	 private boolean isPickup;
	 private static SystemState instance=new SystemState() ;
	 
	 private SystemState()
	 {
		 
	 }
	 
	 public static SystemState getIstance()
	 {
		 if (instance == null) 
		 {
			 return new SystemState();
		 }
	 return instance;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public boolean getIsPickup() {
		return isPickup;
	}

	public void setPickup(boolean isPickup) {
		this.isPickup = isPickup;
	}
	
}
