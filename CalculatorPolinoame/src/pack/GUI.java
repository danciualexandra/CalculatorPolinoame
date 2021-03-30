package pack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI extends JFrame
{
	private JButton butonAdunare, butonScadere, butonInmultire, butonDerivare, butonClear, butonEqual;
	private JTextArea jtext, jtext2;
	private JScrollPane pane, pane2;
	private Polinom polinom1, polinom2; //stores the polynomial expressions
	private int indicator; //ce operatie efctuam
	//1 adunare
	//2 scadere
	//3 inmultire

	
	public GUI()
	{  
		
		polinom1 = new Polinom();
	polinom2 = new Polinom();
	this.setTitle("Calculator de Polinoame");
		
		
		butonAdunare = new JButton("+");
		butonScadere = new JButton(" -");
		butonInmultire = new JButton(" *");
		butonDerivare = new JButton("( )'");
		butonClear = new JButton("Stergere");
		butonEqual = new JButton("=");
		
		jtext = new JTextArea();
		jtext2 = new JTextArea();
		pane = new JScrollPane(jtext,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(50, 100, 236, 32);
		pane2 = new JScrollPane(jtext2,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane2.setBounds(50, 65, 236, 32);
		pane2.setBorder(BorderFactory.createEmptyBorder());
		jtext2.setEditable(false);
		jtext2.setBackground(this.getBackground());
		jtext.setFont(new Font("Calibri",0,16));
		
		butonAdunare.setBounds(50, 150, 56,56);
		butonScadere.setBounds(140,150,56,56);
		butonInmultire.setBounds(230,150,56,56);
		butonScadere.setFocusable(false);
		butonInmultire.setFocusable(false);
		
		butonDerivare.setBounds(140,220,56,56);
	
		butonClear.setBounds(50, 290, 100, 50);
		butonEqual.setBounds(200, 290, 100, 50);
		
		butonAdunare.setFocusable(false);
		
		
		butonDerivare.setFocusable(false);
	
		butonClear.setFocusable(false);
	butonDerivare.addActionListener(new Deriv());
		
		butonAdunare.addActionListener(new Add());
		
		butonEqual.setFocusable(false);
		butonScadere.addActionListener(new Sub());
		butonInmultire.addActionListener(new Multi());
	
		butonEqual.addActionListener(new Equal());
		butonClear.addActionListener(new Clear());
		
		this.add(pane);
		
		this.add(butonInmultire);
		this.add(butonDerivare);
		this.add(butonClear);
		this.add(pane2);
		this.add(butonAdunare);
		this.add(butonScadere);
		this.add(butonEqual);
		
		
		this.setLayout(null);
		this.setSize(340, 480);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	private class Add implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(jtext.getText().length() > 0)
			{
				if(indicator == 1) polinom1.clearPol();
				String s = jtext.getText();
				s = s.replace('X','x'); //replace capital 'X' with 'x'
				if(!Operatie.readPolinom(s, polinom1))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					jtext.setText(null);
				}
				else
				{
				jtext.setText(null);
				jtext2.setText("(" + s + ")" + " +"); //put the text in the above text area
				indicator = 1; //set the flag to the corresponding operation
				}
			}
		}
	}
	

	
	private class Multi implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(jtext.getText().length() > 0)
			{
			if(indicator == 3) 
				polinom1.clearPol();
			String s = jtext.getText();
			s = s.replace('X','x');
			if(!Operatie.readPolinom(s, polinom1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				jtext.setText(null);
			}
			else
			{
			jtext.setText(null);
			jtext2.setText("(" + s + ")" + " *");
			indicator = 3;
			}
			}
		}
	}
	
	
	private class Deriv implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(jtext.getText().length() > 0)
			{
			String s = jtext.getText();
			s = s.replace('X','x');
			if(!Operatie.readPolinom(s, polinom1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				jtext.setText(null);
			}
			else
			{
			Polinom rez = Operatie.derivare(polinom1);
			rez.cleanPol();
			jtext.setText(rez.toString());
			}
			}
		}
	}
	
	
	
	private class Clear implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			jtext.setText(null);
			jtext2.setText(null);
			polinom1.clearPol(); //sterge polinomul curent
			polinom2.clearPol();
			indicator = 0;
		}
	}
	private class Equal implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(jtext.getText().length() > 0)
			{
			if (indicator == 1)
			{
				String s = jtext.getText(); //read the second expression
				s = s.replace('X','x');
				if(!Operatie.readPolinom(s, polinom2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					jtext.setText(null);
				}
				else
				{
				jtext2.setText(null);
				Polinom rez = Operatie.adunare(polinom1, polinom2); //obtain the result
				rez.cleanPol(); //erase monomials with coefficient 0
				rez.sort(); //sort the expression in descending order based on their degree
				jtext.setText(rez.toString()); //print the result
				indicator = 0; //set the flag back to 0
				}
			}
			else if (indicator == 2)
			{
				String s = jtext.getText();
				s = s.replace('X','x');
				if(!Operatie.readPolinom(s, polinom2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					jtext.setText(null);
				}
				else
				{
				jtext2.setText(null);
				Polinom rez = Operatie.scadere(polinom1, polinom2);
				rez.cleanPol();
				rez.sort();
				jtext.setText(rez.toString());
				indicator = 0;
				}
			}
			else if (indicator == 3)
			{
				String s = jtext.getText();
				s = s.replace('X','x');
				if(!Operatie.readPolinom(s, polinom2))
				{
					JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
					jtext.setText(null);
				}
				else
				{
				jtext2.setText(null);
				Polinom rez = Operatie.inmultire(polinom1, polinom2);
				rez.sort();
				rez.cleanPol();
				jtext.setText(rez.toString());
				indicator = 0;
				}
			}
			
	}
	} }
	
	private class Sub implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(jtext.getText().length() > 0)
			{
			if(indicator == 2) 
				polinom1.clearPol();
			String s = jtext.getText();
			s = s.replace('X','x');
			if(!Operatie.readPolinom(s, polinom1))
			{
				JOptionPane.showMessageDialog(null, String.format("%s", "Input should look like: ax^m+bx^n+c (no spaces)" ), "Invalid format", JOptionPane.ERROR_MESSAGE, null);
				jtext.setText(null);
			}
			else
			{
			jtext.setText(null);
			jtext2.setText("(" + s + ")" + " -");
			indicator = 2;
			}
		}
		}
	}
	
	}
	
				