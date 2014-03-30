package org.neverfear.assertexit;

import static java.lang.System.getSecurityManager;
import static java.lang.System.setSecurityManager;

final class ExitContext {

	private static final ExitContext INSTANCE = new ExitContext();

	private boolean enabled = false;
	private SecurityManager previous;

	/**
	 * 
	 * @return The previously set {@link SecurityManager} or null if none.
	 */
	public SecurityManager enable() {
		this.previous = getSecurityManager();

		final SecurityManager delegate;
		if (this.previous != null) {
			delegate = this.previous;
		} else {
			delegate = new PermissiveSecurityManager();
		}

		setSecurityManager(new ExitDeniedSecurityManager(delegate));
		this.enabled = true;

		return this.previous;
	}

	public void disable() {
		if (!this.enabled) {
			throw new IllegalStateException("Not enabled");
		}

		setSecurityManager(this.previous);
	}

	public static ExitContext get() {
		return INSTANCE;
	}
}