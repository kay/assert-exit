package org.neverfear.assertexit;

import static org.junit.Assert.fail;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Rule to assert that {@link System#exit(int)} will be invoked.
 * 
 * This done by replacing the system {@link SecurityManager}. Any existing
 * {@link SecurityManager} will be preserved with the exception of the
 * {@link SecurityManager#checkExit(int)} check which is intercepted.
 * 
 * The following tests pass:
 * 
 * <pre>
 * 
 * 
 * public class ExpectedExitTest {
 * 
 * 	&#064;Rule
 * 	public ExpectedExit expectedExit = ExpectedExit.noAttempt();
 * 
 * 	&#064;Test
 * 	public void doesNotInvokeExit() {
 * 		// does nothing
 * 	}
 * 
 * 	&#064;Test
 * 	public void invokesExitAsExpected() {
 * 		this.expectedExit.expectExit(7);
 * 		System.exit(7);
 * 	}
 * }
 * </pre>
 * 
 * The following tests will fail:
 * 
 * <pre>
 * 
 * 
 * public class ExpectedExitTest {
 * 
 * 	&#064;Rule
 * 	public ExpectedExit expectedExit = ExpectedExit.noAttempt();
 * 
 * 	&#064;Test
 * 	public void invokesExitUnwantedly() {
 * 		System.exit(1);
 * 	}
 * 
 * 	&#064;Test
 * 	public void invokesExitWantedlyButWrongCode() {
 * 		this.expectedExit.expectExit(1);
 * 		System.exit(2);
 * 	}
 * }
 * </pre>
 * 
 * @author doug@neverfear.org
 * 
 */
public class ExpectedExit
	implements TestRule {

	public static ExpectedExit noAttempt() {
		return new ExpectedExit();
	}

	private boolean expectsExit = false;
	private int expectedStatus = Integer.MIN_VALUE;

	private ExpectedExit() {}

	public void expectExit(final int expectedStatus) {
		this.expectsExit = true;
		this.expectedStatus = expectedStatus;
	}

	private class ExpectedExitStatement
		extends Statement {

		private final Statement next;

		public ExpectedExitStatement(final Statement base) {
			this.next = base;
		}

		@Override
		public void evaluate() throws Throwable {
			final ExitContext context = ExitContext.get();
			context.enable();
			try {
				this.next.evaluate();

				if (ExpectedExit.this.expectsExit) {
					fail("Expected test to exit with status " + ExpectedExit.this.expectedStatus);
				}

			} catch (final ExitDeniedSecurityException e) {
				if (!ExpectedExit.this.expectsExit) {
					fail("Exit was not expected but was invoked with status " + e.getStatus());
				}

				final int actual = e.getStatus();
				if (actual != ExpectedExit.this.expectedStatus) {
					fail("Wanted " + ExpectedExit.this.expectedStatus + " but was " + actual);
				}
			} finally {
				context.disable();
			}
		}
	}

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new ExpectedExitStatement(base);
	}

}
