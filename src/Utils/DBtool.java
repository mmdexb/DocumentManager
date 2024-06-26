package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DBtool<T> {
    private Connection connection;

    public DBtool() {
        try {
            connection = DBconnector.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<T> executeQuery(String sql, Function<ResultSet, T> mapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, parameters);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.apply(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询失败", e);
        }
        return results;
    }

    public int executeUpdate(String sql, Object... parameters) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("更新失败", e);
        }
    }

    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }

}
