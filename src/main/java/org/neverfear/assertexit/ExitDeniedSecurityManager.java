package org.neverfear.assertexit;

/**
 * Used for test purposes only. Throws an {@link ExitDeniedSecurityException} when
 * {@link System#exit(int)} is called containing the status code.
 * 
 * @author doug@neverfear.org
 * 
 */
final class ExitDeniedSecurityManager
	extends DelegateSecurityManager {

	ExitDeniedSecurityManager(final SecurityManager delegate) {
		super(delegate);
	}

	@Override
	public void checkExit(final int status) {
		throw new ExitDeniedSecurityException(status);
	}

}
