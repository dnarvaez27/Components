package com.dnarvaez27.componentes.labels;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import java.awt.Color;

/**
 * Construye un Label con un Custom ToolTip
 *
 * @see {@link javax.swing.JLabel}
 * @author d.narvaez11
 */
public class LabelTip extends JLabel
{
	private static final long serialVersionUID = -7217970904676792950L;

	/**
	 * Background del ToolTip
	 */
	private Color bgTip = new Color( 33, 33, 33 );

	/**
	 * Foreground del ToolTip
	 */
	private Color fgTip = new Color( 255, 255, 255 );

	/**
	 * Constructor de un label por defecto
	 *
	 * @see {@link javax.swing.JLabel#JLabel( ) }
	 */
	public LabelTip( )
	{
		super( );
	}

	public LabelTip( Icon image, int horizontalAlignment )
	{
		super( image, horizontalAlignment );
	}

	public LabelTip( Icon image )
	{
		super( image );
	}

	public LabelTip( String text, Icon icon, int horizontalAlignment )
	{
		super( text, icon, horizontalAlignment );
	}

	public LabelTip( String text, int horizontalAlignment )
	{
		super( text, horizontalAlignment );
	}

	/**
	 * Constructor de un label con imagen
	 *
	 * @param icon Imagen del Label
	 * @see {@link javax.swing.JLabel#JLabel( javax.swing.Icon ) }
	 */
	public LabelTip( ImageIcon icon )
	{
		super( icon );
	}

	/**
	 * Constructor de un label con texto
	 *
	 * @param text Texto del label
	 * @see {@link javax.swing.JLabel#JLabel( String ) }
	 */
	public LabelTip( String text )
	{
		super( text );
	}

	/**
	 * Cambia el color del ToolTip
	 *
	 * @param bg Background del ToolTip
	 * @param fg Foreground del ToolTip
	 */
	public void cambiarColorTip( Color bg, Color fg )
	{
		bgTip = bg;
		fgTip = fg;
	}

	/**
	 * Construye el ToolTip
	 *
	 * @see {@link javax.swing.JLabel#createToolTip( )}
	 */
	@Override
	public JToolTip createToolTip( )
	{
		JToolTip tt = new JToolTip( );
		tt.setBackground( bgTip );
		tt.setForeground( fgTip );
		tt.setBorder( BorderFactory.createLineBorder( new Color( 255, 255, 255 ) ) );
		tt.setAlignmentX( CENTER_ALIGNMENT );
		return tt;
	}

	/**
	 * Configura el texto del ToolTip en formato HTML
	 *
	 * @see {@link javax.swing.JLabel#setToolTipText( String ) }
	 */
	@Override
	public void setToolTipText( String text )
	{
		super.setToolTipText( "<html><br>&nbsp;" + text + "&nbsp<br>&nbsp</html>" );
	}
}