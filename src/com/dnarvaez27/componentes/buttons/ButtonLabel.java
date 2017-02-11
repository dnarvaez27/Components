package com.dnarvaez27.componentes.buttons;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonLabel extends JPanel implements ActionListener
{
	private static final String FIELD_BUTTON = "Field Button";
	
	private static final String MAIN_BUTTON = "Main Button";
	
	private static final long serialVersionUID = -3748649926075460736L;
	
	private ButtonTip btnField;
	
	private ButtonTip buttonTip;
	
	private JPanel panelField;
	
	private JTextComponent textComponent;
	
	public ButtonLabel( JTextComponent textComponent, ButtonTip buttonTip, ButtonTip btnField )
	{
		setLayout( new BorderLayout( ) );
		
		this.textComponent = textComponent;
		this.buttonTip = buttonTip;
		
		if( textComponent instanceof JTextField )
		{
			JTextField jTextField = ( ( JTextField ) textComponent );
			jTextField.addActionListener( this );
			jTextField.setActionCommand( FIELD_BUTTON );
		}
		
		panelField = new JPanel( );
		panelField.setLayout( new BorderLayout( ) );
		panelField.add( textComponent, BorderLayout.CENTER );
		
		if( btnField != null )
		{
			this.btnField = btnField;
			this.btnField.setActionCommand( FIELD_BUTTON );
			this.btnField.addActionListener( this );
			
			panelField.add( this.btnField, BorderLayout.EAST );
		}
		
		this.buttonTip.addActionListener( this );
		this.buttonTip.setActionCommand( MAIN_BUTTON );
		
		add( this.buttonTip, BorderLayout.CENTER );
	}
	
	// public static void main( String[ ] args )
	// {
	// // JFT
	// JFrame f = new JFrame( );
	//
	// ButtonTip main = new ButtonTip( ":)" );
	// main.setHorizontalAlignment( SwingConstants.LEFT );
	//
	// ButtonLabel buttonLabel = new ButtonLabel( new JTextField( "Hola" ), main, null );
	// buttonLabel.setBorder( BorderFactory.createLineBorder( Color.RED ) );
	//
	// f.add( buttonLabel );
	//
	// f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	// f.setSize( 300, 300 );
	// f.setLocationRelativeTo( null );
	// f.setVisible( true );
	// }
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		
		if( comando.equals( MAIN_BUTTON ) )
		{
			remove( buttonTip );
			add( panelField, BorderLayout.CENTER );
			textComponent.setCaretPosition( textComponent.getText( ).length( ) );
			textComponent.requestFocus( );
		}
		else
		{
			remove( panelField );
			String text = "<html>" + textComponent.getText( ) + "</html>";
			text = text.replace( "\n", "<br>" );
			buttonTip.setText( text );
			add( buttonTip, BorderLayout.CENTER );
		}
		revalidate( );
		repaint( );
	}
}