package com.dnarvaez27.componentes.text;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AreaHint extends JTextArea implements FocusListener
{
	private static final long serialVersionUID = -1195543382957749477L;
	
	private String hint;
	
	public AreaHint( String hint )
	{
		super( );
		this.hint = hint;
		addFocusListener( this );
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
		if( ( t == null ) || t.isEmpty( ) )
		{
			addHint( );
		}
		else if( !t.equals( hint ) )
		{
			setForeground( Color.BLACK );
		}
	}
	
	private void addHint( )
	{
		super.setText( hint );
		setForeground( Color.GRAY );
	}
	
	private void removeHint( )
	{
		setForeground( Color.BLACK );
		super.setText( "" );
	}
}