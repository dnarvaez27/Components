package com.dnarvaez27.componentes.containers;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Crea un Custom ScrollPane
 *
 * @see {@link javax.swing.JScrollPane}
 * @author d.narvaez11
 */
public class ScrollColor extends JScrollPane
{
	/**
	 * Custom BasicScrollBarUI
	 * 
	 * @author d.narvaez11
	 */
	private class BasicScrollBarUICustom extends BasicScrollBarUI
	{
		/**
		 * Color de la barra de desplazamiento
		 */
		private Color barra;
		
		/**
		 * Color del botón
		 */
		private Color botonBg;
		
		/**
		 * Color del Foreground del Botón
		 */
		private Color botonFg;
		
		/**
		 * Color del camino del scroll
		 */
		private Color camino;
		
		/**
		 * String para el boton de descenso
		 */
		private String decString;
		
		/**
		 * String para el botón de incremento
		 */
		private String incString;
		
		/**
		 * Contructor
		 * 
		 * @param barra Color de la barra de desplazamiento del Scroll
		 * @param camino Color del camino del Scroll
		 * @param botonBg Color del Background del botón
		 * @param botonFg Color del Foreground del boton
		 * @param decString Texto de decremento del boton
		 * @param incString Texto de incremento del boton
		 */
		public BasicScrollBarUICustom( Color barra, Color camino, Color botonBg, Color botonFg, String decString, String incString )
		{
			this.barra = barra;
			this.camino = camino;
			this.botonBg = botonBg;
			this.botonFg = botonFg;
			this.decString = decString;
			this.incString = incString;
		}
		
		@Override
		protected void configureScrollBarColors( )
		{
			thumbColor = barra;
			trackColor = camino;
		}
		
		@Override
		protected JButton createDecreaseButton( int orientation )
		{
			JButton dec = new JButton( decString );
			dec.setBackground( botonBg );
			dec.setForeground( botonFg );
			dec.setContentAreaFilled( false );
			dec.setOpaque( true );
			dec.setBorder( null );
			dec.setFocusPainted( false );
			dec.setPreferredSize( new Dimension( 20, 20 ) );
			
			return dec;
		}
		
		@Override
		protected JButton createIncreaseButton( int orientation )
		{
			JButton inc = new JButton( incString );
			inc.setBackground( botonBg );
			inc.setForeground( botonFg );
			inc.setContentAreaFilled( false );
			inc.setOpaque( true );
			inc.setBorder( null );
			inc.setFocusPainted( false );
			inc.setPreferredSize( new Dimension( 20, 20 ) );
			
			return inc;
		}
	}
	
	private static final long serialVersionUID = -6061560849032895941L;
	
	/**
	 * Crea un Scroll Personalizado
	 *
	 * @param comp Componente que alojará el Scroll
	 * @param orientacion 1: Vertical. 2: Horizontal. 3: Ambos
	 * @param bg Background de los botones y del Scroll
	 * @param fg Foreground de los botones y del Scroll ( Thumb )
	 * @see #ScrollColor(Component, int, Color, Color, Color, Color)
	 */
	public ScrollColor( Component comp, int orientacion, Color bg, Color fg )
	{
		this( comp, orientacion, bg, fg, fg, bg );
		// new ScrollColor( comp, orientacion, bg, fg, fg, bg );
	}
	
	/**
	 * Crea un Scroll Personalizado
	 *
	 * @param comp Componente que alojará el Scroll
	 * @param orientacion 0: Vertical. 1: Horizontal. 2: Ambos
	 * @param botonBg Background de los botones
	 * @param botonFg Foreground de los botones
	 * @param barra Foreground del Scroll ( Thumb )
	 * @param camino Background del Scroll
	 * @see {@link javax.swing.JScrollPane#JScrollPane(Component)}
	 */
	public ScrollColor( Component comp, int orientacion, Color botonBg, Color botonFg, Color barra, Color camino )
	{
		super( comp );
		setBorder( null );
		
		switch( orientacion )
		{
			case 0:
				configurarBarraVertical( botonBg, botonFg, barra, camino );
				setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
				break;
			case 1:
				configurarBarraHorizontal( botonBg, botonFg, barra, camino );
				setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
				break;
			case 2:
				configurarBarraVertical( botonBg, botonFg, barra, camino );
				configurarBarraHorizontal( botonBg, botonFg, barra, camino );
				break;
		}
	}
	
	/**
	 * Configura la barra horizontal del Scroll. <br>
	 * Ajusta le unidad de incremento a 16
	 *
	 * @param bg Background de los botones y del Scroll
	 * @param fg Foreground de los botones y del Scroll ( Thumb )
	 * @see #configurarBarraHorizontal(Color, Color, Color, Color)
	 */
	public void configurarBarraHorizontal( Color bg, Color fg )
	{
		configurarBarraHorizontal( bg, fg, fg, bg );
	}
	
	/**
	 * Configura la barra horizontal del Scroll<br>
	 * Ajusta le unidad de incremento a 16
	 *
	 * @param botonBg Background de los botones
	 * @param botonFg Foreground de los botones
	 * @param barra Foreground del Scroll ( Thumb )
	 * @param camino Background del Scroll
	 */
	public void configurarBarraHorizontal( Color botonBg, Color botonFg, Color barra, Color camino )
	{
		getHorizontalScrollBar( ).setUnitIncrement( 16 );
		getHorizontalScrollBar( ).setUI( new BasicScrollBarUICustom( barra, camino, botonBg, botonFg, "◄", "►" ) );
	}
	
	/**
	 * Configura la barra vertical del Scroll. <br>
	 * Ajusta le unidad de incremento a 16
	 *
	 * @param bg Background de los botones y del Scroll
	 * @param fg Foreground de los botones y del Scroll ( Thumb )
	 * @see #configurarBarraVertical(Color, Color, Color, Color)
	 */
	public void configurarBarraVertical( Color bg, Color fg )
	{
		configurarBarraVertical( bg, fg, fg, bg );
	}
	
	/**
	 * Configura la barra vertical del Scroll <br>
	 * Ajusta le unidad de incremento a 16
	 *
	 * @param botonBg Background de los botones
	 * @param botonFg Foreground de los botones
	 * @param barra Foreground del Scroll ( Thumb )
	 * @param camino Background del Scroll
	 */
	public void configurarBarraVertical( Color botonBg, Color botonFg, Color barra, Color camino )
	{
		getVerticalScrollBar( ).setUnitIncrement( 16 );
		getVerticalScrollBar( ).setUI( new BasicScrollBarUICustom( barra, camino, botonBg, botonFg, "▲", "▼" ) );
	}
}