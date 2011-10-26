package jklass.iu;


import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;




class ColorColumnRenderer extends DefaultTableCellRenderer 
{
Color bkgndColor, fgndColor;

public ColorColumnRenderer(Color bkgnd, Color foregnd) {
super(); 
bkgndColor = bkgnd;
fgndColor = foregnd;
}

public Component getTableCellRendererComponent
(JTable table, Object value, boolean isSelected,
boolean hasFocus, int row, int column) 
{
Component cell = super.getTableCellRendererComponent
(table, value, isSelected, hasFocus, row, column);

cell.setBackground( bkgndColor );
cell.setForeground( fgndColor );

return cell;
}
}

