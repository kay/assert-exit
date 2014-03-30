package org.neverfear.assertexit;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

/**
 * A security manager that does not deny anything.
 * 
 * @author doug@neverfear.org
 * 
 */
class PermissiveSecurityManager
	extends SecurityManager {

	@Override
	public void checkPermission(final Permission perm) {}

	@Override
	public void checkPermission(final Permission perm, final Object context) {}

	@Override
	public void checkCreateClassLoader() {}

	@Override
	public void checkAccess(final Thread t) {}

	@Override
	public void checkAccess(final ThreadGroup g) {}

	@Override
	public void checkExit(final int status) {}

	@Override
	public void checkExec(final String cmd) {}

	@Override
	public void checkLink(final String lib) {}

	@Override
	public void checkRead(final FileDescriptor fd) {}

	@Override
	public void checkRead(final String file) {}

	@Override
	public void checkRead(final String file, final Object context) {}

	@Override
	public void checkWrite(final FileDescriptor fd) {}

	@Override
	public void checkWrite(final String file) {}

	@Override
	public void checkDelete(final String file) {}

	@Override
	public void checkConnect(final String host, final int port) {}

	@Override
	public void checkConnect(final String host, final int port, final Object context) {}

	@Override
	public void checkListen(final int port) {}

	@Override
	public void checkAccept(final String host, final int port) {}

	@Override
	public void checkMulticast(final InetAddress maddr) {}

	@Override
	public void checkMulticast(final InetAddress maddr, final byte ttl) {}

	@Override
	public void checkPropertiesAccess() {}

	@Override
	public void checkPropertyAccess(final String key) {}

	@Override
	public boolean checkTopLevelWindow(final Object window) {
		return true;
	}

	@Override
	public void checkPrintJobAccess() {}

	@Override
	public void checkSystemClipboardAccess() {}

	@Override
	public void checkAwtEventQueueAccess() {}

	@Override
	public void checkPackageAccess(final String pkg) {}

	@Override
	public void checkPackageDefinition(final String pkg) {}

	@Override
	public void checkSetFactory() {}

	@Override
	public void checkMemberAccess(final Class<?> clazz, final int which) {}

	@Override
	public void checkSecurityAccess(final String target) {}

}
