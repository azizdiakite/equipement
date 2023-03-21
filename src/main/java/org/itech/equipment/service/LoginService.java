package org.itech.equipment.service;

/**
 * 
 * @author Pascal
 *
 */
public interface LoginService {
	boolean isAuthenticated();

	void login(String username, String password);
}
