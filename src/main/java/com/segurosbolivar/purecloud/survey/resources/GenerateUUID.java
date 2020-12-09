package com.segurosbolivar.purecloud.survey.resources;

import java.util.UUID;
public class GenerateUUID {

	private UUID uuid;

	public GenerateUUID() {
		uuid = UUID.randomUUID();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "GenerateUUID [uuid=" + uuid + "]";
	}

}
