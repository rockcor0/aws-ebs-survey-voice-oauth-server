//package com.segurosbolivar.purecloud.survey.ws.rest.services;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.ext.Provider;
//
//
//import java.io.IOException;
//
//@Provider
//public class CORSFilter implements ContainerResponseFilter {
//
//	@Override
//	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException
//	{
//		response.getHeaders().add("Access-Control-Allow-Origin", "*");
//		response.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
//		response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
//	}
//
//}
