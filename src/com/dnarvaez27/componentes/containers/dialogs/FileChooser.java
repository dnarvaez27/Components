package com.dnarvaez27.componentes.containers.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.dnarvaez27.componentes.buttons.ButtonTip;
import com.dnarvaez27.componentes.containers.Popup;
import com.dnarvaez27.componentes.containers.ScrollColor;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Colores;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Imagenes;
import com.dnarvaez27.componentes.recursos.ConstantesComponentes.Media;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileChooser extends JDialog
{
	/**
	 * Componente que representa un File
	 *
	 * @author d.narvaez11
	 * @see {@link File}<br>
	 *      {@link ButtonTip}
	 */
	private class FileComponent extends ButtonTip
	{
		private static final long serialVersionUID = 6292678077358146006L;
		
		/**
		 * File del componente
		 */
		private File file;
		
		/**
		 * Instancia, usada solo para propositos internos en:
		 * <ul>
		 * <li>{@link MouseListener}
		 * <li>{@link Popup}
		 * </ul>
		 */
		private final FileComponent instance;
		
		/**
		 * {@link Popup} con el menu del Componente
		 */
		private Popup popup;
		
		/**
		 * Estado de seleccion del componente
		 */
		private boolean selected;
		
		private FileComponent( )
		{
			instance = this;
		}
		
		/**
		 * Inicializa el {@link Popup} con el menu del componente
		 *
		 * @param x Posición en x del Popup
		 * @param y Posición en y del Popup
		 * @param bg Background del Popup
		 * @param fg Foreground del Popup
		 */
		public void inicializarPopup( int x, int y, Color bg, Color fg )
		{
			popup = new Popup( instance, new PanelOpciones( file, bg, fg ), x, y );
		}
		
		/**
		 * Configura el borde del Componente<br>
		 * Se agrega por defecto un {@link BorderFactory#createEmptyBorder(int, int, int, int)} de 5 px
		 */
		@Override
		public void setBorder( Border border )
		{
			super.setBorder( BorderFactory.createCompoundBorder( border, BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
		}
		
		/**
		 * Configura el background y foreground para un estado de seleccion dado por parametro
		 *
		 * @param selected True si marcará como seleccionado, False de lo contrario
		 * @param showsOnField True si se mostrará el nombre del archivo en el {@link PanelInferior#fieldSelected},
		 *        False de lo contrario
		 */
		public void setSeleccionado( boolean selected, boolean showsOnField )
		{
			this.selected = selected;
			
			if( selected )
			{
				selectedFile = file;
				if( showsOnField )
				{
					panelInferior.fieldSelected.setText( file != null ? file.getName( ) : "" );
				}
				
				setBackground( Colores.GRIS );
				setForeground( Colores.BLANCO );
				setBorder( BorderFactory.createLineBorder( Colores.GRIS ) );
			}
			else
			{
				setBackground( null );
				setForeground( Colores.GRIS );
				setBorder( BorderFactory.createLineBorder( Colores.BLANCO ) );
			}
		}
	}
	
	/**
	 * Panel Inferior del Dialogo<br>
	 * Contiene:
	 * <ul>
	 * <li>Field {@link PanelInferior#fieldSelected}
	 * <li>Boton {@link PanelInferior#aceptar}
	 * <li>Boton {@link PanelInferior#cancelar}
	 * </ul>
	 *
	 * @author d.narvaez11
	 */
	private class PanelInferior extends JPanel
	{
		private static final long serialVersionUID = -2564437699276062630L;
		
		/**
		 * Botón de aprobación del Dialogo
		 *
		 * @see ButtonTip
		 */
		private ButtonTip aceptar;
		
		/**
		 * Boton de cancelación del Dialogo
		 *
		 * @see ButtonTip
		 */
		private ButtonTip cancelar;
		
		/**
		 * Field contenedor del nombre del archivo seleccionado
		 */
		private JTextField fieldSelected;
		
		/**
		 * Constructor del panelInferior
		 */
		public PanelInferior( )
		{
			setLayout( new BorderLayout( ) );
			setBackground( Colores.GRIS );
			
			JPanel panelBotones = new JPanel( );
			panelBotones.setBackground( null );
			
			cancelar = new ButtonTip( ConstantesComponentes.Strings.CANCELAR );
			cancelar.setBackground( Colores.BLANCO );
			cancelar.setForeground( Colores.GRIS );
			cancelar.addActionListener( a ->
			{
				selectedFile = null;
				hideBut( null );
				confirmacion = OPCION_CANCELAR;
				dispose( );
			} );
			
			aceptar = new ButtonTip( ConstantesComponentes.Strings.ACEPTAR );
			aceptar.setBackground( Colores.BLANCO );
			aceptar.setForeground( Colores.GRIS );
			aceptar.addActionListener( a ->
			{
				hideBut( null );
				confirmacion = OPCION_ACEPTAR;
				
				dispose( );
			} );
			
			panelBotones.add( aceptar );
			panelBotones.add( cancelar );
			
			JPanel panelField = new JPanel( );
			panelField.setLayout( new BorderLayout( ) );
			panelField.setBorder( BorderFactory.createEmptyBorder( 0, 4, 0, 4 ) );
			panelField.setBackground( null );
			
			fieldSelected = new JTextField( );
			fieldSelected.setSelectedTextColor( Colores.BLANCO );
			fieldSelected.setSelectionColor( Colores.GRIS );
			fieldSelected.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Colores.GRIS ), BorderFactory.createEmptyBorder( 2, 5, 2, 5 ) ) );
			fieldSelected.setFont( getFont( ).deriveFont( Font.BOLD ) );
			fieldSelected.addFocusListener( new FocusAdapter( )
			{
				@Override
				public void focusGained( FocusEvent e )
				{
					hideBut( null );
				}
			} );
			fieldSelected.addKeyListener( new KeyAdapter( )
			{
				@Override
				public void keyTyped( KeyEvent e )
				{
					if( !fieldSelected.getText( ).isEmpty( ) )
					{
						highLight( fieldSelected.getText( ), false );
					}
				}
			} );
			
			JLabel labelNombre = new JLabel( "Nombre" );
			labelNombre.setBorder( BorderFactory.createEmptyBorder( 0, 5, 0, 5 ) );
			labelNombre.setForeground( Colores.BLANCO );
			
			panelField.add( labelNombre, BorderLayout.WEST );
			panelField.add( fieldSelected, BorderLayout.CENTER );
			
			add( panelField, BorderLayout.NORTH );
			add( panelBotones, BorderLayout.SOUTH );
		}
	}
	
	/**
	 * Panel Menu del {@link Popup} del {@link FileComponent}
	 *
	 * @author d.narvaez11
	 */
	private class PanelOpciones extends JPanel
	{
		private static final long serialVersionUID = 5810144500892917277L;
		
		/**
		 * Botón para eliminar un File
		 */
		private ButtonTip eliminar;
		
		/**
		 * Botón para renombrar un File
		 */
		private ButtonTip rename;
		
		/**
		 * Constructor del PanelOpciones
		 *
		 * @param file File respectivo al menu
		 * @param bg Background del Menu
		 * @param fg Foreground del Menu
		 */
		public PanelOpciones( File file, Color bg, Color fg )
		{
			setLayout( new BorderLayout( ) );
			setBackground( Colores.GRIS );
			setBorder( BorderFactory.createLineBorder( Colores.BLANCO ) );
			
			JPanel contenedor = new JPanel( );
			contenedor.setLayout( new BoxLayout( contenedor, BoxLayout.PAGE_AXIS ) );
			contenedor.setBackground( null );
			
			ButtonTip labelFile = new ButtonTip( file.getName( ) );
			labelFile.setBorderPainted( true );
			labelFile.setForeground( fg );
			labelFile.setBackground( bg );
			labelFile.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder( 0, 0, 1, 0, Colores.BLANCO ), BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
			labelFile.setCursor( Media.DEFAULT );
			
			rename = new ButtonTip( ConstantesComponentes.Strings.RENAME );
			rename.setForeground( Colores.BLANCO );
			rename.setBackground( null );
			rename.addActionListener( a ->
			{
				hideBut( null );
				String[ ] fields = file.getName( ).split( "\\.(?=[^\\.]+$)" );
				DialogoInput dialogoInput = new DialogoInput( ConstantesComponentes.Strings.RENAME, ConstantesComponentes.Strings.INGRESE_NUEVO_NOMBRE, Colores.GRIS, true );
				dialogoInput.setHint( ( file.isDirectory( ) ? file.getName( ) : fields[ 0 ] ) );
				boolean confirmacion = dialogoInput.mostrarDialogo( );
				if( confirmacion )
				{
					String nuevoNombre = dialogoInput.getInput( );
					String ext = !file.isDirectory( ) && ( fields.length > 1 ) ? "." + fields[ 1 ] : "";
					File dest = new File( currentDir + System.getProperty( "file.separator" ) + nuevoNombre + ( ext.isEmpty( ) ? "" : ext ) );
					boolean eNombre = file.getName( ).equals( dest.getName( ) );
					boolean modifico = file.renameTo( dest );
					if( !eNombre && modifico )
					{
						poblar( currentDir.getAbsolutePath( ) );
					}
					else if( !eNombre )
					{
						new DialogoInformacion( ConstantesComponentes.Strings.ERROR, ConstantesComponentes.Strings.NO_SE_RENAME, "", Colores.ROJO ).setButtonsText( ConstantesComponentes.Strings.ACEPTAR, "" ).mostrarDialogo( );
					}
				}
			} );
			
			eliminar = new ButtonTip( ConstantesComponentes.Strings.ELIMINAR );
			eliminar.setForeground( Colores.BLANCO );
			eliminar.setBackground( null );
			eliminar.addActionListener( a ->
			{
				hideBut( null );
				DialogoInformacion dialogoInformacion = new DialogoInformacion( ConstantesComponentes.Strings.ELIMINAR, ConstantesComponentes.Strings.ELIMINAR_ARCHIVO + " " + file.getName( ) + "?", "", bg );
				if( file.isDirectory( ) )
				{
					dialogoInformacion.setText( ConstantesComponentes.Strings.ELIMINAR_FOLDER + " " + file.getName( ) + " " + ConstantesComponentes.Strings.Y_ELIMINAR_ARCHIVOS );
					dialogoInformacion.setForeground( Colores.GRIS );
				}
				boolean confirmacion = dialogoInformacion.setButtonsText( ConstantesComponentes.Strings.ELIMINAR, ConstantesComponentes.Strings.CANCELAR ).mostrarDialogo( );
				if( confirmacion )
				{
					eliminar( file );
				}
			} );
			
			contenedor.add( rename );
			contenedor.add( eliminar );
			
			add( labelFile, BorderLayout.NORTH );
			add( contenedor, BorderLayout.CENTER );
		}
		
		/**
		 * Elimina un File del Sistema<br>
		 * Elimina tanto archivos como directorios
		 *
		 * @param file File a eliminar
		 */
		private void eliminar( File file )
		{
			if( file.isDirectory( ) )
			{
				for( File f : file.listFiles( ) )
				{
					eliminar( f );
				}
				file.delete( );
			}
			else
			{
				file.delete( );
			}
			poblar( currentDir.getAbsolutePath( ) );
		}
	}
	
	/**
	 * Panel Superior del dialogo<br>
	 * Contiene: <br>
	 * <li>Boton de retorno
	 * <li>Field de ruta
	 * <li>Boton de nueva carpeta
	 * <li>Boton de Home
	 *
	 * @author d.narvaez11
	 */
	private class PanelSuperior extends JPanel
	{
		private static final long serialVersionUID = 8061140812096934109L;
		
		/**
		 * Boton de retorno al directorio superior del File actual
		 */
		private ButtonTip back;
		
		/**
		 * Field con el AbsolutePath del File actual
		 */
		private JTextField fieldAbsolutePath;
		
		/**
		 * Boton Home. Muestra el directorio Home del Usuario
		 *
		 * @see {@link FileChooser#defaultPath}
		 */
		private ButtonTip home;
		
		/**
		 * Botón para crear un nuevo directorio en el actual
		 */
		private ButtonTip newFolder;
		
		/**
		 * Constructor del PanelSuperior
		 */
		public PanelSuperior( )
		{
			setLayout( new BorderLayout( ) );
			setBackground( Colores.GRIS );
			
			back = new ButtonTip( Imagenes.BACK );
			back.setBackground( null );
			back.setPreferredSize( new Dimension( 40, 40 ) );
			back.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					hideBut( null );
					File f = currentDir.getParentFile( );
					if( ( f != null ) && f.isDirectory( ) )
					{
						if( f.getAbsolutePath( ).endsWith( "." ) )
						{
							poblar( f.getParentFile( ).getAbsolutePath( ) );
						}
						else
						{
							poblar( f.getAbsolutePath( ) );
						}
						hideBut( null );
					}
				}
			} );
			back.setToolTipText( ConstantesComponentes.Strings.ATRAS );
			back.cambiarColorTip( Colores.GRIS, Colores.BLANCO );
			back.configurar( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Colores.BLANCO ), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ), CENTER_ALIGNMENT );
			
			home = new ButtonTip( Imagenes.HOME );
			home.setBackground( null );
			home.setPreferredSize( new Dimension( 40, 40 ) );
			home.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					hideBut( null );
					String s = System.getProperty( "user.home" );
					poblar( s );
				}
			} );
			home.setToolTipText( new File( System.getProperty( "user.home" ) ).getName( ) );
			home.cambiarColorTip( Colores.GRIS, Colores.BLANCO );
			home.configurar( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Colores.BLANCO ), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ), CENTER_ALIGNMENT );
			
			newFolder = new ButtonTip( Imagenes.NEW_FOLDER );
			newFolder.setBackground( null );
			newFolder.setPreferredSize( new Dimension( 40, 40 ) );
			newFolder.setToolTipText( ConstantesComponentes.Strings.NUEVA_CARPETA );
			newFolder.cambiarColorTip( Colores.AMARILLO, Colores.GRIS );
			newFolder.configurar( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Colores.GRIS ), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ), CENTER_ALIGNMENT );
			newFolder.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					hideBut( null );
					
					String currPath = currentDir.getAbsolutePath( );
					File f = new File( currPath + System.getProperty( "file.separator" ) + ConstantesComponentes.Strings.NUEVA_CARPETA );
					if( !f.exists( ) )
					{
						f.mkdirs( );
						poblar( currPath );
					}
					else
					{
						int contador = 1;
						for( ;; )
						{
							f = new File( currPath + System.getProperty( "file.separator" ) + ConstantesComponentes.Strings.NUEVA_CARPETA + " (" + contador++ + ") " );
							if( !f.exists( ) )
							{
								f.mkdirs( );
								poblar( currPath );
								break;
							}
						}
					}
				}
			} );
			
			fieldAbsolutePath = new JTextField( );
			fieldAbsolutePath.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder( 10, 5, 10, 5, Colores.GRIS ), BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
			fieldAbsolutePath.setSelectionColor( Colores.GRIS );
			fieldAbsolutePath.setSelectedTextColor( Colores.BLANCO );
			fieldAbsolutePath.setFont( fieldAbsolutePath.getFont( ).deriveFont( Font.BOLD ) );
			fieldAbsolutePath.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					File f = new File( fieldAbsolutePath.getText( ) );
					if( f.exists( ) )
					{
						poblar( f.getAbsolutePath( ) );
					}
					else
					{
						DialogoInformacion dialogoInformacion = new DialogoInformacion( ConstantesComponentes.Strings.ERROR, ConstantesComponentes.Strings.NO_EXISTE_DIR, "", Colores.ROJO );
						dialogoInformacion.setButtonsText( ConstantesComponentes.Strings.ACEPTAR, "" );
						dialogoInformacion.mostrarDialogo( );
					}
				}
			} );
			fieldAbsolutePath.addFocusListener( new FocusListener( )
			{
				@Override
				public void focusGained( FocusEvent e )
				{
					hideBut( null );
				}
				
				@Override
				public void focusLost( FocusEvent e )
				{
					
				}
			} );
			
			JPanel panelSuperiorCentro = new JPanel( );
			panelSuperiorCentro.setLayout( new FlowLayout( ) );
			panelSuperiorCentro.setBackground( null );
			
			panelSuperiorCentro.add( newFolder );
			panelSuperiorCentro.add( home );
			
			add( fieldAbsolutePath, BorderLayout.CENTER );
			add( back, BorderLayout.WEST );
			add( panelSuperiorCentro, BorderLayout.EAST );
		}
	}
	
	public static final int DIRECTORIES_AND_FILES = 123;
	
	public static final int DIRECTORIES_ONLY = 987;
	
	/**
	 * Constante que define la opcion de aprobación para el dialogo;
	 */
	public static final int OPCION_ACEPTAR = 1;
	
	/**
	 * Constante que define la opcion de cancelacion para el dialogo;
	 */
	public static final int OPCION_CANCELAR = 0;
	
	private static final long serialVersionUID = -9218206688345284147L;
	
	/**
	 * Panel en el que se muestran los archivos del directorio
	 */
	private JPanel archivos;
	
	/**
	 * Guarda la informacion de la opcion elegida por el usuario
	 */
	private int confirmacion;
	
	/**
	 * Directorio actual
	 */
	private File currentDir;
	
	/**
	 * Ruta predeterminada de carga <br>
	 * Si no se especifica se asume la propiedad "user.home"
	 *
	 * @see {@link System#getProperty(String)}
	 */
	private String defaultPath;
	
	/**
	 * Componentes de Archivos mostrados<br>
	 *
	 * @see {@link FileComponent}
	 */
	private ArrayList<FileComponent> fileComponents;
	
	/**
	 * KeyEventDispatcher para la escucha de eventos de teclado
	 */
	private KeyEventDispatcher keyEventDispatcher;
	
	/**
	 * Panel inferior del dialogo. <br>
	 *
	 * @see PanelInferior
	 */
	private PanelInferior panelInferior;
	
	/**
	 * Panel superior del dialogo <br>
	 *
	 * @see {@link PanelSuperior}
	 */
	private PanelSuperior panelSuperior;
	
	/**
	 * Scroll para los archivos
	 */
	private ScrollColor scroll;
	
	/**
	 * Archivo seleccionado
	 */
	private File selectedFile;
	
	private int tipoArchivos;
	
	/**
	 * Texto seleccionado para el KeyEventDispatcher
	 */
	private String typedHint;
	
	/**
	 * Construye un {@link #FileChooser(String)} con la ruta predeterminada de usuario
	 *
	 * @see {@link #defaultPath}<br>
	 */
	public FileChooser( )
	{
		this( System.getProperty( "user.home" ), DIRECTORIES_AND_FILES );
	}
	
	/**
	 * Construye un FileChooser con una ruta dada por parámetro.
	 * 
	 * @param path Ruta de la carpeta
	 */
	public FileChooser( String path )
	{
		this( path, DIRECTORIES_AND_FILES );
	}
	
	/**
	 * Constuye un FileChooser con una ruta dada por parametro y un tipo de archivos a mostrar
	 * 
	 * @param path Ruta de la carpeta
	 * @param tipoArchivos Tipo de archivos a mostrar
	 * @see #DIRECTORIES_AND_FILES
	 * @see #DIRECTORIES_ONLY
	 */
	public FileChooser( String path, int tipoArchivos )
	{
		typedHint = new String( );
		this.tipoArchivos = tipoArchivos;
		
		if( !new File( path ).exists( ) )
		{
			throw new NullPointerException( "El directorio no existe" );
		}
		
		setLayout( new BorderLayout( ) );
		
		defaultPath = path;
		
		fileComponents = new ArrayList<>( );
		
		archivos = new JPanel( );
		archivos.setBackground( Color.WHITE );
		archivos.setBorder( BorderFactory.createEmptyBorder( 0, 20, 0, 20 ) );
		archivos.setLayout( new BorderLayout( ) );
		archivos.addMouseListener( new MouseAdapter( )
		{
			@Override
			public void mouseReleased( MouseEvent e )
			{
				hideBut( null );
				seleccionar( null );
				selectedFile = null;
			}
		} );
		
		archivos.add( new JLabel( "No hay archivos" ), BorderLayout.CENTER );
		
		panelInferior = new PanelInferior( );
		
		panelSuperior = new PanelSuperior( );
		
		scroll = new ScrollColor( archivos, 2, Colores.BLANCO, Colores.GRIS );
		scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		
		poblar( );
		
		add( panelSuperior, BorderLayout.NORTH );
		add( scroll, BorderLayout.CENTER );
		add( panelInferior, BorderLayout.SOUTH );
		
		keyEventDispatcher = new KeyEventDispatcher( )
		{
			@Override
			public boolean dispatchKeyEvent( KeyEvent e )
			{
				if( e.getID( ) == KeyEvent.KEY_TYPED )
				{
					typedHint += e.getKeyChar( );
					highLight( typedHint, true );
				}
				
				new Thread( "Timer" )
				{
					@Override
					public void run( )
					{
						try
						{
							sleep( 1000 );
							typedHint = "";
						}
						catch( InterruptedException e )
						{
							e.printStackTrace( );
						}
					}
				}.start( );
				
				return false;
			}
		};
		
		key( );
		
		panelInferior.cancelar.requestFocus( );
		
		setSize( 550, 380 );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setModal( true );
	}
	
	/**
	 * Constructo del FileComponente de un directorio dado por parametro
	 *
	 * @param file File que sera representador por el FileComponent
	 * @param folder Not Used
	 */
	public static FileComponent diectoryComponent( FileChooser chooser, File file )
	{
		FileComponent fc = chooser.new FileComponent( );
		fc.file = file;
		
		fc.setBackground( null );
		fc.setText( file.getName( ) );
		fc.setIcon( Imagenes.FOLDER );
		fc.setHorizontalAlignment( SwingConstants.LEFT );
		fc.setBorderPainted( true );
		fc.setBorder( BorderFactory.createLineBorder( Colores.BLANCO ) );
		fc.setPreferredSize( new Dimension( 200, 40 ) );
		
		fc.addMouseListener( new MouseAdapter( )
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				chooser.panelInferior.fieldSelected.getFocusListeners( )[ 0 ].focusLost( null );
				
				if( e.getButton( ) == MouseEvent.BUTTON3 )
				{
					if( ( fc.popup == null ) || !fc.popup.isShowing( ) )
					{
						fc.inicializarPopup( e.getXOnScreen( ), e.getYOnScreen( ), Colores.AMARILLO, Colores.GRIS );
						fc.popup.show( );
						chooser.hideBut( fc.instance );
					}
					else
					{
						fc.popup.hide( );
					}
				}
				else
				{
					chooser.hideBut( null );
					
					int count = e.getClickCount( );
					if( count >= 2 )
					{
						if( file.isDirectory( ) )
						{
							chooser.poblar( file.getAbsolutePath( ) );
						}
						else
						{
							chooser.selectedFile = file;
							chooser.seleccionar( chooser.selectedFile );
						}
					}
					else
					{
						chooser.seleccionar( file );
						chooser.selectedFile = file;
					}
				}
			}
			
			@Override
			public void mouseReleased( MouseEvent e )
			{
				chooser.panelInferior.fieldSelected.transferFocus( );
			}
		} );
		return fc;
	}
	
	/**
	 * Constructor del FileComponent con un archivo dado por parametro
	 *
	 * @param file File que sera representado por el FileComponent
	 */
	public static FileComponent fileComponent( FileChooser chooser, File file )
	{
		FileComponent fc = chooser.new FileComponent( );
		fc.file = file;
		
		fc.setBackground( null );
		fc.setText( file.getName( ) );
		fc.setIcon( Imagenes.FILE );
		fc.setHorizontalAlignment( SwingConstants.LEFT );
		fc.setBorderPainted( true );
		fc.setBorder( BorderFactory.createLineBorder( Colores.BLANCO ) );
		fc.setPreferredSize( new Dimension( 200, 40 ) );
		
		fc.addActionListener( a ->
		{
			chooser.selectedFile = file;
			chooser.seleccionar( chooser.selectedFile );
			//
			// try
			// {
			// Desktop.getDesktop( ).open( selectedFile );
			// }
			// catch( IOException e1 )
			// {
			// e1.printStackTrace( );
			// }
		} );
		
		fc.addMouseListener( new MouseAdapter( )
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				chooser.panelInferior.fieldSelected.getFocusListeners( )[ 0 ].focusLost( null );
				chooser.panelSuperior.fieldAbsolutePath.getFocusListeners( )[ 0 ].focusLost( null );
				
				if( e.getButton( ) == MouseEvent.BUTTON3 )
				{
					if( ( fc.popup == null ) || !fc.popup.isShowing( ) )
					{
						fc.inicializarPopup( e.getXOnScreen( ), e.getYOnScreen( ), Colores.ROJO, Colores.BLANCO );
						fc.popup.show( );
						chooser.hideBut( fc.instance );
					}
					else
					{
						fc.popup.hide( );
					}
				}
				else
				{
					chooser.hideBut( null );
				}
			}
			
			@Override
			public void mouseReleased( MouseEvent e )
			{
				chooser.panelInferior.fieldSelected.transferFocus( );
			}
		} );
		return fc;
	}
	
	// /**
	// * JFT
	// *
	// * @param args
	// */
	// public static void main( String[ ] args )
	// {
	// FileChooser fileChooser = new FileChooser( "C:\\Users\\Dave\\Documents\\Eclipse\\0 More", DIRECTORIES_ONLY );
	//
	// // fileChooser.setFieldText( "archivo.txt" );
	// fileChooser.setApproveOption( "Guardar" );
	// boolean confirmacion = fileChooser.mostrarDialogo( );
	// if( confirmacion )
	// {
	// File f = fileChooser.getSelectedFile( );
	// System.out.println( "Selected: " + f );
	// // try
	// // {
	// // Desktop.getDesktop( ).open( f );
	// // }
	// // catch( IOException e )
	// // {
	// // e.printStackTrace( );
	// // }
	// }
	// }
	
	/**
	 * Retorna la opcion de eleccion de usuario<br>
	 *
	 * @return {@link #OPCION_ACEPTAR} u {@link #OPCION_CANCELAR}
	 */
	public int getConfirmacion( )
	{
		return confirmacion;
	}
	
	/**
	 * Retorna el archivo seleccionado por el usuario
	 *
	 * @return Archivo seleccionado por el usuario. Null si no ha seleccionado ningun archivo
	 * @see {@link java.io.File}
	 */
	public File getSelectedFile( )
	{
		return selectedFile;
	}
	
	/**
	 * Resalta el FileComponent (Archivo) del panel
	 *
	 * @param name Nombre del archivo a resaltar
	 * @param setField True: El nombre aparece en el Field inferior. False de lo contrario
	 * @see {@link FileComponent}
	 */
	public void highLight( String name, boolean setField )
	{
		boolean primero = false;
		for( FileComponent fileComponent : fileComponents )
		{
			if( !primero && !fileComponent.selected && ( fileComponent.file != null ) && fileComponent.file.getName( ).toUpperCase( ).startsWith( name.toUpperCase( ) ) )
			{
				primero = true;
				fileComponent.setSeleccionado( true, setField );
			}
			else
			{
				fileComponent.setSeleccionado( false, setField );
			}
		}
	}
	
	/**
	 * Muestra el dialogo FileChooser <br>
	 * Retorna la confirmacion del usuario
	 *
	 * @return Confirmación del Usuario. True si acepto o False de lo contrario
	 * @see {@link #FileChooser() } <br>
	 *      {@link #FileChooser(String)}<br>
	 *      {@link FileChooser#getConfirmacion()}<br>
	 *      {@link JDialog#setVisible(boolean)}
	 */
	public boolean mostrarDialogo( )
	{
		setVisible( true );
		
		return confirmacion == OPCION_ACEPTAR;
	}
	
	/**
	 * Selecciona (Resalta) un FileComponent con el File dado por parametro<br>
	 * Ubica el nombre del file en el {@link PanelInferior#fieldSelected} del panelInferior
	 *
	 * @param file File del FileComponent a seleccionar
	 * @see {@link FileComponent}<br>
	 *      {@link #panelInferior}
	 */
	public void seleccionar( File file )
	{
		panelInferior.fieldSelected.setText( file != null ? file.getName( ) : "" );
		
		for( FileComponent fileComponent : fileComponents )
		{
			if( ( fileComponent.file != null ) && fileComponent.file.equals( file ) )
			{
				fileComponent.setBackground( Colores.GRIS );
				fileComponent.setForeground( Colores.BLANCO );
				fileComponent.setBorder( BorderFactory.createLineBorder( Colores.GRIS ) );
			}
			else
			{
				fileComponent.setBackground( null );
				fileComponent.setForeground( Colores.GRIS );
				fileComponent.setBorder( BorderFactory.createLineBorder( Colores.BLANCO ) );
			}
		}
	}
	
	/**
	 * Configura el texto de la opción de aprobación del panel(Botón aceptar)
	 *
	 * @param approveOptionString Texto para la opción de aprobación
	 * @see {@link PanelInferior#fieldSelected}
	 */
	public void setApproveOption( String approveOptionString )
	{
		panelInferior.aceptar.setText( approveOptionString );
	}
	
	/**
	 * Configura el field del panelInferior con el texto dado por parametro
	 *
	 * @param text Texto a colocar en el field del panelInferior
	 * @see {@link #panelInferior}<br>
	 *      {@link PanelInferior#fieldSelected}
	 */
	public void setFieldText( String text )
	{
		panelInferior.fieldSelected.setText( text );
	}
	
	/**
	 * Oculta los Popups de los FileComponents, a excepcion de el que entra por parametro<br>
	 *
	 * @param fileComponent FileComponent del cual <b>NO</b> se ocultara el Popup. Null para ocultarlos todos
	 * @see {@link com.dnarvaez27.componentes.Popup}
	 */
	private void hideBut( FileComponent fileComponent )
	{
		for( FileComponent fc : fileComponents )
		{
			if( !fc.equals( fileComponent ) )
			{
				if( /* ( fc != null ) && */ ( fc.popup != null ) )
				{
					fc.popup.hide( );
				}
			}
		}
	}
	
	/**
	 * Activa el KeyEventDispatcher
	 */
	private void key( )
	{
		KeyboardFocusManager.getCurrentKeyboardFocusManager( ).addKeyEventDispatcher( keyEventDispatcher );
	}
	
	/**
	 * Agrega los FileComponents al Panel<br>
	 * Los archivos provienen del defaultPath
	 *
	 * @see {@link #defaultPath }
	 *      {@link FileComponent}
	 */
	private void poblar( )
	{
		currentDir = new File( defaultPath );
		poblar( currentDir.getAbsolutePath( ) );
	}
	
	/**
	 * Agrega los FileComponents con la ruta del directorio dada por parametro<br>
	 * Ordena los archivos por nombre y categoria (Directorios y Archivos)
	 *
	 * @param path Ruta del directorio con archivos a agregar
	 * @see {@link FileComponent}<br>
	 *      {@link File}
	 */
	private void poblar( String path )
	{
		currentDir = new File( path );
		panelSuperior.fieldAbsolutePath.setText( currentDir.getAbsolutePath( ) );
		
		fileComponents.clear( );
		
		setTitle( currentDir.getName( ) );
		setCursor( Media.WAIT );
		
		archivos.removeAll( );
		
		File f = new File( path );
		// int contador = 0;
		if( f.isDirectory( ) )
		{
			File[ ] files = f.listFiles( );
			ArrayList<File> arch = new ArrayList<>( );
			if( ( files != null ) && ( files.length != 0 ) )
			{
				arch = new ArrayList<>( Arrays.asList( f.listFiles( ) ) );
				archivos.setLayout( new GridLayout( 5, 1 ) );
			}
			else
			{
				archivos.setLayout( new BorderLayout( ) );
				JLabel label = new JLabel( ConstantesComponentes.Strings.VACIA );
				label.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
				label.setHorizontalAlignment( SwingConstants.CENTER );
				archivos.add( label, BorderLayout.NORTH );
			}
			Collections.sort( arch, ( File f1, File f2 ) ->
			{
				if( f1.isDirectory( ) )
				{
					if( f1.isDirectory( ) && f2.isDirectory( ) )
					{
						return f1.compareTo( f2 );
					}
					return -1;
				}
				else if( f2.isDirectory( ) )
				{
					return 1;
				}
				return f1.compareTo( f2 );
			} );
			
			for( File fi : arch )
			{
				FileComponent fileComponent = null;
				if( fi.isDirectory( ) )
				{
					fileComponent = diectoryComponent( this, fi );
				}
				else if( tipoArchivos != DIRECTORIES_ONLY )
				{
					fileComponent = fileComponent( this, fi );
				}
				if( !fi.isHidden( ) && ( fileComponent != null ) )
				{
					fileComponents.add( fileComponent );
					archivos.add( fileComponent );
				}
				// contador++;
				// if( contador == 100 )
				// {
				// break;
				// }
			}
		}
		
		scroll.setViewportView( archivos );
		
		setCursor( Media.DEFAULT );
		revalidate( );
		repaint( );
	}
}