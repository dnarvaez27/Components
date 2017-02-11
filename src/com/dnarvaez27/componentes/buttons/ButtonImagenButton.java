package com.dnarvaez27.componentes.buttons;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

// TODO Abstact more?
public class ButtonImagenButton extends ButtonTip implements MouseMotionListener, MouseListener
{
	public static final String COMANDO_SECUNDARIO = ";;;COMANDO_SECUNDARIO";
	
	private static final long serialVersionUID = -2390350973439514515L;
	
	private ActionListener aListener;
	
	private ActionListener aListenerButton;
	
	private Ellipse2D button;
	
	private Color buttonBackground;
	
	private Color buttonBg;
	
	private Color buttonForeground;
	
	private String buttonText;
	
	private boolean isInButton;
	
	public ButtonImagenButton( )
	{
		super( );
		addMouseMotionListener( this );
		addMouseListener( this );
	}
	
	public ButtonImagenButton( ImageIcon icon )
	{
		super( icon );
		addMouseMotionListener( this );
		addMouseListener( this );
	}
	
	public ButtonImagenButton( String txt )
	{
		super( txt );
		addMouseMotionListener( this );
		addMouseListener( this );
	}
	
	@Override
	public void addActionListener( ActionListener l )
	{
		this.aListener = l;
	}
	
	public void addActionListenerButton( ActionListener l )
	{
		this.aListenerButton = l;
	}
	
	@Override
	public void mouseClicked( MouseEvent e )
	{
		if( isInButton )
		{
			aListenerButton.actionPerformed( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, getActionCommand( ) + COMANDO_SECUNDARIO ) );
		}
		else
		{
			aListener.actionPerformed( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, getActionCommand( ) ) );
		}
	}
	
	@Override
	public void mouseDragged( MouseEvent e )
	{
		
	}
	
	@Override
	public void mouseEntered( MouseEvent e )
	{
	}
	
	@Override
	public void mouseExited( MouseEvent e )
	{
		buttonBackground = buttonBg;
	}
	
	@Override
	public void mouseMoved( MouseEvent e )
	{
		isInButton = button != null && button.contains( e.getPoint( ) );
		if( isInButton )
		{
			buttonBackground = buttonBg.darker( );
		}
		else
		{
			buttonBackground = buttonBg;
		}
		repaint( );
	}
	
	@Override
	public void mousePressed( MouseEvent e )
	{
		
	}
	
	@Override
	public void mouseReleased( MouseEvent e )
	{
	}
	
	@Override
	public void paint( Graphics g )
	{
		super.paint( g );
		if( !buttonText.isEmpty( ) )
		{
			g.setColor( buttonBackground );
			
			int x = ( getWidth( ) / 2 ) - 12;
			button = new Ellipse2D.Double( x, getHeight( ) - 28, 25, 25 );
			
			paintShadow( ( Graphics2D ) g );
			g.setColor( buttonBackground );
			( ( Graphics2D ) g ).fill( button );
			
			x += 25 / 2;
			x -= g.getFontMetrics( ).stringWidth( buttonText ) / 2;
			
			g.setColor( buttonForeground );
			g.drawString( buttonText, x + 2, ( getHeight( ) - ( g.getFont( ).getSize( ) / 2 ) ) - 4 );
		}
	}
	
	public void setButtonBackground( Color buttonBackground )
	{
		this.buttonBackground = buttonBackground;
		buttonBg = buttonBackground;
	}
	
	public void setButtonForeground( Color buttonForeground )
	{
		this.buttonForeground = buttonForeground;
	}
	
	public void setButtonText( String buttonText )
	{
		this.buttonText = buttonText;
	}
	
	private void paintShadow( Graphics2D g )
	{
		
		int r = buttonBackground.getRed( );
		int gr = buttonBackground.getGreen( );
		int b = buttonBackground.getBlue( );
		
		int width = ( int ) button.getWidth( );
		int height = ( int ) button.getHeight( );
		
		int x = ( int ) ( button.getX( ) );
		int y = ( int ) ( button.getY( ) );
		
		g.setColor( buttonBackground.darker( ).darker( ).darker( ) );
		g.drawOval( x, y, width, height );
		
		width = ( int ) button.getWidth( )/* + ( tam * 2 ) */ + 2;
		height = ( int ) button.getHeight( ) /* + ( tam * 2 ) */;
		x = ( int ) ( button.getX( ) /*- tam */ ) - 1;
		for( int tam = 2; tam > 0; tam-- )
		{
			
			y = ( int ) ( button.getY( ) - tam );
			g.setColor( new Color( r, gr, b, 150 ).darker( ) );
			g.fillOval( x, y, width, height );
		}
		
		// final int init = 3;
		// for( int i = init; i >= 0; i-- )
		// {
		// int width = ( int ) button.getWidth( ) + ( i * 2 );
		// int height = ( int ) button.getHeight( ) + ( i * 2 );
		//
		// int x = ( int ) ( button.getX( ) - i );
		// int y = ( int ) ( button.getY( ) - i );
		//
		// int alpha = ( 255 / ( i + 1 ) );
		// g.setColor( new Color( r, gr, b, alpha ).darker( ) );
		//
		// g.fillOval( x, y, width, height );
		// }
	}
}