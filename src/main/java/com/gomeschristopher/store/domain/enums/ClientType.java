package com.gomeschristopher.store.domain.enums;

public enum ClientType {

	NATURAL_PERSON(1, "Natural Person"),
	JURIDICAL_PERSON(2, "Juridical Person");
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (ClientType x : ClientType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id: " + cod);
			
	}
	
	
	
	
}
