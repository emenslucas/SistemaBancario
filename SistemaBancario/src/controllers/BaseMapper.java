package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface BaseMapper<T> {
	public T toEntity(ResultSet resultSet);
	public List<T> toList(ResultSet resultSet);
	public PreparedStatement validarParametros(PreparedStatement prepareStatement, T parametro);

}
