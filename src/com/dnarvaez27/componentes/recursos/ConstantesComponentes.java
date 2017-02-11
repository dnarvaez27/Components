package com.dnarvaez27.componentes.recursos;

import java.net.URL;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

/**
 * Constantes para la clase Componentes
 * 
 * @author d.narvaez11
 */
public class ConstantesComponentes
{
	/**
	 * Clase que contiene los colores utilizados en el paquete componentes
	 * 
	 * @author d.narvaez11
	 */
	public static class Colores
	{
		/**
		 * <b>R:</b> 255<br>
		 * <b>G:</b> 214<br>
		 * <b>B:</b> 47<br>
		 */
		public static final Color AMARILLO = new Color( 255, 214, 47 );
		
		/**
		 * <b>R:</b> 255<br>
		 * <b>G:</b> 255<br>
		 * <b>B:</b> 255<br>
		 */
		public static final Color BLANCO = new Color( 255, 255, 255 );
		
		/**
		 * <b>R:</b> 33<br>
		 * <b>G:</b> 33<br>
		 * <b>B:</b> 33<br>
		 */
		public static final Color GRIS = new Color( 33, 33, 33 );
		
		/**
		 * <b>R:</b> 228<br>
		 * <b>G:</b> 75<br>
		 * <b>B:</b> 77<br>
		 */
		public static final Color ROJO = new Color( 228, 75, 77 );
	}
	
	/**
	 * Clase que contiene las imagenes utilizadas en el paquete componentes
	 * 
	 * @author d.narvaez11
	 */
	public static class Imagenes
	{
		/**
		 * Imagen de flecha de retorno
		 */
		public static final ImageIcon BACK = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "Back.png" ) );
		
		/**
		 * Imagen de cierre
		 */
		public static final ImageIcon CLOSE = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "Close.png" ) );
		
		/**
		 * Imagen de un archivo
		 */
		public static final ImageIcon FILE = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "File.png" ) );
		
		/**
		 * Imagen de un folder
		 */
		public static final ImageIcon FOLDER = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "Folder.png" ) );
		
		/**
		 * Imagen de Home
		 */
		public static final ImageIcon HOME = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "Home.png" ) );
		
		/**
		 * Imagen de un folder nuevo
		 */
		public static final ImageIcon NEW_FOLDER = new ImageIcon( UtilidadesComponentes.getResourcesInstance( ).getPathFile( "NewFolder.png" ) );
		
		public static final URL GIF_URL = UtilidadesComponentes.getResourcesInstance( ).getPathFile( "GifInicioSesion.gif" );;
	}
	
	/**
	 * Clase que contiene los cursores utilizados en el paquete componentes
	 * 
	 * @author d.narvaez11
	 */
	public static class Media
	{
		public static final Cursor DEFAULT = new Cursor( Cursor.DEFAULT_CURSOR );
		
		public static final Cursor HAND = new Cursor( Cursor.HAND_CURSOR );
		
		public static final Cursor WAIT = new Cursor( Cursor.WAIT_CURSOR );
	}
	
	public static class Strings
	{
		public static final String PICK_DATE = "Seleccione una fecha";
		
		public static final String ACEPTAR = "Aceptar";
		
		public static final String ATRAS = "Atrás";
		
		public static final String CANCELAR = "Cancelar";
		
		public static final String ELIMINAR = "Eliminar";
		
		public static final String ELIMINAR_ARCHIVO = "¿Desea eliminar el archivo";
		
		public static final String ELIMINAR_FOLDER = "¿Desea eliminar la carpeta";
		
		public static final String ERROR = "Error";
		
		public static final String HOME = "Home";
		
		public static final String INGRESE_NUEVO_NOMBRE = "Ingrese el nuevo nombre";
		
		public static final String NO_EXISTE_DIR = "No existe el directorio especificado";
		
		public static final String NO_SE_RENAME = "No se pudo cambiar el nombre";
		
		public static final String NUEVA_CARPETA = "Nueva Carpeta";
		
		public static final String RENAME = "Renombrar";
		
		public static final String VACIA = "La carpeta esta vacía";
		
		public static final String Y_ELIMINAR_ARCHIVOS = "y todos sus archivos?";
		
	}
}