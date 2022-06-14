package it.synclab.sushilab.model;

public class JwtResponse {
	
	private String jwttoken;
	
	private Long idUtente;

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public JwtResponse(String jwttoken, Long idUtente) {
		this.jwttoken = jwttoken;
		this.idUtente=idUtente;
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