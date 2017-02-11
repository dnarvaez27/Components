package com.dnarvaez27.componentes.containers;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Imagenes;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Modela un panel con una imagen como background<br>
 * <b> Se debe importar la libreria Resources</b>
 *
 * @author d.narvaez11
 */
public class PanelImagen extends JPanel implements MouseMotionListener, MouseListener
{
	private static final long serialVersionUID = 6673444782341355880L;
	
	/**
	 * Determina si se muestra el botón de retorno
	 */
	private boolean backButton;
	
	/**
	 * Dialogo de confirmación al cerrar
	 */
	private JDialog dialogClose;
	
	/**
	 * Imagen de fondo del Panel
	 */
	private Image imageBackground;
	
	/**
	 * Determina si el cursor esta sobre el botón de retorno
	 */
	private boolean isBackIn;
	
	/**
	 * Determina si el cursor esta sobre el boton de cierre
	 */
	private boolean isCloseIn;
	
	/**
	 * Constructor por defecto del Panel<br>
	 * Asigna el MouseListener
	 *
	 * @see #setMouseListener()
	 * @see {@link javax.swing.JPanel#JPanel() }
	 */
	public PanelImagen( )
	{
		super( );
		setMouseListener( );
	}
	
	/**
	 * Constructor del panel con un DoubleBuffered
	 *
	 * @param isDoubleBuffered {@link javax.swing.JPanel#setDoubleBuffered(boolean)}
	 * @see #setMouseListener()
	 * @see {@link javax.swing.JPanel#JPanel(boolean)}
	 */
	public PanelImagen( boolean isDoubleBuffered )
	{
		super( isDoubleBuffered );
		setMouseListener( );
	}
	
	/**
	 * Constructor de un panel con Layout
	 *
	 * @param layout Layout del panel
	 * @see #setMouseListener()
	 * @see {@link javax.swing.JPanel#JPanel(LayoutManager) }
	 */
	public PanelImagen( LayoutManager layout )
	{
		super( layout );
		setMouseListener( );
	}
	
	/**
	 * Constructor de un panel con un layout y un doubleBuffered especifico
	 *
	 * @param layout Layout del Panel
	 * @param isDoubleBuffered {@link javax.swing.JPanel#setDoubleBuffered(boolean)}
	 * @see #setMouseListener()
	 * @see {@link javax.swing.JPanel#JPanel(LayoutManager, boolean)}
	 */
	public PanelImagen( LayoutManager layout, boolean isDoubleBuffered )
	{
		super( layout, isDoubleBuffered );
		setMouseListener( );
	}
	
	// TODO Class dial
	@Override
	public void mouseClicked( MouseEvent e )
	{
		if( isCloseIn )
		{
			dialogClose.dispose( );
			isCloseIn = false;
		}
		else if( isBackIn )
		{
			dialogClose.dispose( );
			try
			{
				String dial = "com.dnarvaez27.CommOS.cliente.interfaz.dialogos.DialogoInicial";
				Class.forName( dial ).newInstance( );
			}
			catch( InstantiationException | IllegalAccessException | ClassNotFoundException e1 )
			{
				e1.printStackTrace( );
			}
			isBackIn = false;
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
		
	}
	
	@Override
	public void mouseMoved( MouseEvent e )
	{
		int x = e.getX( );
		int y = e.getY( );
		boolean alt = ( y >= 5 ) && ( y <= 15 );
		boolean wid = ( x >= ( getWidth( ) - 15 ) ) && ( x <= ( getWidth( ) - 5 ) );
		boolean widB = ( x >= 5 ) && ( x <= 15 );
		
		if( alt && wid )
		{
			setCursor( new Cursor( Cursor.HAND_CURSOR ) );
			isCloseIn = true;
			isBackIn = false;
		}
		else if( alt && widB )
		{
			setCursor( new Cursor( Cursor.HAND_CURSOR ) );
			isBackIn = true;
			isCloseIn = false;
		}
		else
		{
			setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
			isBackIn = false;
			isCloseIn = false;
		}
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
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		
		g.drawImage( imageBackground, 0, 0, getWidth( ), getHeight( ), this );
		g.drawImage( Imagenes.CLOSE.getImage( ), getWidth( ) - 15, 5, this );
		
		if( backButton )
		{
			g.drawImage( Imagenes.BACK.getImage( ), 5, 5, this );
		}
	}
	
	/**
	 * Determina si se muestra o no el botón de retroceso
	 *
	 * @param backButton True si se mostrará el botón. False de lo contrario
	 */
	public void setBackButton( boolean backButton )
	{
		this.backButton = backButton;
	}
	
	/**
	 * Determina la imagen del panel
	 *
	 * @param image Imagen del panel
	 * @see #setMouseListener( )
	 */
	public void setBackgroundImage( Image image )
	{
		imageBackground = image;
		setMouseListener( );
	}
	
	/**
	 * Asigna el Dialogo que se cerrará en el dispose
	 *
	 * @param dialog Dialogo que se cerrará
	 */
	public void setComponentOnDispose( JDialog dialog )
	{
		dialogClose = dialog;
	}
	
	/**
	 * Métodos:
	 * <ul>
	 * <li>addMouseMotionListener( this );
	 * <li>addMouseListener( this );
	 * </ul>
	 */
	private void setMouseListener( )
	{
		addMouseMotionListener( this );
		addMouseListener( this );
	}
	
	// public static void main( String[ ] args )
	// {
	// // JFT
	// JDialog d = new JDialog( );
	// d.setUndecorated( true );
	//
	// PanelImagen panelImagen = new PanelImagen( false );
	// try
	// {
	// panelImagen.setBackgroundImage( panelImagen.getToolkit( ).createImage( Imagenes.GIF_URL ) );
	// }
	// catch( Exception e )
	// {
	// e.printStackTrace( );
	// }
	//
	// panelImagen.setLayout( new BorderLayout( ) );
	// panelImagen.setComponentOnDispose( d );
	// panelImagen.setBorder( BorderFactory.createEmptyBorder( 50, 50, 50, 50 ) );
	//
	// d.add( panelImagen );
	//
	// d.pack( );
	// d.setLocationRelativeTo( null );
	// d.setVisible( true );
	// d.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
	// }
}