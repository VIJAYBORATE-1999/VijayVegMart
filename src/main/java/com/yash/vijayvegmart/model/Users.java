package com.yash.vijayvegmart.model;

public class Users {

	 private int id;
	    private String username;
	    private String password;
	    private String email;
	    private String isapproved;
	    private String isactive;
	    private String usertype;
	    
	    //Constructor
		public Users() {
			super();
			// TODO Auto-generated constructor stub
		}

	    //Constructor with fields 
		public Users(int id, String username, String password, String email, String isapproved, String isactive,
				String usertype) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
			this.isapproved = isapproved;
			this.isactive = isactive;
			this.usertype = usertype;
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

		public String getIsapproved() {
			return isapproved;
		}

		public void setIsapproved(String isapproved) {
			this.isapproved = isapproved;
		}

		public String getIsactive() {
			return isactive;
		}

		public void setIsactive(String isactive) {
			this.isactive = isactive;
		}

		public String getUsertype() {
			return usertype;
		}

		public void setUsertype(String usertype) {
			this.usertype = usertype;
		}
		
	    
}
