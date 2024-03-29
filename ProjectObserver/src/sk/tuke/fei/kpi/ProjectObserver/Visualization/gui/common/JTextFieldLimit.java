package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4966132382864840082L;
	private final int limit;
	// optional uppercase conversion
	private boolean toUppercase = false;

	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
		toUppercase = upper;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			if (toUppercase)
				str = str.toUpperCase();
			super.insertString(offset, str, attr);
		}
	}
}
