package org.itech.equipment.exception;

/**
 * 
 * @author pascal
 *
 */

public class OperationFailedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OperationFailedException(String msg) {
		super(msg);
	}

	public OperationFailedException() {
		super("Echec lors de l'opération!");
	}
}
