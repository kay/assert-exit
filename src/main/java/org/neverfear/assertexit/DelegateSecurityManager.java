package org.neverfear.assertexit;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

/**
 * Glue designed to allow a default delegate to implement most of the behaviour,
 * but for a sub class to override specific methods.
 * 
 * @author doug@neverfear.org
 * 
 */
abstract class DelegateSecurityManager
	extends SecurityManager {

	private final SecurityManager delegate;

	DelegateSecurityManager(final SecurityManager delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public boolean getInCheck() {
		return this.delegate.getInCheck();
	}

	@Override
	public Object getSecurityContext() {
		return this.delegate.getSecurityContext();
	}

	@Override
	public void checkPermission(final Permission perm) {
		this.delegate.checkPermission(perm);
	}

	@Override
	public void checkPermission(final Permission perm, final Object context) {
		this.delegate.checkPermission(perm, context);
	}

	@Override
	public void checkCreateClassLoader() {
		this.delegate.checkCreateClassLoader();
	}

	@Override
	public void checkAccess(final Thread t) {
		this.delegate.checkAccess(t);
	}

	@Override
	public void checkAccess(final ThreadGroup g) {
		this.delegate.checkAccess(g);
	}

	@Override
	public void checkExit(final int status) {
		this.delegate.checkExit(status);
	}

	@Override
	public void checkExec(final String cmd) {
		this.delegate.checkExec(cmd);
	}

	@Override
	public void checkLink(final String lib) {
		this.delegate.checkLink(lib);
	}

	@Override
	public void checkRead(final FileDescriptor fd) {
		this.delegate.checkRead(fd);
	}

	@Override
	public void checkRead(final String file) {
		this.delegate.checkRead(file);
	}

	@Override
	public void checkRead(final String file, final Object context) {
		this.delegate.checkRead(file, context);
	}

	@Override
	public void checkWrite(final FileDescriptor fd) {
		this.delegate.checkWrite(fd);
	}

	@Override
	public void checkWrite(final String file) {
		this.delegate.checkWrite(file);
	}

	@Override
	public void checkDelete(final String file) {
		this.delegate.checkDelete(file);
	}

	@Override
	public void checkConnect(final String host, final int port) {
		this.delegate.checkConnect(host, port);
	}

	@Override
	public void checkConnect(final String host, final int port, final Object context) {
		this.delegate.checkConnect(host, port, context);
	}

	@Override
	public void checkListen(final int port) {
		this.delegate.checkListen(port);
	}

	@Override
	public void checkAccept(final String host, final int port) {
		this.delegate.checkAccept(host, port);
	}

	@Override
	public void checkMulticast(final InetAddress maddr) {
		this.delegate.checkMulticast(maddr);
	}

	@Override
	public void checkMulticast(final InetAddress maddr, final byte ttl) {
		this.delegate.checkMulticast(maddr, ttl);
	}

	@Override
	public void checkPropertiesAccess() {
		this.delegate.checkPropertiesAccess();
	}

	@Override
	public void checkPropertyAccess(final String key) {
		this.delegate.checkPropertyAccess(key);
	}

	@Override
	public boolean checkTopLevelWindow(final Object window) {
		return this.delegate.checkTopLevelWindow(window);
	}

	@Override
	public void checkPrintJobAccess() {
		this.delegate.checkPrintJobAccess();
	}

	@Override
	public void checkSystemClipboardAccess() {
		this.delegate.checkSystemClipboardAccess();
	}

	@Override
	public void checkAwtEventQueueAccess() {
		this.delegate.checkAwtEventQueueAccess();
	}

	@Override
	public void checkPackageAccess(final String pkg) {
		this.delegate.checkPackageAccess(pkg);
	}

	@Override
	public void checkPackageDefinition(final String pkg) {
		this.delegate.checkPackageDefinition(pkg);
	}

	@Override
	public void checkSetFactory() {
		this.delegate.checkSetFactory();
	}

	@Override
	public void checkMemberAccess(final Class<?> clazz, final int which) {
		this.delegate.checkMemberAccess(clazz, which);
	}

	@Override
	public void checkSecurityAccess(final String target) {
		this.delegate.checkSecurityAccess(target);
	}

	@Override
	public ThreadGroup getThreadGroup() {
		return this.delegate.getThreadGroup();
	}

}
