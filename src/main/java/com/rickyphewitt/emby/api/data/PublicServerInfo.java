package com.rickyphewitt.emby.api.data;

public class PublicServerInfo {

	
	/*Example Entity
	 * 
	 * Id: "d6488b4b48d74802af6fe00679903259"
	 * LocalAddress: "http://10.11.12.234:8096"
	 * OperatingSystem: "Linux"
	 * ServerName: "emby"
	 * Version: "3.2.14.0"
	 * WanAddress: "https://rphdev.no-ip.org:8920"
	 *
	 */
	
	private String id;
	private String localAddress;
	private String operatingSystem;
	private String serverName;
	private String version;
	private String wanAddress;
	
	// Getters/Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getWanAddress() {
		return wanAddress;
	}
	public void setWanAddress(String wanAddress) {
		this.wanAddress = wanAddress;
	}
	
}
