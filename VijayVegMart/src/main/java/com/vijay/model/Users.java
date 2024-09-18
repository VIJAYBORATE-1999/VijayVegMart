package com.vijay.model;

public class Users {

	 private int id;
	    private String username;
	    private String password;
	    private String email;
	
	    
	    //Constructor
	    
	    public Users() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Users(int id, String username, String password, String email) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
		}
		
		//Getter Setter
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		

	    
	    
}
