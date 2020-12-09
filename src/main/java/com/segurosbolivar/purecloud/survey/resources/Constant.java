/**
 * 
 */
package com.segurosbolivar.purecloud.survey.resources;

public class Constant {
	
	
	public static final String RESPONSE_MESSAGE_VALID_200 = "OK";
	public static final int RESPONSE_CODE_VALID_200 = 200;
	public static final int ERROR_CODE_GENERIC_EXCEPTION = 400;
	public static final String ERROR_MESSAGE_BAD_REQUEST = "Bad request";
	public static final String RESPONSE_MESSAGE_INVALID_401 = "Credenciales no válidas o faltantes";
	public static final int RESPONSE_CODE_INVALID_401 = 401;
	public static final String ERROR_MESSAGE_INVALID_LOGIN_CREDENTIALS = "El token ha expirado.";
	public static final int RESPONSE_CODE_NOT_FOUND_404 = 404;
	public static final String ERROR_MESSAGE_NOT_FOUND_TOKEN = "El token no ha sido encontrado.";
	

	public static final long TOKEN_EXPIRATION = 3600000;

	public static final String ROLE_GENESYS = "GENESYS";
	
	public static final String PROC_DELETE_TOKEN = "CALL PRC_ENC_DEL_TOKENS(?)";
	
	public static final String ERROR_MESSAGE_INVALID_QUEUE = "La cola no existe";
	public static final int ERROR_CODE_INVALID_QUEUE = 404;
	public static final String ERROR_MESSAGE_EXIST_QUEUE = "El queueId es incorrecto o ya existe una cola con el queueId enviado";
	public static final int ERROR_CODE_EXIST_QUEUE = 404;
	public static final String ERROR_MESSAGE_DOESNT_EXIST_QUEUE = "El id de la cola es incorrecto o no existe una cola con dicho id";
	public static final int ERROR_CODE_DOESNT_EXIST_QUEUE = 404;
	public static final String ERROR_MESSAGE_INVALID_ROL = "El rol no existe";
	public static final String ERROR_MESSAGE_EMPTY_ROL_OR_PERSON = "Falta al menos un rol o falta el identificador de la persona";
	public static final int ERROR_CODE_INVALID_ROL = 404;
	public static final String ERROR_MESSAGE_EXIST_ROL = "El identificaro del rol es incorrecto o ya existe el rol";
	public static final int ERROR_CODE_EXIST_ROL = 404;
	public static final String ERROR_MESSAGE_DOESNT_EXIST_ROL = "El identificador del rol es incorrecto o no existe el rol.";
	public static final int ERROR_CODE_DOESNT_EXIST_ROL = 404;
	public static final String ERROR_MESSAGE_EXIST_SUPERVISOR = "El id del supervisor es incorrecto o ya existe un supervisor con dicho id";
	public static final int ERROR_CODE_EXIST_SUPERVISOR = 404;
	public static final int ERROR_CODE_INVALID_PASSWORD_USER = 401;
	public static String ERROR_MESSAGE_INVALID_PASSWORD_USER = "Credenciales no válidas o faltantes";
	public static final String ERROR_MESSAGE_DOESNT_EXIST_PERSON = "El identificador de la persona es inválido o no existe.";
	public static final int ERROR_CODE_DOESNT_EXIST_PERSON = 404;
	
	
	public static final int VAL_QUEUEID_CHAR_LENGTH = 36;
	public static final String ERROR_MESSAGE_INVALID_SUPERVISOR = "El supervisor no existe";
	public static final int ERROR_CODE_INVALID_SUPERVISOR = 404;
	public static final int ERROR_CODE_ROLLBACK_EXCEPTION = 404;
	public static final int ERROR_CODE_CONSTRAINT_VIOLATION_EXCEPTION = 404;
	public static final int ERROR_CODE_ILLEGAL_STATE_EXCEPTION = 404;
	public static final int ERROR_CODE_ILLEGAL_ARGUMENT_EXCEPTION = 404;
	public static final int ERROR_CODE_PERSISTENCE_EXCEPTION = 404;

	public static final int ERROR_CODE_INVALID_AGENT = 404;
	public static final String ERROR_MESSAGE_INVALID_PERSON = "El identificador de la persona es incorrecto o no existe.";
	public static final String ERROR_MESSAGE_EXIST_PERSON = "El identificador de la persona es incorrecto o ya existe";
	public static final int ERROR_CODE_EXIST_PERSON = 404;
	public static final String ERROR_MESSAGE_INVALID_PERSON_QUEUE = "El identificador de la persona o de la cola son incorrectos o no existe alguno de ellos en la base de datos";
	public static final int ERROR_CODE_INVALID_PERSON_QUEUE = 404;
	public static final String ERROR_MESSAGE_INVALID_QUEUE_AGENT = "El identificador del agente o de la cola son incorrectos o no existe alguno de ellos en la base de datos";
	public static final int ERROR_CODE_INVALID_QUEUE_AGENT = 404;
	public static final int ERROR_CODE_INVALID_PERSON = 404;
	public static final int ERROR_CODE_INVALID_SURVEY = 404;
	public static final int VAL_SUPERVISOR_ID_CHAR_LENGTH = 36;
	public static final int VAL_AGENT_ID_CHAR_LENGTH = 36;
	public static final int VAL_PERSON_ID_CHAR_LENGTH = 36;
	public static final int VAL_QUEUE_ID_CHAR_LENGTH = 36;
	public static final int VAL_ROL_ID_CHAR_LENGTH = 36;
	public static final String ERROR_MESSAGE_INVALID_SURVEY = "El identificador de la encuesta es incorrecto o no existe.";
	public static final String ERROR_MESSAGE_EXIST_SURVEY = "El identificador de la encuesta es incorrecto o ya existe";
	public static final int ERROR_CODE_EXIST_SURVEY = 404;
	public static final String ERROR_MESSAGE_INVALID_QUESTION = "El identificador de la pregunta es incorrecto o no existe";
	public static final int ERROR_CODE_INVALID_QUESTION = 404;
	public static final int VAL_QUESTION_ID_CHAR_LENGTH = 36;
	public static final String ERROR_MESSAGE_EXIST_QUESTION = "El identificador de la pregunta es incorrecto o ya existe";
	public static final int ERROR_CODE_EXIST_QUESTION = 404;
	public static final String ERROR_MESSAGE_INVALID_INTERACTION = "El identificador de la interacción es incorrecto o no existe.";
	public static final int ERROR_CODE_INVALID_INTERACTION = 404;
	public static final int VAL_INTERACTIONID_CHAR_LENGTH = 36;
	public static final String ERROR_MESSAGE_INVALID_ANSWER = "El identificador de la respuesta es incorrecto o no existe";
	public static final int ERROR_CODE_INVALID_ANSWER = 404;
	public static final int VAL_ANSWER_ID_CHAR_LENGTH = 36;
	public static final String ERROR_MESSAGE_EXIST_ANSWER = "El identificador de la respuesta es incorrecto o ya existe";
	public static final int ERROR_CODE_EXIST_ANSWER = 404;
	public static final String ERROR_MESSAGE_DOESNT_EXIST_QUESTION = "El identificador de la pregunta es incorrecto o ya existe";
	public static final int ERROR_CODE_DOESNT_EXIST_QUESTION = 404;
	public static final int VAL_AUTH_CODE_CHAR_LENGTH = 36;
	
	
	//Tablas de la base de datos
	public static final String DB_TABLE_NAME_INTERACTION = "PURE_ENC_INTERACTION";
	public static final String DB_TABLE_NAME_QUEUE = "PURE_ENC_QUEUE";
	public static final String DB_TABLE_NAME_PERSON = "PURE_ENC_PERSON";
	public static final String DB_TABLE_NAME_PERSON_HAS_QUEUE = "PURE_ENC_PERSON_HAS_QUEUE";
	public static final String DB_TABLE_NAME_PERSON_HAS_ROL = "PURE_ENC_PERSON_HAS_ROL";
	public static final String DB_TABLE_NAME_ROL = "PURE_ENC_ROL";
	public static final String DB_TABLE_NAME_ANSWER = "PURE_ENC_ANSWER";
	public static final String DB_TABLE_NAME_QUESTION = "PURE_ENC_QUESTION";
	public static final String DB_TABLE_NAME_SURVEY = "PURE_ENC_SURVEY";
	public static final String DB_TABLE_NAME_TOKEN = "PURE_ENC_TOKEN";
	public static final String DB_TABLE_NAME_LOG = "PURE_ENC_LOG";
	public static final String ERROR_MESSAGE_EMPTY_QUEUE_AGENT = "No ha enviado ninguna cola o agente para realizar una consulta.";
	public static final String ERROR_MESSAGE_INVALID_DATE = "La fecha de inicio o fin es incorrecta";
	public static final int ERROR_CODE_INVALID_DATE = 404;
	public static final String ERROR_MESSAGE_IS_NOT_SUPERVISOR = "El usuario no tiene permisos de supervisor";
	
	public static final String VAL_SUPERVISOR_NAME = "Supervisor";
	public static final String VAL_AGENT_NAME = "Agent";
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	public static final String PROC_NEW_TOKEN = "CALL PRC_ENC_INSERTAR_TOKEN_V2(?,?,?,?,?,?,?)";
	public static final String PROC_NEW_OAUTH_CLIENT = "CALL PRC_ENC_INSERTAR_AUTH_CLIENT(?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String ERROR_MESSAGE_INVALID_AUTH_CODE = "El código de autorización no es válido";
	
	//public static final String PROC_GET_INTERACTIONS = "CALL PURE_ENC_SELECCIONAR_INTERACCIONES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//public static final String PROC_GET_A_QUEUE = "CALL PURE_ENC_SELECCIONAR_UNA_COLA(?)";
	
	public static final String PROC_UPDATE_QUEUE = "CALL PRC_ENC_SELECCIONAR_UNA_COLA(?.?)";
	public static final String PROC_NEW_QUEUE = "CALL PRC_ENC_INSERT_COLA(?,?)";

	public static final String PROC_NEW_ROL = "CALL PRC_ENC_INSERT_ROL(?,?)";
	public static final String PROC_NEW_PERSON = "CALL PRC_ENC_INSERT_PERSONA(?,?)";
	public static final String PROC_NEW_SURVEY = "CALL PRC_ENC_INSERT_ENCUESTA(?,?)";
	public static final String PROC_NEW_QUESTION = "CALL PRC_ENC_INSERT_PREGUNTA(?,?,?,?)";
	public static final String PROC_NEW_ANSWER = "CALL PRC_ENC_INSERT_RESPUESTA(?,?,?,?,?)";
	public static final String PROC_NEW_INTERACTION = "CALL PRC_ENC_INSERT_INTERACTION(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String PROC_NEW_LOG = "CALL PRC_ENC_INSERT_LOG(?,?,?,?)";
	
	public static final String PROC_LINK_PERSON_ROL = "CALL PRC_ENC_ENLAZAR_ROL_PERSONA(?,?)";
	public static final String PROC_LINK_PERSON_QUEUE = "CALL PRC_ENC_ENLAZAR_QUEUE_PERSONA(?,?)";
	
	public static final String PROC_UNLINK_PERSON_QUEUE = "CALL PRC_ENC_DEL_PERSON_QUEUE(?,?)";
	public static final String PROC_UNLINK_PERSON_ROL = "CALL PRC_ENC_DEL_PERSON_ROL(?,?)";
	
	public static final String PROC_DELETE_LOG = "CALL PRC_ENC_DEL_LOG(?,?)";
	
	public static final String PROC_DELETE_PERSON = "CALL PRC_ENC_DEL_PERSON(?)";
	
	public static final Object PURECLOUD_ORIGIN = "PureCloud Contact Center";

	
	
}
