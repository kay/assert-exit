package org.neverfear.assertexit;

import static java.lang.System.getSecurityManager;
import static java.lang.System.setSecurityManager;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExistingSecurityManagerTest {

	private static final String COMPUTER_SAYS_NO = "Computer says no";
	private static SecurityManager denyReadingFromTempFileSecurityManager;
	private static SecurityManager originalSecurityManager;

	private static File tempFile;

	@BeforeClass
	public static void beforeClass() throws Exception {
		originalSecurityManager = getSecurityManager();

		tempFile = File.createTempFile(ExistingSecurityManagerTest.class.getSimpleName(), "DeniedFile");
		tempFile.deleteOnExit();

		denyReadingFromTempFileSecurityManager = new DelegateSecurityManager(new PermissiveSecurityManager()) {

			@Override
			public void checkRead(final String file) {
				if (tempFile.toString()
						.equals(file.toString())) {
					throw new SecurityException(COMPUTER_SAYS_NO);
				}
			}
		};

		setSecurityManager(denyReadingFromTempFileSecurityManager);
	}

	@AfterClass
	public static void afterClass() {
		setSecurityManager(originalSecurityManager);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private ExpectedExit expectedExit;

	@Before
	public void before() {
		this.expectedExit = ExpectedExit.noAttempt();
	}

	@Test
	public void givenExistingSecurityManagerAndExpectedExit_whenTriggerUnrelatedCheck_expectOriginalSecurityManagerIsInvoked()
			throws Exception {
		/*
		 * Given
		 */
		this.expectedExit.expectExit(1);

		/*
		 * Then
		 */
		this.expectedException.expect(SecurityException.class);
		this.expectedException.expectMessage(COMPUTER_SAYS_NO);

		/*
		 * When
		 */
		{
			/*
			 * Anger the original SecurityManager by reading it's length
			 */
			tempFile.length();
		}
	}
}
