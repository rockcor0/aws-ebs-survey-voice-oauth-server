package com.segurosbolivar.purecloud.survey.model;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import com.segurosbolivar.purecloud.survey.dto.DTOToken;
import com.segurosbolivar.purecloud.survey.resources.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenFactory {

	private static TokenFactory tokenFactory;

	/**
	 * Instancia la clase TokenFactory
	 * @return Retorna el objeto instanciado de la clase
	 */
	public static TokenFactory getInstance() {
		if(tokenFactory == null){
			tokenFactory = new TokenFactory();
		}
		
		return tokenFactory;
	}

	/**
	 * Constructor de la clase
	 */
	private TokenFactory() {

	}
	
	/**
	 * Crea un nuevo token
	 * @return
	 */
	public Token createToken() {
		
		Token token = new Token();
		String tokenId = UUID.randomUUID().toString();

		Date now = new Date();
		token.setCreatedDate(dateSerConvertToLocalDateTimeViaInstant(now).toString());
		token.setUpdatedDate(dateSerConvertToLocalDateTimeViaInstant(now).toString());
		token.setStart_in(dateSerConvertToLocalDateTimeViaInstant(now).toString());
		token.setTokenId(tokenId);
		token.setToken_type("bearer");
//		token.setExpires_in(dtoClient.getAccessTokenValiditySeconds());
		token.setExpires_in(Constant.TOKEN_EXPIRATION);
		
		//ClientId y clientSecret cifrados en MD5
		//String clientSecret = dtoClient.getClientSecret();
		//String clientId = dtoClient.getClientId();
		String name = Base64.getEncoder().encodeToString(("Seguros Bolívar").getBytes());
		String issuer = Base64.getEncoder().encodeToString("Genesys Cloud".getBytes());
		String aud = Base64.getEncoder().encodeToString("Encuesta Bolívar".getBytes());
		//String key = Base64.getEncoder().encodeToString((tokenId+":"+clientId+":"+clientSecret).getBytes());
		//System.out.println(key);
		
		String jwt = createJWT(Base64.getEncoder().encodeToString(tokenId.getBytes()), issuer , name, Constant.TOKEN_EXPIRATION, aud);
		
		token.setAccess_token(jwt);
		
		return token;
	}
	
	/**
	 * Crea un token JWT
	 * @param id Identificador del token
	 * @param issuer Emisor del token
	 * @param subject Nombre del cliente
	 * @param ttlMillis Duración del token
	 * @param aud Cliente del token
	 * @return Cadena JWT
	 */
	public static String createJWT(String id, String issuer, String subject, long ttlMillis, String aud) {
		 
		 //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    System.out.println("Id en generacion id " + id);
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(id+":"+id);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .setAudience(aud)
	            .setHeaderParam("typ","jwt")
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

	/**
	 * Decodifica un token
	 * @param dtoToken Token a decodificar
	 * @return true o false de acuerdo a si la operación fue exitosa o fallida. 
	 * @throws ExpiredJwtException
	 * @throws Exception
	 */
	public boolean decodeToken(DTOToken dtoToken) throws ExpiredJwtException, Exception{
		boolean isValid = false;
		
		System.out.println(dtoToken.getAccess_token() == null);
		
		if(dtoToken != null && dtoToken.getAccess_token() != null) {
			String jwt = dtoToken.getAccess_token();
			String id = Base64.getEncoder().encodeToString((dtoToken.getTokenId()).getBytes());
			
			System.out.println("Id token = " + dtoToken.getTokenId() + DatatypeConverter.parseBase64Binary(id+":"+id));
			Claims claims = Jwts.parser()
		            .setSigningKey(DatatypeConverter.parseBase64Binary(id+":"+id))
		            .parseClaimsJws(jwt).getBody();
			
			System.out.println(claims.getId() + " - " +Base64.getEncoder().encodeToString(dtoToken.getTokenId().getBytes()));

			//Comparar certificados
			if((claims.getId().equals( Base64.getEncoder().encodeToString( dtoToken.getTokenId().getBytes())))) {
				isValid = true;
			}
		}else {
			throw new Exception();
		}
		
		return isValid;
	}
	

	/**
	 * Convertir una fecha de tipo Date a LocalDateTime
	 * @param dateToConvert
	 * @return Fecha de tipo LocalDateTime
	 */
	private LocalDateTime dateSerConvertToLocalDateTimeViaInstant(Date dateToConvert) {
		//System.out.println("Zona horaria " + dateToConvert.toInstant().atZone(ZoneId.systemDefault()).getOffset());
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}
}
