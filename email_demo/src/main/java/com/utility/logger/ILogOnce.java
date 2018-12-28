package com.utility.logger;

/**
 * @author Aniket Bharsakale
 * Interface exposes methods indicating if object has already been logged
 */
public interface ILogOnce {

	/**
	 * Method indicates if the object was logged
	 * @return boolean value
	 */
	public boolean isLogged();

	/**
	 * Method is used to set the logged state
	 * @param logged
	 */
	public void setLogged(boolean logged);
}
