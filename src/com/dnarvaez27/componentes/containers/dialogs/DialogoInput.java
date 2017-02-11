package com.dnarvaez27.componentes.containers.dialogs;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.dnarvaez27.componentes.buttons.ButtonTip;
import com.dnarvaez27.componentes.containers.ScrollColor;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Colores;
import com.dnarvaez27.componentes.text.AreaHint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que modela un dialogo con una entrada de texto
 *
 * @author d.narvaez11
 */
public class DialogoInput extends JDialog
{
	private static final long serialVersionUID = 5461934912199421055L;
	
	/**
	 * Botón de opción de aprobacion
	 */
	private ButtonTip btnAceptar;
	
	/**
	 * Botón de opción de cancelacion
	 */
	private ButtonTip btnCancelar;
	
	/**
	 * Verifica el estado de la confirmacion del usuario
	 */
	private boolean confirmacion;
	
	/**
	 * Verifica si la entrada de texto debe ser solo Double
	 */
	private boolean esDouble;
	
	/**
	 * Field de input del dialogo
	 */
	private AreaHint field;
	
	/**
	 * Input que el usuario ha escrito
	 */
	private String input;
	
	/**
	 * Label del Mensaje del Dialogo
	 */
	private JLabel lblMensaje;
	
	/**
	 * Verifica si el input debe ser solo de numeros
	 */
	private boolean numbersOnly;
	
	/**
	 * Scroll del input
	 */
	private ScrollColor scroll;
	
	/**
	 * Constructor del dialogo
	 *
	 * @param titulo Titulo del dialogo
	 * @param mensaje Mensaje del dialogo
	 * @param color Color del dialogo
	 * @param singleLine True, si el input deberá permitir texto de una linea. False de lo contrario<br>
	 *        Si es True, se activa el un actionPerformed para verificar la acción del input
	 */
	public DialogoInput( String titulo, String mensaje, Color color, boolean singleLine )
	{
		setTitle( titulo );
		JPanel contenedor = new JPanel( );
		contenedor.setBackground( color );
		contenedor.setLayout( new BorderLayout( ) );
		
		mensaje = mensaje.replace( "\n", "<br>" );
		
		lblMensaje = new JLabel( "<html><center>" + mensaje + "</html>" );
		lblMensaje.setHorizontalAlignment( SwingConstants.CENTER );
		lblMensaje.setForeground( Colores.BLANCO );
		lblMensaje.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		
		field = new AreaHint( "" );
		field.setBorder( BorderFactory.createEmptyBorder( 5, 10, 5, 10 ) );
		field.setFont( field.getFont( ).deriveFont( Font.BOLD ) );
		field.setLineWrap( singleLine ? false : true );
		field.setWrapStyleWord( singleLine ? false : true );
		field.setSelectedTextColor( Colores.BLANCO );
		field.setSelectionColor( Colores.GRIS );
		field.setMinimumSize( new Dimension( 100, 40 ) );
		field.addKeyListener( new KeyAdapter( )
		{
			@Override
			public void keyPressed( KeyEvent e )
			{
				if( singleLine )
				{
					if( e.getKeyChar( ) == KeyEvent.VK_ENTER )
					{
						e.consume( );
						confirmacion = true;
						input = field.getText( );
						dispose( );
					}
				}
			}
			
			@Override
			public void keyTyped( KeyEvent e )
			{
				if( numbersOnly )
				{
					char c = e.getKeyChar( );
					
					boolean range = ( c >= '0' ) && ( c <= '9' );
					boolean space = ( c == KeyEvent.VK_BACK_SPACE );
					boolean delete = ( c == KeyEvent.VK_DELETE );
					boolean punto = ( c == KeyEvent.VK_PERIOD );
					
					boolean doubleStat = ( field.getText( ).isEmpty( ) && punto ) || ( field.getText( ).contains( "." ) && punto );
					boolean point = !esDouble && punto;
					boolean config = !( range || space || delete || punto );
					
					if( point || ( esDouble && doubleStat ) || config )
					{
						e.consume( );
					}
				}
			}
		} );
		
		scroll = new ScrollColor( field, 2, Colores.BLANCO, color );
		scroll.setBorder( BorderFactory.createMatteBorder( 4, 10, 4, 10, color ) );
		scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		JPanel panelBotones = new JPanel( );
		panelBotones.setBackground( null );
		
		btnAceptar = new ButtonTip( ConstantesComponentes.Strings.ACEPTAR );
		btnAceptar.setBackground( Colores.BLANCO );
		btnAceptar.setForeground( Colores.GRIS );
		btnAceptar.addActionListener( a ->
		{
			confirmacion = true;
			input = field.getText( );
			dispose( );
		} );
		
		btnCancelar = new ButtonTip( ConstantesComponentes.Strings.CANCELAR );
		btnCancelar.setBackground( Colores.BLANCO );
		btnCancelar.setForeground( Colores.GRIS );
		btnCancelar.addActionListener( a -> dispose( ) );
		
		panelBotones.add( btnAceptar );
		panelBotones.add( btnCancelar );
		
		contenedor.add( lblMensaje, BorderLayout.NORTH );
		contenedor.add( scroll, BorderLayout.CENTER );
		contenedor.add( panelBotones, BorderLayout.SOUTH );
		
		add( contenedor );
		
		setModal( true );
		pack( );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setLocationRelativeTo( null );
	}
	
	/**
	 * Retorna el input que ha escrito el usuario
	 *
	 * @return Input que ha escrito el usuario
	 */
	public String getInput( )
	{
		return input;
	}
	
	// public static void main( String[ ] args )
	// {
	// // JFT
	// DialogoInput di = new DialogoInput( "TITLE", "INPUT", Color.DARK_GRAY, true );
	// di.setNumeric( true, true );
	// di.setHint( "Hola" );
	// di.btnAceptar.requestFocus( );
	// di.mostrarDialogo( );
	// System.out.println( di.getNumberInput( ) );
	// }
	
	/**
	 * Retorna el número que ha escrito el usuario.<br>
	 * Solo si el dialogo acepta solo números
	 * 
	 * @return Número que ha escrito el usuario.
	 *         {@link Double#POSITIVE_INFINITY} cuando no esta habilitado solo para números
	 */
	public Number getNumberInput( )
	{
		if( numbersOnly && !input.isEmpty( ) )
		{
			return esDouble ? Double.parseDouble( input ) : Integer.parseInt( input );
		}
		return Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Muestra el dialogo<br>
	 * Devuelve el estado de la confirmacion
	 *
	 * @return Estado de la confirmacion
	 * @see #confirmacion
	 */
	public boolean mostrarDialogo( )
	{
		setVisible( true );
		return confirmacion;
	}
	
	/**
	 * Configura el texto de botón de aprobación
	 *
	 * @param stringApproveOption Texto para el boton de aprobacion
	 * @see #btnAceptar
	 */
	public void setApproveOption( String stringApproveOption )
	{
		btnAceptar.setText( stringApproveOption );
	}
	
	/**
	 * Configura el "Hint" para el field del input
	 *
	 * @param hint Hint del field
	 * @see #field
	 */
	public void setHint( String hint )
	{
		field.setHint( hint );
	}
	
	/**
	 * Establece si el field deberia permitir o no valores no númericos
	 *
	 * @param numbersOnly True para solo permitir numeros. False de lo contrario
	 * @param isDouble True si el input numerico es double. False de lo contrario
	 */
	public void setNumeric( boolean numbersOnly, boolean isDouble )
	{
		this.numbersOnly = numbersOnly;
		esDouble = isDouble;
	}
	
	/**
	 * Establece la dimension del dialogo<br>
	 * La altura minima es 162
	 */
	@Override
	public void setSize( Dimension dimension )
	{
		if( dimension.getHeight( ) >= 162 )
		{
			super.setSize( dimension );
		}
		else
		{
			pack( );
			Dimension dimension2 = new Dimension( dimension.width, getHeight( ) );
			super.setSize( dimension2 );
		}
		setLocationRelativeTo( null );
	}
}