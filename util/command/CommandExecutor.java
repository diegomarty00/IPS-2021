package util.command;

import java.sql.Connection;
import java.sql.SQLException;

import persistencia.PersistenceException;
import util.BusinessException;
import util.jdbc.Jdbc;

public class CommandExecutor {
	public <T> T execute(Command<T> cmd) throws BusinessException {
		Connection c = null;
		try {
			try {
				c = Jdbc.createThreadConnection();
				T res = cmd.execute();
				c.commit();
				
				return res;

			} catch (BusinessException e) {
				c.rollback();
				throw e;
			}
		} catch (SQLException e ) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(c);
		}
		
	}

}