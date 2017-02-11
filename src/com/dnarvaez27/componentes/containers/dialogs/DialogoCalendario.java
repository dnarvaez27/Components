package com.dnarvaez27.componentes.containers.dialogs;

import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.dnarvaez27.calendario.Calendario;
import com.dnarvaez27.calendario.recursos.Meses;
import com.dnarvaez27.componentes.buttons.ButtonTip;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Strings;

import java.awt.BorderLayout;
import java.awt.Color;

public class DialogoCalendario extends JDialog
{
	private static final long serialVersionUID = 6088895053334100274L;
	
	private Color backgroundColor = new Color( 220, 180, 10 );
	
	private Calendario calendario;
	
	private boolean rta;
	
	public DialogoCalendario( Calendar initial )
	{
		setTitle( Strings.PICK_DATE );
		setLayout( new BorderLayout( ) );
		
		calendario = new Calendario( true );
		calendario.setBackground( backgroundColor );
		calendario.setBackgroundColor( backgroundColor.darker( ) );
		if( initial != null )
		{
			calendario.setDate( initial.get( Calendar.YEAR ), Meses.values( )[ initial.get( Calendar.MONTH ) ] );
		}
		calendario.actualizar( );
		calendario.setForegroundColor( Color.WHITE );
		
		if( initial != null )
		{
			calendario.selectDay( initial.get( Calendar.DAY_OF_MONTH ) );
		}
		
		JPanel panelBotones = new JPanel( );
		
		ButtonTip btnOkCal = new ButtonTip( Strings.ACEPTAR );
		btnOkCal.addActionListener( a ->
		{
			rta = true;
			dispose( );
		} );
		
		ButtonTip btnCanCal = new ButtonTip( Strings.CANCELAR );
		btnCanCal.addActionListener( a -> dispose( ) );
		
		panelBotones.add( btnOkCal );
		panelBotones.add( btnCanCal );
		
		add( calendario, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
	}
	
	public Calendario getCalendar( )
	{
		return calendario;
	}
	
	public boolean getRta( )
	{
		return rta;
	}
	
	public Calendar getSelectedDate( )
	{
		return calendario.getSelectedDate( );
	}
	
	public boolean mostrar( )
	{
		pack( );
		setSize( 250, getHeight( ) );
		setResizable( false );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setLocationRelativeTo( null );
		setModal( true );
		setVisible( true );
		
		return rta;
	}
	
	public void setCalendarBackground( Color backgroundColor )
	{
		this.backgroundColor = backgroundColor;
		calendario.setBackground( backgroundColor );
		calendario.setBackgroundColor( backgroundColor.darker( ) );
		calendario.actualizar( );
	}
}