package com.rickyphewitt.emby.api.data;

/**
 * Holds details required to connect to the Emby Server
 * <p>
 *
 * @param  url  an absolute URL of the emby server, include port
 * @param  version version of the emby server app E.g. 3.2.5.0
 * @return      EmbyServerDetails Object
 * @see         ApiV1Client
 */
public class EmbyServerDetails {

	private String url;
	private String version;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
