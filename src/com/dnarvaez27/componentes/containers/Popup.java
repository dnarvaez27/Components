package com.dnarvaez27.componentes.containers;

import java.awt.Component;
import java.awt.Rectangle;

/**
 * Clase que modela un Popup
 *
 * @author d.narvaez11
 * @see javax.swing.Popup
 */
public class Popup extends javax.swing.Popup
{
	/**
	 * Bounds del Popup
	 */
	private Rectangle bounds;
	
	/**
	 * Verifica si el popup se muestra o no
	 */
	private boolean showing;
	
	/**
	 * Constructor del Popup
	 *
	 * @see javax.swing.Popup#Popup()
	 */
	public Popup( )
	{
		super( );
	}
	
	/**
	 * Constructor del Popup
	 *
	 * @param owner Parent del Popup
	 * @param contents Contenido del Popup
	 * @param x Posición en x del Popup
	 * @param y Posición en y del Popup
	 * @see javax.swing.Popup#Popup(Component, Component, int, int)
	 */
	public Popup( Component owner, Component contents, int x, int y )
	{
		super( owner, contents, x, y );
		bounds = new Rectangle( x, y, contents.getWidth( ), contents.getHeight( ) );
	}
	
	/**
	 * Retorna los Bounds del Popup
	 * 
	 * @return Bounds del Popup
	 */
	public Rectangle getBounds( )
	{
		return bounds;
	}
	
	/**
	 * Oculta el popup<br>
	 * Cambia el estado de {@link #isShown}
	 */
	@Override
	public void hide( )
	{
		super.hide( );
		showing = false;
	}
	
	/**
	 * Retorna el estado del Popup
	 *
	 * @return True si el Popup se esta mostrando, False de lo contrario
	 */
	public boolean isShowing( )
	{
		return showing;
	}
	
	/**
	 * Muestra el Popup<br>
	 * Cambia el estado de {@link #isShown}
	 */
	@Override
	public void show( )
	{
		super.show( );
		showing = true;
	}
}