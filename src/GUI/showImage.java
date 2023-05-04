package GUI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class showImage extends JDialog
{
	public showImage(ImageIcon image)
	{
		getContentPane().add(new JLabel(image));
		setVisible(true);
		setTitle("image");
		setSize(image.getIconWidth(), image.getIconHeight());
	}
}
