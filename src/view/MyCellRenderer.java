package view;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;


public class MyCellRenderer extends JButton implements ListCellRenderer, TableCellRenderer {
	private boolean b=false;
	public MyCellRenderer() {  
        setOpaque(true); 

    }
   
   @Override
   public void setBackground(Color bg) {
      
        if(!b)
        {
            return;
        }

       super.setBackground(bg);
   }
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)  
    {  

        b=true;
        setText(" ");           
        setBackground((Color)value);        
        b=false;
        return this;  
    }

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		    b=true;
	        setText(" ");           
	        setBackground((Color)value);        
	        b=false;
	        return this; 
	}  
		
	

}
