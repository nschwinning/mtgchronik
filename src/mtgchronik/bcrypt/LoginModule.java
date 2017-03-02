package mtgchronik.bcrypt;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

import org.jboss.security.auth.spi.DatabaseServerLoginModule;

/**
 * A login module using a database server and the bcrypt encryption algorithm
 * for authentication.
 * @author Björn Zurmaar
 */
public final class LoginModule
	extends DatabaseServerLoginModule
{
	/** An array of options this login module supports. */
	private static final String[] VALID_OPTIONS = {};

	/**
	 * Initializes this login module.
	 * @see DatabaseServerLoginModule#initialize(Subject,CallbackHandler,Map,Map)
	 */
	@Override
	public void initialize(final Subject subject,
		final CallbackHandler callbackHandler,final Map<String, ?> sharedState,
		final Map<String, ?> options)
	{
		// We first have to declare valid module options so the parent class
		// does not complain about invalid module options in the configuration.
		addValidOptions(VALID_OPTIONS);

		// Now that the options are known we call the super implementation.
		super.initialize(subject,callbackHandler,sharedState,options);
	}

	/**
	 * Checks if the provided password matches the given hash using the bcrypt
	 * algorithm.
	 * @throws NullPointerException If either {@code password} or {@code hash}
	 *   are {@code null}.
	 */
	@Override
	protected boolean validatePassword(final String password,final String hash)
		throws NullPointerException
	{
		if (password == null || hash == null)
			throw new NullPointerException();

		return BCrypt.check(password,hash);
	}
}
