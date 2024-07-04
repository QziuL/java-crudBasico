package br.qziul;

import br.qziul.dao.UserDAO;
import br.qziul.database.ConnectionFactory;
import br.qziul.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *  Estudo de CRUD simples e básico com PostgreSQL
 *  Dependência adicionada: postgresql
 *  @autor QziuL(Github)
 */

public class TesteConexao {
    public static void main(String[] args)  {
        try {
            Connection conexao = ConnectionFactory.getConnection(
                    "localhost",
                    "5432",
                    "crud",
                    "postgres",
                    "postgres"
            );

            if(conexao != null) {
                System.out.println("Conectado ao banco de dados.");

                UserDAO userDAO = new UserDAO(conexao);

                /*
                    COMANDOS CRUD
                    insert(userDAO);
                    selectAll(userDAO);
                    deleteAll(userDAO);
                    updateById(userDAO, 1);
                    selectById(userDAO, 1);
                    deleteById(userDAO, 7);

                 */

                conexao.close();
            } else {
                System.out.println("Falha na conexão com o banco de dados!");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAll(UserDAO userDAO) throws SQLException {
        List<User> users = userDAO.selectAll();

        System.out.println("\nTodos os usuários: ");
        for(User user : users) {
            System.out.println(user);
        }
    }

    public static void selectById(UserDAO userDAO, Integer id) throws SQLException {
        User user = userDAO.selectById(id);
        if (user != null) {
            System.out.println("\nUsuário encontrado: \n"+user);
        }
    }

    public static void insert(UserDAO userDAO) throws SQLException{
        User user = new User();
        user.setNome("Luiz Fernando 3");
        user.setSenha("password");
        boolean inseriu = userDAO.insert(user);
        if(inseriu) {
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Não inseriu? "+ inseriu);
        }
    }

    public static void updateById(UserDAO userDAO, Integer id) throws SQLException {
        System.out.println(
                (userDAO.updateById("Luizao", "password" ,id))
                    ? "Atualizado com sucesso."
                    : "Usuário não atualizado."
        );
    }

    public static void deleteById(UserDAO userDAO, Integer id) throws SQLException {
        System.out.println("\nDeletado? ");
        System.out.println(
                (userDAO.deleteById(id) == 1)
                    ? "Usuário deletado com sucesso."
                    : "Verifique o ID"
        );
    }

    public static void deleteAll(UserDAO userDAO) throws SQLException{
        userDAO.deleteAll();
        System.out.println("\nDeletado todos os registros!");
    }
}
