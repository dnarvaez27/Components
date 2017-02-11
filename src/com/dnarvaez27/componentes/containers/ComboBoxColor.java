package com.dnarvaez27.componentes.containers;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

import java.awt.Color;
import java.awt.Cursor;

/**
 * Constructor de un Custom ComboBox
 *
 * @author d.narvaez11
 * @param <T> Tipo de datos a alojar
 * @see {@link javax.swing.JComboBox}
 */
public class ComboBoxColor<T> extends JComboBox<T>
{
	private class BasicComboBoxUIColor extends BasicComboBoxUI
	{
		/**
		 * Texto del botón
		 */
		private String buttonText;
		
		/**
		 * Constructor por defecto
		 */
		public BasicComboBoxUIColor( )
		{
			super( );
			buttonText = "▼";
		}
		
		/**
		 * Constructor con texto del botón
		 *
		 * @param text Texto del botón
		 */
		public BasicComboBoxUIColor( String text )
		{
			super( );
			buttonText = text;
		}
		
		/**
		 * Construye el botón del ComboBox
		 */
		@Override
		protected JButton createArrowButton( )
		{
			JButton b = new JButton( );
			b.setBackground( bgButton );
			b.setFocusPainted( false );
			b.setContentAreaFilled( false );
			b.setOpaque( true );
			b.setForeground( fgButton );
			b.setBorder( borde );
			b.setText( buttonText );
			b.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
			
			return b;
		}
	}
	
	private class ScrollBarUIColor extends BasicScrollBarUI
	{
		/**
		 * Constructor del ScrollBar
		 *
		 * @param bg Background del Scroll
		 * @param fg Foreground del Scroll ( Thumb )
		 */
		public ScrollBarUIColor( Color bg, Color fg )
		{
			super( );
			bgBar = bg;
			fgBar = fg;
		}
		
		@Override
		protected void configureScrollBarColors( )
		{
			thumbColor = fgBar;
			trackColor = bgBar;
			maximumThumbSize = new DimensionUIResource( 28, 28 );
		}
		
		@Override
		protected JButton createDecreaseButton( int orientation )
		{
			JButton dec = new JButton( "▲" );
			dec.setBackground( bgBar );
			dec.setForeground( fgBar );
			dec.setContentAreaFilled( false );
			dec.setOpaque( true );
			dec.setBorder( null );
			dec.setFocusPainted( false );
			dec.setPreferredSize( new DimensionUIResource( 20, 20 ) );
			
			return dec;
		}
		
		@Override
		protected JButton createIncreaseButton( int orientation )
		{
			JButton inc = new JButton( "▼" );
			inc.setBackground( bgBar );
			inc.setForeground( fgBar );
			inc.setContentAreaFilled( false );
			inc.setOpaque( true );
			inc.setBorder( null );
			inc.setFocusPainted( false );
			inc.setPreferredSize( new DimensionUIResource( 20, 20 ) );
			
			return inc;
		}
	}
	
	private static final long serialVersionUID = -4424033522578579322L;
	
	/**
	 * Background de la barra
	 */
	private Color bgBar;
	
	/**
	 * Background del Botón
	 */
	private Color bgButton;
	
	/**
	 * Borde del botón
	 */
	private Border borde;
	
	/**
	 * Foreground de la barra ( Thumb )
	 */
	private Color fgBar;
	
	/**
	 * Foreground del Botón
	 */
	private Color fgButton;
	
	/**
	 * Constructor predeterminado del ComboBox
	 */
	public ComboBoxColor( )
	{
		super( );
	}
	
	/**
	 * Constructor del ComboBox
	 *
	 * @param items Items del Combo
	 */
	public ComboBoxColor( T[ ] items )
	{
		super( items );
	}
	
	/**
	 * Constructor del ComboBox
	 *
	 * @param items Items del Combo
	 */
	public ComboBoxColor( Vector<T> items )
	{
		super( items );
	}
	
	/**
	 * Configura la altura de las celdas del ComboBox<br>
	 * <b>NOT WORKING</b>
	 *
	 * @param c Altura de la celda
	 * @see #configurarBoton(Color, Color, Border)
	 * @see #configurarAlturaCelda(int)
	 * @see #configurarScroll(Color, Color)
	 * @see #configurarBoton(Color, Color, Border, String)
	 */
	@Deprecated
	public void configurarAlturaCelda( int c )
	{
		ComboPopup popup = ( ComboPopup ) getUI( ).getAccessibleChild( this, 0 );
		popup.getList( ).setFixedCellHeight( c );
	}
	
	/**
	 * Configura el botón del ComboBox
	 * <br>
	 * <b> ORDEN: </b>
	 * <ol>
	 * <li>configurarBoton( )
	 * <li>configurarScroll( )
	 * </ol>
	 *
	 * @see #configurarBoton(Color, Color, Border)
	 * @see #configurarAlturaCelda(int)
	 * @see #configurarScroll(Color, Color)
	 * @see #configurarBoton(Color, Color, Border, String)
	 * @param bg Background del Botón
	 * @param fg Foreground del Botón
	 * @param borde Borde del Botón
	 */
	public void configurarBoton( Color bg, Color fg, Border borde )
	{
		bgButton = bg;
		fgButton = fg;
		this.borde = borde;
		setBackground( bg );
		
		this.setUI( new BasicComboBoxUIColor( ) );
	}
	
	/**
	 * Configura el botón del ComboBox
	 * <br>
	 * <b> ORDEN: </b>
	 * <ol>
	 * <li>configurarBoton( )
	 * <li>configurarScroll( )
	 * </ol>
	 *
	 * @see #configurarBoton(Color, Color, Border)
	 * @see #configurarAlturaCelda(int)
	 * @see #configurarScroll(Color, Color)
	 * @see #configurarBoton(Color, Color, Border, String)
	 * @param bg Background del Botón
	 * @param fg Foreground del Botón
	 * @param borde Borde del Botón
	 * @param textButton Texto del Botón principal
	 */
	public void configurarBoton( Color bg, Color fg, Border borde, String textButton )
	{
		bgButton = bg;
		fgButton = fg;
		this.borde = borde;
		setBackground( bg );
		
		this.setUI( new BasicComboBoxUIColor( textButton ) );
	}
	
	/**
	 * Configura la barra de desplazamiento del ComboBox
	 * <br>
	 * <b> ORDEN: </b>
	 * <ol>
	 * <li>configurarBoton( )
	 * <li>configurarScroll( )
	 * </ol>
	 *
	 * @see #configurarBoton(Color, Color, Border)
	 * @see #configurarAlturaCelda(int)
	 * @see #configurarScroll(Color, Color)
	 * @see #configurarBoton(Color, Color, Border, String)
	 * @param bg Background del Scroll
	 * @param fg Foreground del Scroll ( Thumb )
	 */
	public void configurarScroll( Color bg, Color fg )
	{
		setBackground( bg );
		
		JPopupMenu pm = ( JPopupMenu ) getUI( ).getAccessibleChild( this, 0 );
		JScrollPane sp = ( JScrollPane ) pm.getComponent( 0 );
		sp.getVerticalScrollBar( ).setUI( new ScrollBarUIColor( bg, fg ) );
	}
}