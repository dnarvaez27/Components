package com.dnarvaez27.componentes.text;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextHint extends JTextField implements FocusListener
{
	private static final long serialVersionUID = -193371308210131218L;
	
	private String hint;
	
	public TextHint( String hint )
	{
		super( );
		addFocusListener( this );
		this.hint = hint;
		addHint( );
	}
	
	@Override
	public void focusGained( FocusEvent e )
	{
		if( super.getText( ).equals( hint ) )
		{
			removeHint( );
		}
	}
	
	@Override
	public void focusLost( FocusEvent e )
	{
		if( super.getText( ).isEmpty( ) )
		{
			addHint( );
		}
	}
	
	@Override
	public String getText( )
	{
		String rta = super.getText( );
		if( !rta.equals( hint ) )
		{
			return rta;
		}
		return "";
	}
	
	public void setHint( String hint )
	{
		this.hint = hint;
		addHint( );
	}
	
	@Override
	public void setText( String t )
	{
		super.setText( t );
		if( ( t == null || t.isEmpty( ) ) && !isFocusOwner( ) )
		{
			addHint( );
		}
		else if( t != null && !t.equals( hint ) )
		{
			super.setForeground( Color.BLACK );
		}
	}
	
	private void addHint( )
	{
		super.setText( hint );
		super.setForeground( Color.GRAY );
	}
	
	private void removeHint( )
	{
		super.setText( "" );
		super.setForeground( Color.BLACK );
	}
}