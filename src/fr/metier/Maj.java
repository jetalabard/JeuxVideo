package fr.metier;

public class Maj  {
	
	private String textMaj;
	
	private String textButton;

	public Maj(String text, String textButton) {
		this.setTextButton(textButton);
		this.setTextMaj(text);
	}

	public String getTextButton() {
		return textButton;
	}

	public void setTextButton(String textButton) {
		this.textButton = textButton;
	}

	public String getTextMaj() {
		return textMaj;
	}

	public void setTextMaj(String textMaj) {
		this.textMaj = textMaj;
	}

}
