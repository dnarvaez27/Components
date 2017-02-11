package com.dnarvaez27.componentes.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class Tag extends RoundRectangle2D.Double
{
	public static final int ARC = 10;
	
	public static final int BTN_SIZE = 4;
	
	public static final int HEI = 20;
	
	private static final long serialVersionUID = -5851397423759716195L;
	
	private Color background;
	
	private Ellipse2D.Double btnRemove;
	
	private Graphics2D g;
	
	private String tag;
	
	public Tag( Graphics2D g, String tag, int x, int y, int w )
	{
		super( x, y, w + HEI + 2, HEI, ARC, ARC );
		btnRemove = new Ellipse2D.Double( x + w + 2, y + ( BTN_SIZE / 2 ), HEI - BTN_SIZE, HEI - BTN_SIZE );
		this.tag = tag;
		this.g = g;
		background = Color.DARK_GRAY;
	}
	
	public boolean containsRemoveBtn( Point p )
	{
		return btnRemove.contains( p );
	}
	
	public String getTag( )
	{
		return tag;
	}
	
	public void paintTag( )
	{
		g.setFont( g.getFont( ).deriveFont( Font.BOLD ) );
		g.setColor( Color.BLACK );
		g.drawString( tag, ( int ) ( x + 4 ), ( ( ( int ) y + HEI ) - ( g.getFont( ).getSize( ) / 2 ) ) + 2 );
		g.setColor( background );
		g.fill( btnRemove );
		String ex = "x";
		int wS = g.getFontMetrics( ).stringWidth( ex );
		int xB = ( int ) ( btnRemove.getX( ) + ( btnRemove.getWidth( ) / 2 ) ) - ( wS / 2 );
		int yB = ( int ) ( btnRemove.getY( ) + ( btnRemove.getHeight( ) / 2 ) + ( g.getFont( ).getSize( ) / 2 ) );
		yB -= 2; // btnSize/2 (?)
		g.setColor( Color.WHITE );
		g.drawString( ex, xB, yB );
	}
	
	public void paintTagTwoLine( )
	{
		g.setFont( g.getFont( ).deriveFont( Font.BOLD ) );
		g.setColor( Color.BLACK );
		
		int max = ( int ) ( getWidth( ) - HEI - 2 );
		StringBuilder tag1 = new StringBuilder( );
		StringBuilder tag2 = new StringBuilder( );
		boolean second = false;
		// Divides in optimal space
		for( String s : tag.split( " " ) )
		{
			if( ( g.getFontMetrics( ).stringWidth( tag1.toString( ) + s ) < max ) && !second )
			{
				tag1.append( s + " " );
			}
			else
			{
				second = true;
				tag2.append( s + " " );
			}
		}
		// Tag with no spaces
		if( tag1.length( ) == 0 )
		{
			second = false;
			tag2 = new StringBuilder( );
			for( char s : tag.toCharArray( ) )
			{
				if( ( g.getFontMetrics( ).stringWidth( tag1.toString( ) + s ) < max ) && !second )
				{
					tag1.append( s );
				}
				else
				{
					second = true;
					tag2.append( s );
				}
			}
		}
		String tag1S = tag1.substring( 0, tag1.length( ) - 1 );
		String tag2S = tag2.substring( 0, tag2.length( ) - 1 );
		
		g.drawString( tag1S, ( int ) ( x + 4 ), ( ( ( int ) y + HEI ) - ( g.getFont( ).getSize( ) / 2 ) ) + 2 );
		g.drawString( tag2S, ( int ) ( x + 4 ), ( int ) ( ( ( ( int ) y + HEI + ( HEI * 0.85 ) ) - ( g.getFont( ).getSize( ) / 2 ) ) + 2 ) );
		
		g.setColor( background );
		g.fill( btnRemove );
		String ex = "x";
		int wS = g.getFontMetrics( ).stringWidth( ex );
		int xB = ( int ) ( btnRemove.getX( ) + ( btnRemove.getWidth( ) / 2 ) ) - ( wS / 2 );
		int yB = ( int ) ( btnRemove.getY( ) + ( btnRemove.getHeight( ) / 2 ) + ( g.getFont( ).getSize( ) / 2 ) );
		yB -= 2; // btnSize/2 (?)
		g.setColor( Color.WHITE );
		g.drawString( ex, xB, yB );
	}
	
	public void setButtonBackground( Color background )
	{
		this.background = background;
	}
	
	public void setHeight( double h )
	{
		height = h;
	}
}