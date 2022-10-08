package util.command;

import util.BusinessException;

public interface Command<T> {

	T execute() throws BusinessException; 
}