package com.insags.poc.filenet.dao.impl;

import org.springframework.stereotype.Repository;

import com.filenet.api.constants.ConfigurationParameter;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.UpdatingBatch;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.util.UserContext;
import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.filenet.dao.FilenetDao;

/**
 * Dao para filenet.
 * @author INSA
 */
@Repository("FilenetDaoImpl")
public class FilenetDaoImpl implements FilenetDao {

	/**
	 * Conexión
	 */
	private Connection connection;
	
	/**
	 * Dominio.
	 */
	private Domain dominio;

	/* (non-Javadoc)
	 * @see com.insags.poc.filenet.dao.FilenetDao#conectar()
	 */
	@Override
	public Connection conectar() throws Exception {
		
		String uri = "http://insa.filenet.es";
        connection = Factory.Connection.getConnection(uri);
        if (null != connection) {
            connection.setParameter(ConfigurationParameter.CONNECTION_PARTICIPATES_IN_TRANSACTION, Boolean.TRUE);

        // Obtenemos el dominio (fetch para que informe si no hay conexion)
           String domainName = "FilenetDomain";
           PropertyFilter pf = new PropertyFilter();
           dominio = Factory.Domain.fetchInstance(connection, domainName, pf);
        }
        return connection;
	}

	/* (non-Javadoc)
	 * @see com.insags.poc.filenet.dao.FilenetDao#desconectar()
	 */
	@Override
	public void desconectar() throws Exception {
		UserContext uc = UserContext.get();
        if (uc != null) {
            uc.popSubject();
        }
	}

	/* (non-Javadoc)
	 * @see com.insags.poc.filenet.dao.FilenetDao#crearCarpetaUsuario(com.insags.poc.comun.dto.UsuarioDto)
	 */
	@Override
	public Folder crearCarpetaUsuario(UsuarioDto dto) {
		UpdatingBatch ub = UpdatingBatch.createUpdatingBatchInstance(this.dominio, RefreshMode.NO_REFRESH);
    	// Se crea la carpeta para la convocatoria
		Folder usuarioFolder = 
				Factory.Folder.createInstance(Factory.ObjectStore.getInstance(this.dominio, "objectStoreName"),
				"CLASE_DOCUMENTAL_USUARIO_FOLDER");
		
		Folder carpetaPadre = Factory.Folder.getInstance(
				Factory.ObjectStore.getInstance(this.dominio, "objectStoreName"), 
				null, 
				"RUTA_USUARIO_FOLDER");

		usuarioFolder.set_Parent(carpetaPadre);
		
		ub.add(usuarioFolder, null);
		
		if (ub.hasPendingExecute()) {
			ub.updateBatch();
		}
    	        
    	return usuarioFolder;
	}

}
