package com.dnarvaez27.componentes.buttons;

import javax.swing.ImageIcon;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Clase que modela un botón con background efectivamente transparente<br>
 * Extiende de ButtonTip
 *
 * @see {@link com.dnarvaez27.componentes.ButtonTip}.
 * @author d.narvaez11
 */
public class ButtonTransparent extends ButtonTip
{
	private static final long serialVersionUID = -2202716752198966870L;

	/**
	 * Imagen del Botón
	 */
	private ImageIcon image;

	/**
	 * Texto del Botón
	 */
	private String text;

	/**
	 * Constructor predeterminado del Botón
	 *
	 * @see {@link javax.swing.JButton#JButton()}
	 */
	public ButtonTransparent( )
	{
		super( );
		setContentAreaFilled( false );
		setOpaque( false );
		setBackground( null );
		setFocusPainted( false );
		setBorderPainted( false );
	}

	/**
	 * Constructor del botón con una imagen
	 *
	 * @param icon Imagen del botón
	 * @see {@link javax.swing.JButton#JButton(javax.swing.Icon)}
	 */
	public ButtonTransparent( ImageIcon icon )
	{
		super( icon );
		image = icon;
		setContentAreaFilled( false );
		setOpaque( false );
		setBackground( null );
		setFocusPainted( false );
		setBorderPainted( false );
	}

	/**
	 * Constructor del botón con texto
	 *
	 * @param txt Texto del Botón
	 * @see {@link javax.swing.JButton#JButton(String)}
	 */
	public ButtonTransparent( String txt )
	{
		super( txt );
		text = txt;
		setContentAreaFilled( false );
		setOpaque( false );
		setBackground( null );
		setFocusPainted( false );
		setBorderPainted( false );
	}

	@Override
	public void paint( Graphics g )
	{
		if( image != null )
		{
			Image im = image.getImage( );
			g.drawImage( im, ( getWidth( ) / 2 ) - ( im.getWidth( null ) / 2 ), ( getHeight( ) / 2 ) - ( im.getHeight( null ) / 2 ), this );
		}
		if( ( text != null ) && !text.isEmpty( ) )
		{
			int x = getWidth( ) / 2;
			int y = getHeight( ) / 2;
			FontMetrics metrics = getFontMetrics( getFont( ) );
			x -= metrics.stringWidth( text ) / 2;
			y += metrics.getHeight( ) / 4;
			g.drawString( text, x, y );
		}
	}

	@Override
	public void paintAll( Graphics g )
	{
	}

	@Override
	public void paintComponent( Graphics g )
	{
	}

	@Override
	public void paintComponents( Graphics g )
	{
	}

	/**
	 * Asigna la imagen del botón
	 *
	 * @param image
	 */
	public void setImageButton( ImageIcon image )
	{
		this.image = image;
	}
}