package com.dnarvaez27.componentes.graphics;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class ImageShape
{
	private ImageIcon image;
	
	private Rectangle2D rectangle;
	
	private int x;
	
	private int y;
	
	public ImageShape( ImageIcon image, int x, int y )
	{
		this.x = x;
		this.y = y;
		this.image = image;
		rectangle = new Rectangle2D.Double( x, y, image.getIconWidth( ), image.getIconHeight( ) );
	}
	
	public boolean contains( int x, int y )
	{
		return rectangle.contains( x, y );
	}
	
	public boolean contains( Point point )
	{
		return rectangle.contains( point );
	}
	
	public Image getImage( )
	{
		return image.getImage( );
	}
	
	public int getX( )
	{
		return x;
	}
	
	public int getY( )
	{
		return y;
	}
}
