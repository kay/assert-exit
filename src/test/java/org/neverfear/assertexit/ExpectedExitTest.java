package org.neverfear.assertexit;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ExpectedExitTest {

	private final static class SystemExit
		implements Answer<Void> {

		private final int exitStatus;

		private SystemExit(final int exitStatus) {
			this.exitStatus = exitStatus;
		}

		@Override
		public Void answer(final InvocationOnMock invocation) throws Throwable {
			System.exit(this.exitStatus);
			return null;
		}
	}

	private Statement mockStatement;
	private ExpectedExit expectedExit;

	@Before
	public void before() {
		this.mockStatement = mock(Statement.class);
		this.expectedExit = ExpectedExit.noAttempt();
	}

	@Test
	public void wrappedStatementDoesNotInvokeExitAsExpected() throws Throwable {
		/*
		 * Given
		 */
		final Statement statement = this.expectedExit.apply(this.mockStatement, mock(Description.class));

		/*
		 * When
		 */
		statement.evaluate();

		/*
		 * Then
		 */
		// Success is measured by getting this far
	}

	@Test
	public void wrappedStatementInvokesExitAsExpected() throws Throwable {
		/*
		 * Given
		 */
		final int exitStatus = 7;
		doAnswer(new SystemExit(exitStatus)).when(this.mockStatement)
				.evaluate();

		this.expectedExit.expectExit(exitStatus);
		final Statement statement = this.expectedExit.apply(this.mockStatement, mock(Description.class));

		/*
		 * When
		 */
		statement.evaluate();

		/*
		 * Then
		 */
		// Success is measured by getting this far
	}

	@Test(expected = AssertionError.class)
	public void wrappedSatementInvokesExitUnexpectedly() throws Throwable {
		/*
		 * Given
		 */
		final int exitStatus = 7;
		doAnswer(new SystemExit(exitStatus)).when(this.mockStatement)
				.evaluate();

		final Statement statement = this.expectedExit.apply(this.mockStatement, mock(Description.class));

		/*
		 * When
		 */
		statement.evaluate();

		/*
		 * Then
		 */
		// Will throw AssertionError
	}

	@Test(expected = AssertionError.class)
	public void wrappedStatementInvokesExitAsExpectedButWrongCode() throws Throwable {
		/*
		 * Given
		 */
		final int exitStatus = 7;
		doAnswer(new SystemExit(5)).when(this.mockStatement)
				.evaluate();

		this.expectedExit.expectExit(exitStatus);
		final Statement statement = this.expectedExit.apply(this.mockStatement, mock(Description.class));

		/*
		 * When
		 */
		statement.evaluate();

		/*
		 * Then
		 */
		// Will throw AssertionError
	}
}
