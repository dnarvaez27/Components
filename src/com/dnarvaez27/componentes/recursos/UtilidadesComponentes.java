package com.dnarvaez27.componentes.recursos;

import com.dnarvaez27.resources.Resources;

public class UtilidadesComponentes
{
	/**
	 * Atributo de resource
	 */
	private static Resources resource;

	/**
	 * Devuelve el manejador de recursos del archivo.<br>
	 * Datos alojados en la carpeta Imagenes
	 * 
	 * @return Manejador de recursos del archivo
	 */
	public static Resources getResourcesInstance( )
	{
		if( resource == null )
		{
			resource = new Resources( UtilidadesComponentes.class, "Imagenes" );
		}
		return resource;
	}
}