package com.dnarvaez27.componentes.buttons;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

/**
 * Clase que modela un botón con un Custom ToolTip
 *
 * @author d.narvaez11
 * @see {@link javax.swing.JButton}
 */
public class ButtonTip extends JButton
{
	private static final long serialVersionUID = 32115415957007018L;

	/**
	 * Alineamiento predeterminado del ToolTip
	 */
	private float alignmentTip = Component.CENTER_ALIGNMENT;

	/**
	 * Contiene el color del background predeterminado del ToolTip
	 */
	private Color bgTip = new Color( 33, 33, 33 );

	/**
	 * Borde predeterminado del ToolTip
	 */
	private Border bordeTip = BorderFactory.createEmptyBorder( );

	/**
	 * Contieen el color del foreground predeternimado del ToolTip
	 */
	private Color fgTip = new Color( 255, 255, 255 );

	/**
	 * Constructor predeterminado del Botón<br>
	 * El botón tiene Hand Cursor predeterminado<br>
	 * <br>
	 * <b>Se ha configurado el botón:</b>
	 * <ul>
	 * <li>setContentAreaFilled( false );
	 * <li>setOpaque( true );
	 * <li>setFocusPainted( false );
	 * <li>setBorderPainted( false );
	 * </ul>
	 *
	 * @see {@link javax.swing.JButton#JButton( )}
	 */
	public ButtonTip( )
	{
		super( );
		setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		configurar( );
	}

	/**
	 * Constructor del Botón con imagen<br>
	 * El botón tiene Hand Cursor predeterminado<br>
	 * <br>
	 * <b>Se ha configurado el botón:</b>
	 * <ul>
	 * <li>setContentAreaFilled( false );
	 * <li>setOpaque( true );
	 * <li>setFocusPainted( false );
	 * <li>setBorderPainted( false );
	 * </ul>
	 *
	 * @param icon Imagen del botón
	 * @see {@link javax.swing.JButton#JButton(javax.swing.Icon)}
	 */
	public ButtonTip( ImageIcon icon )
	{
		super( icon );
		setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		configurar( );
	}

	/**
	 * Constructor del Botón con texto<br>
	 * El botón tiene Hand Cursor predeterminado<br>
	 * <br>
	 * <b>Se ha configurado el botón:</b>
	 * <ul>
	 * <li>setContentAreaFilled( false );
	 * <li>setOpaque( true );
	 * <li>setFocusPainted( false );
	 * <li>setBorderPainted( false );
	 * </ul>
	 *
	 * @param txt Texto del botón
	 * @see {@link javax.swing.JButton#JButton(String)}
	 */
	public ButtonTip( String txt )
	{
		super( txt );
		setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		configurar( );
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
	 * <b>Configura:</b>
	 * <ul>
	 * <li>setContentAreaFilled( false );
	 * <li>setOpaque( true );
	 * <li>setFocusPainted( false );
	 * <li>setBorderPainted( false );
	 * </ul>
	 */
	private void configurar( )
	{
		setContentAreaFilled( false );
		setOpaque( true );
		setFocusPainted( false );
		setBorderPainted( false );
	}

	/**
	 * Configura el borde y el alineamiento del texto del ToolTip
	 *
	 * @param bordeT
	 * @param alignT
	 */
	public void configurar( Border bordeT, float alignT )
	{
		bordeTip = bordeT;
		alignmentTip = alignT;
	}

	/**
	 * Configura el tiempo del ToolTip
	 *
	 * @param dismissDelay Tiempo de Delay del ToolTip para desaparecer
	 * @param iniDelay Tiempo de Delay del ToolTip para aparecer
	 * @param reshowDelay Tiempo de Delay del ToolTip para reaparecer
	 */
	public void configurarTiempo( int dismissDelay, int iniDelay, int reshowDelay )
	{
		ToolTipManager.sharedInstance( ).setDismissDelay( dismissDelay );// Integer.MAX_VALUE
		ToolTipManager.sharedInstance( ).setInitialDelay( iniDelay );// 0
		ToolTipManager.sharedInstance( ).setReshowDelay( reshowDelay );// 0
	}

	/**
	 * <b>Configurar, si es necesario, antes de llamar este método</b><br>
	 * Configura el ToolTip
	 *
	 * @see {@link javax.swing.JButton#createToolTip( )}
	 */
	@Override
	public JToolTip createToolTip( )
	{
		super.createToolTip( );

		JToolTip tt = new JToolTip( );
		tt.setBackground( bgTip );
		tt.setForeground( fgTip );
		tt.setBorder( bordeTip );
		tt.setAlignmentX( alignmentTip );

		return tt;
	}

	/**
	 * Sobreescribe el metodo {@link javax.swing.JButton#setBackground(Color)}<br>
	 * Asigna el color al background del ToolTip
	 */
	@Override
	public void setBackground( Color c )
	{
		super.setBackground( c );
		bgTip = c;
	}
}