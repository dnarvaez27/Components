package com.dnarvaez27.componentes.containers.dialogs;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.dnarvaez27.componentes.recursos.ConstantesComponentes;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Colores;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Media;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Clase que modela un dialogo de información
 *
 * @author d.narvaez11
 */
public class DialogoInformacion extends JDialog implements ActionListener, KeyEventDispatcher
{
	private static final long serialVersionUID = -7478176338044959360L;
	
	/**
	 * Botón de aprobación del dialogo
	 */
	protected JButton aceptar;
	
	/**
	 * Boton de cancelación del dialogo
	 */
	protected JButton cancelar;
	
	/**
	 * Imagen del dialogo
	 */
	protected JLabel imagen;
	
	/**
	 * Verifica si la respuesta del dialogo es afirmativa o no
	 */
	protected boolean respuesta;
	
	/**
	 * Texto del dialogo
	 */
	protected JLabel texto;
	
	/**
	 * Constructor del dialogo con Icono
	 *
	 * @param icon Icono del dialogo
	 * @param title Titulo del dialogo
	 * @param pTexto Texto principal del dialogo
	 * @param pTextoMin Texto secundario del dialogo
	 * @param color Color Background del dialogo
	 * @see #DialogoInformacion(String, String, String, Color)
	 */
	public DialogoInformacion( ImageIcon icon, String title, String pTexto, String pTextoMin, Color color )
	{
		this( title, pTexto, pTextoMin, color );
		setIconImage( icon.getImage( ) );
	}
	
	/**
	 * Constructor del dialogo con Icono e Imagen
	 *
	 * @param icon Icono del dialogo
	 * @param title Titulo del dialogo
	 * @param pTexto Texto principal del dialogo
	 * @param pTextoMin Texto secundario del dialogo
	 * @param color Color del dialogo
	 * @param pImage Imagen del dialogo
	 * @see #DialogoInformacion(String, String, String, Color, ImageIcon)
	 */
	public DialogoInformacion( ImageIcon icon, String title, String pTexto, String pTextoMin, Color color, ImageIcon pImage )
	{
		this( title, pTexto, pTextoMin, color, pImage );
		setIconImage( icon.getImage( ) );
	}
	
	/**
	 * Constructor del dialogo básico, de color {@link Colores#GRIS }
	 *
	 * @param title Titulo del dialogo
	 * @param texto Texto del dialogo
	 * @see #DialogoInformacion(String, String, String, Color)
	 */
	public DialogoInformacion( String title, String texto )
	{
		this( title, texto, "", Colores.GRIS );
	}
	
	/**
	 * Constructor del dialogo básico
	 *
	 * @param title Titulo del dialogo
	 * @param texto Texto del dialogo
	 * @param color Color del dialogo
	 * @see #DialogoInformacion(String, String, String, Color)
	 */
	public DialogoInformacion( String title, String texto, Color color )
	{
		this( title, texto, "", color );
	}
	
	/**
	 * Constructor básico del dialogo
	 *
	 * @param title Titulo del dialogo
	 * @param pTexto Texto principal del dialogo
	 * @param pTextoMin Texto secundario del dialogo
	 * @param color Color del dialogo
	 */
	public DialogoInformacion( String title, String pTexto, String pTextoMin, Color color )
	{
		setTitle( title );
		
		JPanel contenedor = new JPanel( );
		contenedor.setBackground( color );
		contenedor.setLayout( new BorderLayout( ) );
		
		pTexto = pTexto.replace( "\n", "<br>" );
		pTextoMin = pTextoMin.replace( "\n", "<br>" );
		
		if( !pTextoMin.isEmpty( ) )
		{
			texto = new JLabel( "<html><center>" + pTexto + "<br><h5><center>" + pTextoMin + "</html>" );
		}
		else
		{
			texto = new JLabel( "<html><center>" + pTexto + "</html>" );
		}
		texto.setForeground( Colores.BLANCO );
		texto.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		texto.setHorizontalAlignment( SwingConstants.CENTER );
		
		JPanel boton = new JPanel( );
		boton.setBackground( null );
		
		aceptar = new JButton( ConstantesComponentes.Strings.ACEPTAR );
		aceptar.setBackground( Colores.BLANCO );
		aceptar.setForeground( Colores.GRIS );
		aceptar.setBorderPainted( false );
		aceptar.setFocusPainted( false );
		aceptar.setContentAreaFilled( false );
		aceptar.setOpaque( true );
		aceptar.setCursor( Media.HAND );
		aceptar.setActionCommand( ConstantesComponentes.Strings.ACEPTAR );
		aceptar.addActionListener( this );
		
		cancelar = new JButton( ConstantesComponentes.Strings.CANCELAR );
		cancelar.setBackground( Colores.BLANCO );
		cancelar.setForeground( Colores.GRIS );
		cancelar.setBorderPainted( false );
		cancelar.setFocusPainted( false );
		cancelar.setContentAreaFilled( false );
		cancelar.setOpaque( true );
		cancelar.setCursor( Media.HAND );
		cancelar.setActionCommand( ConstantesComponentes.Strings.CANCELAR );
		cancelar.addActionListener( this );
		
		boton.add( aceptar );
		boton.add( cancelar );
		
		contenedor.add( texto, BorderLayout.CENTER );
		contenedor.add( boton, BorderLayout.SOUTH );
		
		add( contenedor );
		
		pack( );
		setModal( true );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setResizable( false );
	}
	
	/**
	 * Constructor del dialogo con Imagen
	 *
	 * @param title Titulo del dialogo
	 * @param pTexto Texto principal del dialogo
	 * @param pTextoMin Texto secundario del dialogo
	 * @param color Color del dialogo
	 * @param pImage Imagen del dialogo
	 */
	public DialogoInformacion( String title, String pTexto, String pTextoMin, Color color, ImageIcon pImage )
	{
		setTitle( title );
		
		JPanel contenedor = new JPanel( );
		contenedor.setBackground( color );
		contenedor.setLayout( new BorderLayout( ) );
		
		imagen = new JLabel( pImage );
		imagen.setBackground( null );
		imagen.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		
		pTexto = pTexto.replace( "\n", "<br>" );
		pTextoMin = pTextoMin.replace( "\n", "<br>" );
		
		if( !pTextoMin.isEmpty( ) )
		{
			texto = new JLabel( "<html><center>" + pTexto + "<br><h5><center>" + pTextoMin + "</html>" );
		}
		else
		{
			texto = new JLabel( "<html><center>" + pTexto + "</html>" );
		}
		texto.setForeground( Colores.BLANCO );
		texto.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		texto.setHorizontalAlignment( SwingConstants.CENTER );
		
		JPanel boton = new JPanel( );
		boton.setBackground( null );
		
		aceptar = new JButton( ConstantesComponentes.Strings.ACEPTAR );
		aceptar.setBackground( Colores.BLANCO );
		aceptar.setForeground( Colores.GRIS );
		aceptar.setBorderPainted( false );
		aceptar.setFocusPainted( false );
		aceptar.setContentAreaFilled( false );
		aceptar.setOpaque( true );
		aceptar.setCursor( Media.HAND );
		aceptar.setActionCommand( ConstantesComponentes.Strings.ACEPTAR );
		aceptar.addActionListener( this );
		
		cancelar = new JButton( ConstantesComponentes.Strings.CANCELAR );
		cancelar.setBackground( Colores.BLANCO );
		cancelar.setForeground( Colores.GRIS );
		cancelar.setBorderPainted( false );
		cancelar.setFocusPainted( false );
		cancelar.setContentAreaFilled( false );
		cancelar.setOpaque( true );
		cancelar.setCursor( Media.HAND );
		cancelar.setActionCommand( ConstantesComponentes.Strings.CANCELAR );
		cancelar.addActionListener( this );
		
		boton.add( aceptar );
		boton.add( cancelar );
		
		contenedor.add( imagen, BorderLayout.NORTH );
		contenedor.add( texto, BorderLayout.CENTER );
		contenedor.add( boton, BorderLayout.SOUTH );
		
		add( contenedor );
		
		pack( );
		setModal( true );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setResizable( false );
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		
		if( comando.equals( ConstantesComponentes.Strings.ACEPTAR ) )
		{
			respuesta = true;
		}
		dispose( );
	}
	
	@Override
	public boolean dispatchKeyEvent( KeyEvent e )
	{
		if( e.getID( ) == KeyEvent.KEY_PRESSED )
		{
			if( e.getKeyChar( ) == KeyEvent.VK_ENTER )
			{
				dispose( );
			}
		}
		return false;
	}
	
	/**
	 * Muestra el dialogo de información
	 *
	 * @return El estado de confirmación: True si el usuario aprobó, False de lo contrario
	 * @see {@link #respuesta}
	 */
	public boolean mostrarDialogo( )
	{
		if( !cancelar.isVisible( ) )
		{
			KeyboardFocusManager.getCurrentKeyboardFocusManager( ).addKeyEventDispatcher( this );
		}
		setVisible( true );
		
		return respuesta;
	}
	
	/**
	 * Configura los botones del dialogo.<br>
	 * Si alguno de los parametros es un string vacio, el botón no se muestra
	 *
	 * @param approve Texto de aprobación
	 * @param cancel Texto de cancelación
	 * @return El Dialogo
	 */
	public DialogoInformacion setButtonsText( String approve, String cancel )
	{
		if( approve.isEmpty( ) )
		{
			aceptar.setVisible( false );
		}
		if( cancel.isEmpty( ) )
		{
			cancelar.setVisible( false );
		}
		aceptar.setText( approve );
		cancelar.setText( cancel );
		
		return this;
	}
	
	/**
	 * Configura el foreground del Dialogo.<br>
	 * Se configura el color del texto del dialogo
	 */
	@Override
	public void setForeground( Color color )
	{
		super.setForeground( color );
		texto.setForeground( color );
	}
	
	/**
	 * Configura el texto del dialogo
	 *
	 * @param pTexto Texto del dialogo
	 */
	public void setText( String pTexto )
	{
		pTexto = pTexto.replace( "\n", "<br>" );
		texto.setText( "<html><center>" + pTexto + "</html>" );
	}
}