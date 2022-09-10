package oop21.tentelli.fonti.observations.utility;

public enum ObservationsList {
	
	SI_ALZA("si alza"),
	
	PARLA("parla"),
	
	CORRE("corre"),
	
	LANCIA_OGGETTI("lancia oggetti"),
	
	DISTURBA("disturba");

	private String showText;
	
	private ObservationsList(final String showText) {
		this.showText = showText;
	}

	public String toString() {
		return this.showText;
	}
	
	public String getText() {
		return this.showText;
	}
}
