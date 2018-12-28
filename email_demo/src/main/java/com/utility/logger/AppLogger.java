package com.utility.logger;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;

import com.utility.exception.AppException;

/**
 * The Logger Instance to use in the projects
 *
 */

public class AppLogger implements Logger{

	private static final String EMPTY_STRING = "";
	private static final String EQUALS = "= ";
	private static final String MDC_KEY = "MDC";
	private static final String SEMICOLON = "; ";

	private final Logger logger;
	private final ThreadLocal<TreeMap<String, String>> mdcMap = new ThreadLocal<>();

	public AppLogger(@SuppressWarnings("rawtypes") final Class clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public String getName() {

		return this.logger.getName();
	}

	public boolean isTraceEnabled() {

		return this.logger.isTraceEnabled();
	}

	public void trace(final String msg) {

		this.logger.trace(msg);
	}

	public void trace(final String format, final Object arg) {

		this.logger.trace(format, arg);
	}

	public void trace(final String format, final Object arg1, final Object arg2) {

		this.logger.trace(format, arg1, arg2);
	}

	public void trace(final String format, final Object[] argArray) {

		this.logger.trace(format, argArray);

	}

	public void trace(final String msg, final Throwable t) {
		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.trace(msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.trace(msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.trace(msg, t);
		}

	}

	public boolean isTraceEnabled(final Marker marker) {

		return this.logger.isTraceEnabled(marker);
	}

	public void trace(final Marker marker, final String msg) {

		this.logger.trace(marker, msg);
	}

	public void trace(final Marker marker, final String format, final Object arg) {

		this.logger.trace(marker, format, arg);
	}

	public void trace(final Marker marker, final String format, final Object arg1, final Object arg2) {

		this.logger.trace(marker, format, arg1, arg2);
	}

	public void trace(final Marker marker, final String format, final Object[] argArray) {

		this.logger.trace(marker, format, argArray);
	}

	public void trace(final Marker marker, final String msg, final Throwable t) {
		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.trace(marker, msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.trace(marker, msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.trace(marker, msg, t);
		}

	}

	public boolean isDebugEnabled() {

		return this.logger.isDebugEnabled();
	}

	public void debug(final String msg) {

		this.logger.debug(msg);
	}

	public void debug(final String format, final Object arg) {

		this.logger.debug(format, arg);
	}

	
	public void debug(final String format, final Object arg1, final Object arg2) {

		this.logger.debug(format, arg1, arg2);
	}

	
	public void debug(final String format, final Object[] argArray) {

		this.logger.debug(format, argArray);
	}

	
	public void debug(final String msg, final Throwable t) {
		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.debug(msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.debug(msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.debug(msg, t);
		}
	}

	public boolean isDebugEnabled(final Marker marker) {

		return this.logger.isDebugEnabled(marker);
	}

	public void debug(final Marker marker, final String msg) {

		this.logger.debug(marker, msg);
	}

	public void debug(final Marker marker, final String format, final Object arg) {

		this.logger.debug(marker, format, arg);
	}

	public void debug(final Marker marker, final String format, final Object arg1, final Object arg2) {

		this.logger.debug(marker, format, arg1, arg2);
	}

	public void debug(final Marker marker, final String format, final Object[] argArray) {

		this.logger.debug(marker, format, argArray);
	}

	
	public void debug(final Marker marker, final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.debug(marker, msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.debug(marker, msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.debug(marker, msg, t);
		}
	}

	
	public boolean isInfoEnabled() {

		return this.logger.isInfoEnabled();
	}

	
	public void info(final String msg) {

		this.logger.info(msg);
	}

	
	public void info(final String format, final Object arg) {

		this.logger.info(format, arg);
	}

	
	public void info(final String format, final Object arg1, final Object arg2) {

		this.logger.info(format, arg1, arg2);
	}

	
	public void info(final String format, final Object[] argArray) {

		this.logger.info(format, argArray);
	}

	
	public void info(final String msg, final Throwable t) {
		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.info(msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.info(msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.info(msg, t);
		}
	}

	
	public boolean isInfoEnabled(final Marker marker) {

		return this.logger.isInfoEnabled();
	}

	
	public void info(final Marker marker, final String msg) {

		this.logger.info(marker, msg);
	}

	
	public void info(final Marker marker, final String format, final Object arg) {

		this.logger.info(marker, format, arg);
	}

	
	public void info(final Marker marker, final String format, final Object arg1, final Object arg2) {

		this.logger.info(marker, format, arg1, arg2);
	}

	
	public void info(final Marker marker, final String format, final Object[] argArray) {

		this.logger.info(marker, format, argArray);
	}

	
	public void info(final Marker marker, final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.info(marker, msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.info(marker, msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.info(marker, msg, t);
		}
	}

	
	public boolean isWarnEnabled() {

		return this.logger.isWarnEnabled();
	}

	
	public void warn(final String msg) {

		this.logger.warn(msg);
	}

	
	public void warn(final String format, final Object arg) {

		this.logger.warn(format, arg);
	}

	
	public void warn(final String format, final Object[] argArray) {

		this.logger.warn(format, argArray);
	}

	
	public void warn(final String format, final Object arg1, final Object arg2) {

		this.logger.warn(format, arg1, arg2);
	}

	
	public void warn(final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.warn(msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.warn(msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.warn(msg, t);
		}
	}

	
	public boolean isWarnEnabled(final Marker marker) {

		return this.logger.isWarnEnabled();
	}

	
	public void warn(final Marker marker, final String msg) {

		this.logger.warn(marker, msg);
	}

	
	public void warn(final Marker marker, final String format, final Object arg) {

		this.logger.warn(marker, format, arg);
	}

	
	public void warn(final Marker marker, final String format, final Object arg1, final Object arg2) {

		this.logger.warn(marker, format, arg1, arg2);
	}

	
	public void warn(final Marker marker, final String format, final Object[] argArray) {

		this.logger.warn(marker, format, argArray);
	}

	
	public void warn(final Marker marker, final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.warn(marker, msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.warn(marker, msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.warn(marker, msg, t);
		}
	}

	
	public boolean isErrorEnabled() {

		return this.logger.isErrorEnabled();
	}

	
	public void error(final String msg) {

		this.logger.error(msg);
	}

	
	public void error(final String format, final Object arg) {

		this.logger.error(format, arg);
	}

	
	public void error(final String format, final Object arg1, final Object arg2) {

		this.logger.error(format, arg1, arg2);
	}

	
	public void error(final String format, final Object[] argArray) {

		this.logger.error(format, argArray);
	}

	
	public void error(final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.error(msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.error(msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.error(msg, t);
		}
	}

	
	public boolean isErrorEnabled(final Marker marker) {

		return this.logger.isErrorEnabled(marker);
	}

	
	public void error(final Marker marker, final String msg) {

		this.logger.error(marker, msg);
	}

	
	public void error(final Marker marker, final String format, final Object arg) {

		this.logger.error(marker, format, arg);
	}

	
	public void error(final Marker marker, final String format, final Object arg1, final Object arg2) {

		this.logger.error(marker, format, arg1, arg2);
	}

	
	public void error(final Marker marker, final String format, final Object[] argArray) {

		this.logger.error(marker, format, argArray);
	}

	
	public void error(final Marker marker, final String msg, final Throwable t) {

		if (t instanceof AppException) {
			final AppException lqacException = (AppException) t;
			if (lqacException.isLogged()) {
				this.logger.error(marker, msg + " - Exception logged earlier - " + lqacException.getMessage());
			} else {
				this.logger.error(marker, msg, lqacException);
				lqacException.setLogged(true);
			}
		} else {
			this.logger.error(marker, msg, t);
		}
	}

	/**
	 * Returns the internal MDC TreeMap for this thread. We are using a TreeMap instead of just using the
	 * MDC directly because there is no guarantee that the key value pairs contained in the MDC will be
	 * output in a predictable order.
	 *
	 * @return
	 */
	private TreeMap<String, String> getMdcMap() {
		TreeMap<String, String> mdcMap = this.mdcMap.get();

		if (mdcMap == null) {
			mdcMap = new TreeMap<>();
			this.mdcMap.set(mdcMap);
		}

		return mdcMap;
	}

	/**
	 * Clears the MDC for this thread
	 */
	public void clearMdc() {
		getMdcMap().clear();
		MDC.remove(MDC_KEY);
	}

	/**
	 * Adds/Removes MDC key value pairs in the current thread. If value is set to null the key is removed from the MDC
	 * @param key
	 * @param value
	 */
	public void putMdc(String key, String value) {

		if ((key != null) && !key.trim().isEmpty()) {
			if (value != null) {
				getMdcMap().put(key.trim(), value);
			}
			else {
				getMdcMap().remove(key);
			}

			StringBuilder sb = new StringBuilder();

			for (Entry<String, String> mdcMapEntry : getMdcMap().entrySet()) {
				sb.append(SEMICOLON).append(mdcMapEntry.getKey()).append(EQUALS).append(mdcMapEntry.getValue());
			}

			MDC.put(MDC_KEY, sb.length() > 0 ? sb.substring(2) : EMPTY_STRING);
		}
	}

	/**
	 * Adds/Removes MDC key value pairs in the current thread. The return value of value.toString() will be used as the value stored in the MDC.
	 * If value is set to null the key is removed from the MDC
	 * @param key
	 * @param value
	 */
	public void putMdc(String key, Object value) {

		if (value != null) {
			putMdc(key, value.toString());
		}
		else {
			putMdc(key, null);
		}
	}

	/**
	 * Retrieves the value of the given key from the MDC for this thread.
	 * @param key
	 * @return
	 */
	public String getMdc(String key) {
		String value = null;

		if (key != null) {
			value = getMdcMap().get(key.trim());
		}

		return value;
	}
}
