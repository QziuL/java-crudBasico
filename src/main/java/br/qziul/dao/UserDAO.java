package br.qziul.dao;

import br.qziul.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String querySelectAll = "SELECT * FROM tb_users;";
    private String queryInsertoInto = "INSERT INTO tb_users(nome, senha) VALUES (?, ?);";
    private String queryDeleteAll = "DELETE FROM tb_users;";
    private String queryDeleteById = "DELETE FROM tb_users WHERE id = ?;";
    private String querySelectById = "SELECT * FROM tb_users WHERE id = ?;";
    private String queryUpdateById = "UPDATE tb_users SET nome = ?, senha = ? WHERE id = ?;";
    private PreparedStatement pstUpdateById;
    private PreparedStatement pstSelectById;
    private PreparedStatement pstSelectAll;
    private PreparedStatement pstInsertoInto;
    private PreparedStatement pstDeleteAll;
    private PreparedStatement pstDeleteById;

    public UserDAO(Connection connection) throws SQLException {
        pstSelectAll = connection.prepareStatement(querySelectAll);
        pstInsertoInto = connection.prepareStatement(queryInsertoInto);
        pstDeleteAll = connection.prepareStatement(queryDeleteAll);
        pstDeleteById = connection.prepareStatement(queryDeleteById);
        pstSelectById = connection.prepareStatement(querySelectById);
        pstUpdateById = connection.prepareStatement(queryUpdateById);
    }

    public List<User> selectAll() throws SQLException {
        ResultSet rs = pstSelectAll.executeQuery();
        ArrayList<User> usuarios = new ArrayList<>();
        while(rs.next()) {
            User usuario = new User();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public User selectById(Integer id) throws SQLException {
        pstSelectById.clearParameters();
        pstSelectById.setInt(1, id);
        ResultSet rs = pstSelectById.executeQuery();
        User usuario = new User();
        if(rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
        }
        return usuario;
    }

    public boolean insert(User user) throws SQLException {
        pstInsertoInto.clearParameters();
        pstInsertoInto.setString(1, user.getNome());
        pstInsertoInto.setString(2, user.getSenha());
        return pstInsertoInto.executeUpdate() == 1;
    }

    public boolean updateById(String nome, String senha, Integer id) throws SQLException {
        pstUpdateById.clearParameters();
        pstUpdateById.setString(1, nome);
        pstUpdateById.setString(2, senha);
        pstUpdateById.setInt(3, id);
        return pstUpdateById.executeUpdate() == 1;
    }

    public void deleteAll() throws SQLException {
        pstDeleteAll.executeUpdate();
    }

    public int deleteById(Integer id) throws SQLException {
        pstDeleteById.clearParameters();
        pstDeleteById.setInt(1, id);
        return pstDeleteById.executeUpdate();
    }
}
