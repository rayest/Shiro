package cn.rayest.realm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rayest on 2016/10/30 0030.
 */
public class UserService {
    public User getByUserName(Connection connection, String userName) throws SQLException {
        User resultUser = null;
        String sql = "SELECT * FROM t_user WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()) {
            resultUser = new User();
            resultUser.setId(resultset.getInt("id"));
            resultUser.setUserName(resultset.getString("userName"));
            resultUser.setPassword(resultset.getString("password"));
        }
        return resultUser;
    }

    public Set<String> getRoles(Connection connection, String userName) throws SQLException {
        Set<String> roles = new HashSet<String>();
        String sql = "SELECT * FROM t_user u, t_role r WHERE u.roleId = r.id AND u.userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            roles.add(resultSet.getString("roleName"));
        }
        return roles;

    }

    public Set<String> getPermissions(Connection connection, String userName) throws SQLException {
        Set<String> permissions = new HashSet<String>();
        String sql = "SELECT * FROM t_user u, t_role r, t_permission p WHERE u.roleId = r.id AND p.roleId = r.id AND u.userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            permissions.add(resultSet.getString("permissionName"));
        }
        return permissions;

    }
}
