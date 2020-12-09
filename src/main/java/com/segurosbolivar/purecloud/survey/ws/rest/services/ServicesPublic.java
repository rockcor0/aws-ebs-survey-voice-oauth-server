/**
 * Paquete de servicios Rest.
 */
package com.segurosbolivar.purecloud.survey.ws.rest.services;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.segurosbolivar.purecloud.survey.resources.Constant;
import com.segurosbolivar.purecloud.survey.threads.CrudAuth;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserResponse;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetTokenRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetTokenResponse;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenResponse;

/**
 * API Pública para el consumo desde aplicaciones externas como PureCloud.
 * @author Ricardo Delgado
 */
@Path("/public")
public class ServicesPublic {

	
	@GET
	@Path("/status")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String getStatus() {
		return "Ok";
	}
	
	/**
	 * Expone un servicio web para iniciar sesión en la encuesta.
	 * @return
	 */
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOGetTokenResponse createToken(VOGetTokenRequest voGetTokenRequest) {
		//System.out.println(" Client ID from " + voGetTokenRequest.getClientId());

		//Eliminar tokens de forma aleatoria
		int aleatorio = (int) (Math.random()*100+1);

		if(aleatorio == 0) {
			CrudAuth crudAuth2 = new CrudAuth();
			crudAuth2.start();
			crudAuth2.deleteOldTokens();	
		}

		// Generar nuevo token
		CrudAuth crudAuth = new CrudAuth();
		crudAuth.start();
		return crudAuth.getToken(voGetTokenRequest);
		//		return null;
	}

	/**
	 * Expone un servicio web para validar un token.
	 * @return
	 */
	@POST
	@Path("/validateToken")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOGetValidateTokenResponse createToken(VOGetValidateTokenRequest voGetValidateTokenRequest) {
		CrudAuth crudAuth = new CrudAuth();
		crudAuth.start();
		return crudAuth.validateToken(voGetValidateTokenRequest);
	}
	
	/**
	 * Expone un servicio web para crear un cliente de autorización
	 * @return
	 */
	@RolesAllowed(Constant.ROLE_GENESYS)
	@POST
	@Path("/createOAuthClient")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOCreateUserResponse createOAuthClient(VOCreateUserRequest voCreateUserRequest) {

		CrudAuth crudAuth = new CrudAuth();
		crudAuth.start();
		return crudAuth.createOAuthClient(voCreateUserRequest);
	}
	
}
