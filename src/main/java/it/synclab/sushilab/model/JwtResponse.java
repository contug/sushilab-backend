package it.synclab.sushilab.model;

public class JwtResponse {
	
	private String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public JwtResponse() {
		
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public void setToken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
}