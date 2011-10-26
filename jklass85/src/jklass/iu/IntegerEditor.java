package jklass.iu;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Implementa l'editor de dades de la matriu de discretització manual.
 * @author Laia Riera Guerra
 * @version v4
 * Data: 23/06/2007
 */
public class IntegerEditor extends DefaultCellEditor {
    JFormattedTextField ftf;
    NumberFormat integerFormat;
    private Float minimum, maximum;
    private boolean DEBUG = false;
    /**
     * Constructor
     * @param min, valor mínim que pot introduir-se
     * @param max, valor màxim que pot introduir-se a la matriu
     */
    public IntegerEditor(float min, float max) {
        super(new JFormattedTextField());
        ftf = (JFormattedTextField)getComponent();
        minimum = new Float(min);
        maximum = new Float(max);

        //Set up the editor for the integer cells.
        
        integerFormat = NumberFormat.getNumberInstance();
        NumberFormatter intFormatter = new NumberFormatter(integerFormat);
        intFormatter.setFormat(integerFormat);
        intFormatter.setMinimum(minimum);
        intFormatter.setMaximum(maximum);

        ftf.setFormatterFactory(
                new DefaultFormatterFactory(intFormatter));
        ftf.setValue(minimum);
        ftf.setHorizontalAlignment(JTextField.TRAILING);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

        //React when the user presses Enter while the editor is
        //active.  (Tab is handled as specified by
        //JFormattedTextField's focusLostBehavior property.)
        ftf.getInputMap().put(KeyStroke.getKeyStroke(
                                        KeyEvent.VK_ENTER, 0),
                                        "check");
        ftf.getActionMap().put("check", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
		if (!ftf.isEditValid()) { //The text is invalid.
                    if (userSaysRevert()) { //reverted
		        ftf.postActionEvent(); //inform the editor
		    }
                } else try {              //The text is valid,
                    ftf.commitEdit();     //so use it.
                    ftf.postActionEvent(); //stop editing
                } catch (java.text.ParseException exc) { }
            }
        });
    }

    //Override to invoke setValue on the formatted text field.
   /***
    * Invoca la funció setValue en un camp de text formatejat
    */
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected,
            int row, int column) {
        JFormattedTextField ftf =
            (JFormattedTextField)super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
        ftf.setValue(value);
        return ftf;
    }

    //Override to ensure that the value remains an Integer.
    /***
     * Comprova que el valor continua sent un float
     */
    public Object getCellEditorValue() {
        JFormattedTextField ftf = (JFormattedTextField)getComponent();
        Object o = ftf.getValue();
        if (o instanceof Float) {
            return o;
        } else if (o instanceof Number) {
            return new Float(((Number)o).floatValue());
        } else {
            if (DEBUG) {
                System.out.println("getCellEditorValue: o isn't a Number");
            }
            try {
                return integerFormat.parseObject(o.toString());
            } catch (ParseException exc) {
            	exc.printStackTrace();
                System.err.println("getCellEditorValue: can't parse o: " + o);
                return null;
            }
        }
    }

    //Override to check whether the edit is valid,
    //setting the value if it is and complaining if
    //it isn't.  If it's OK for the editor to go
    //away, we need to invoke the superclass's version 
    //of this method so that everything gets cleaned up.
    /**
     * Comprova si l'edició és vàlida, inserint el valor a la matriu si ho és
     * i mostrant un missatge d'error en cas contrari.
     */
    public boolean stopCellEditing() {
        JFormattedTextField ftf = (JFormattedTextField)getComponent();
        if (ftf.isEditValid()) {
            try {
                ftf.commitEdit();
            } catch (java.text.ParseException exc) { }
	    
        } else { //text is invalid
            if (!userSaysRevert()) { //user wants to edit
	        return false; //don't let the editor go away
	    } 
        }
        return super.stopCellEditing();
    }

    
    /**
     * Indica a l'usuari que el text entrat no és correcte i li permet reeditar-lo o bé desfer el canvi.
     * @return true si l'usuari selecciona l'opció de desfer el canvi, false altrament.
     */
    protected boolean userSaysRevert() {
        Toolkit.getDefaultToolkit().beep();
        ftf.selectAll();
        Object[] options = {"Editar",
                            "Esborrar"};
        int answer = JOptionPane.showOptionDialog(
            SwingUtilities.getWindowAncestor(ftf),
            "El valor ha de ser un decimal entre "
            + minimum + " i "
            + maximum + ".\n"
            + "Pots continuar editant "
            + "o pots esborrar l'últim valor.",
            "Invalid Text Entered",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            options,
            options[1]);
	    
        if (answer == 1) { //Revert!
            ftf.setValue(ftf.getValue());
	    return true;
        }
	return false;
    }
}