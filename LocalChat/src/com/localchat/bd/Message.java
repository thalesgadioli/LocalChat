package com.localchat.bd;



public class Message {
	
	private long id;
	private String texto;
	private String dt_enviada;
	private User rem;
	private User dest;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getDt_enviada() {
		return dt_enviada;
	}
	public void setDt_enviada(String dt_enviada) {
		this.dt_enviada = dt_enviada;
	}
	public User getRem() {
		return rem;
	}
	public void setRem(User rem) {
		this.rem = rem;
	}
	public User getDest() {
		return dest;
	}
	public void setDest(User dest) {
		this.dest = dest;
	}
	@Override
	public String toString() {
		String retorno = "De: " + rem.getName() + " " + texto + 
				" no dia: " + dt_enviada ;
		
		return retorno;
	}
	
	
}
