package mtgchronik.webfrontend;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import mtgchronik.services.UserService;

public abstract class AbstractView implements Serializable{

	@EJB
	UserService userService;
	/** Logger used for derived classes */
	private Logger logger;

	/**
	 * * Returns a logger instance to be used by this controller. The logger
	 * gets * initialized lazily so the first call of this method may be a
	 * little+ * slower. * @return A logger instance to be used by this
	 * controller.
	 */
	final Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(getClass());
		return logger;
	}

	/**
	 * * Returns the current faces context. * @return The current faces context.
	 * * @see FacesContext#getCurrentInstance()
	 */
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * * Returns the current external context. * @return The current external
	 * context. *
	 */
	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	/**
	 * * Sends the status code {@code statusCode} to the client. * @param
	 * statusCode The status code to be sent. * @throws IOException If sending
	 * the error code fails.
	 */
	protected void responseSendError(final int statusCode) throws IOException {
		responseSendError(statusCode, "");
	}

	/**
	 * * Sends the status code {@code statusCode} together with the message *
	 * {@code message} to the client. * @param statusCode The status code to be
	 * sent. * @param message The textual message to be sent. * @throws
	 * IOException If sending the error code fails.
	 */
	protected void responseSendError(final int statusCode, final String message) throws IOException {
		getExternalContext().responseSendError(statusCode, message);
	}

	/**
	 * * Redirects the request to the page specified by the parameter
	 * {@code url} with due regard to * the current context path by prepending
	 * it to the given URL. * @param url The URL the request should be
	 * redirected to. * @throws IOException If an I/O error occurrs during the
	 * redirect.
	 */
	protected void redirect(final String url) throws IOException {
		final String fullUrl = getExternalContext().getApplicationContextPath().concat(url);
		getExternalContext().redirect(fullUrl);
	}

	/**
	 * * Creates a map of properties for the cookie API. * @param maxAgeInDays
	 * The number of days the cookie should be valid. Giving a value of 0 will *
	 * delete the cookie, -1 causes the cookie to be invalidated with the
	 * browsers session. * @return A map of properties for the cookie API.
	 * * @see LoginController#setCookie(String, Object, int)
	 */
	private final Map<String, Object> doCookieProperties(final int maxAgeInDays) {
		final Map<String, Object> properties = new HashMap<>();
		properties.put("secure", true);
		final int maxAgeInSeconds = maxAgeInDays == -1 ? -1 : maxAgeInDays * 24 * 60 * 60;
		properties.put("maxAge", Integer.valueOf(maxAgeInSeconds));
		final String contextPath = getExternalContext().getRequestContextPath();
		properties.put("path", contextPath.isEmpty() ? "/" : contextPath);
		return properties;
	}

	/**
	 * * Adds a cookie with the name {@code name} and the value {@code value} to
	 * * the response expiring after {@code expiryDays} days. * @param name The
	 * cookie's name. * @param value The cookie's value. * @param expiryDays The
	 * cookies expiry in days.
	 */
	protected final void setCookie(final String name, final Object value, final int expiryDays) {
		getExternalContext().addResponseCookie(name, String.valueOf(value), doCookieProperties(expiryDays));
	}

	/**
	 * * Returns the value of the cookie with the name {@code name} or
	 * {@code 	 * null} in case no such cookie is available. * @param name The
	 * name of the cookie requested. * @return The value of the cookie with the
	 * given name.
	 */
	protected final String getCookie(final String name) {
		Map<String, Object> cookies = getExternalContext().getRequestCookieMap();
		final Cookie cookie = (Cookie) cookies.get(name);
		return cookie != null ? cookie.getValue() : null;
	}

	/** * Returns the servlet request. * @return The servlet request. */
	protected final HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * * Adds a the given faces message to the faces context with the given
	 * client id. * @param clientId The client identifier with which this
	 * message is associated. * @param message The message to be appended.
	 */
	protected void addFacesMessage(final String clientId, final FacesMessage facesMessage) {
		getFacesContext().addMessage(null, facesMessage);
	}

	/**
	 * * Adds a the given faces message to the faces context with the given
	 * client id. * @param message The message to be appended.
	 */
	protected void addFacesMessage(final FacesMessage facesMessage) {
		addFacesMessage(null, facesMessage);
	}

}
