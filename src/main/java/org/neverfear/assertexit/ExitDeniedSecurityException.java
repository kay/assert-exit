package org.neverfear.assertexit;

final class ExitDeniedSecurityException
	extends SecurityException {

	private static final long serialVersionUID = 618060259432415688L;
	private final int status;

	public ExitDeniedSecurityException(final int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}
}