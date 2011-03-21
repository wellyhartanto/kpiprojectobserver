package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.util.Locale;

public enum Languages {

	SK("sk"), EN("en");

	private String text;

	Languages(String value) {
		this.text = value;
	}

	@Override
	public String toString() {
		return text;
	}

	public static Object[] getlanguages() {
		return Languages.values();
	}

	public static void setLanguage(int selectedLanguage) {

		if (selectedLanguage == SK.ordinal()) {
			Locale.setDefault(new Locale(SK.text));
		}
		if (selectedLanguage == EN.ordinal()) {
			Locale.setDefault(new Locale(EN.text));
		}

	}
}
