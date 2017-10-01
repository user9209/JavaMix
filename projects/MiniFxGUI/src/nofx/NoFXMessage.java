/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Created on 01.10.2017
 */

package nofx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoFXMessage extends JFrame {
  private JTextArea ta_message;
  private JScrollPane tasp_message;
  private JButton b_ok = new JButton();
  
  public NoFXMessage(String title, String message) {
    super();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();

    ta_message = new JTextArea(message);
    tasp_message = new JScrollPane(ta_message);
    ta_message.setEditable(false);
    ta_message.setLineWrap(true);

    /*
    System.out.println("width  of left + right  borders: " + (getWidth()-getContentPane ().getWidth()));
    System.out.println("height of top  + bottom borders: " + (getHeight()-getContentPane().getHeight()));
    System.out.println("height of top  + bottom borders: " + getHeight() + " " + getContentPane().getHeight());
    */

    int frameWidth = 280 + (getWidth()-getContentPane ().getWidth());
    int frameHeight = 170 + (getHeight()-getContentPane().getHeight());
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle(title);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    
    tasp_message.setBounds(10, 10, 270, 120);
    cp.add(tasp_message);
    b_ok.setBounds(100, 140, 100, 30);
    b_ok.setText("OK");
    b_ok.setMargin(new Insets(2, 2, 2, 2));
    b_ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        button_ActionPerformed(evt);
      }
    });
    cp.add(b_ok);

    setVisible(true);
  }

  public void button_ActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  public static void showMessageAndExit(String title, String message) {
     new NoFXMessage(title,message);
  }
}
