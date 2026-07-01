package com.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class ClienteRepository {

    @Autowired
    private DataSource dataSource;

public void  atualizarEmail (int id, String novoEmail){
    String sql = "UPDATE clientes SET email = ? WHERE id = ?";
    try
            ( Connection connection = dataSource.getConnection();
          PreparedStatement pstd = connection.prepareStatement(sql);){
        pstd.setString(1, novoEmail);
        pstd.setInt(2, id);

        int linhas = pstd.executeUpdate();
        if (linhas>0){
            System.out.println("Cliente com o ID: "+ id + "teve o email atualizado");

        }
        else {
            System.out.println("Nenhum cliente encontrado");
        }

    } catch (SQLException e) {
        System.out.println("Erro"+ e.getMessage());
        throw new RuntimeException(e);
    }

}
public void adicionarCliente (String nome, String email){
    String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

    try
            ( Connection connection = dataSource.getConnection();
              PreparedStatement pstd = connection.prepareStatement(sql);){
        pstd.setString(1, nome);
        pstd.setString(2, email);

        int linhas = pstd.executeUpdate();
        if (linhas>0){
            System.out.println("Cliente criado com sucesso");

        }
        else {
            System.out.println("Não foi possivel criar cliente");
        }

    } catch (SQLException e) {
        System.out.println("Erro"+ e.getMessage());
        throw new RuntimeException(e);
    }
}
public void deletarCliente (int id){
    String sql = "DELETE FROM clientes WHERE id=?";
    try
            ( Connection connection = dataSource.getConnection();
              PreparedStatement pstd = connection.prepareStatement(sql);){
        pstd.setInt(1, id);


        int linhas = pstd.executeUpdate();
        if (linhas>0){
            System.out.println("Cliente excluido com sucesso");

        }
        else {
            System.out.println("Não foi possivel excluir cliente");
        }

    } catch (SQLException e) {
        System.out.println("Erro"+ e.getMessage());
        throw new RuntimeException(e);
    }

}
public void buscarCliente (int id){
    String sql = "SELECT * FROM clientes WHERE id=?";
    try (Connection connection = dataSource.getConnection();
         PreparedStatement pstd = connection.prepareStatement(sql)
        ) {
        pstd.setInt(1, id);
        try (ResultSet resultSet = pstd.executeQuery()){
            if(resultSet.next()){
                System.out.printf("Cliente encontrado: ID: %d, Nome: %s, email: %s",
                        resultSet.getInt("id"), resultSet.getString("nome"),resultSet.getString("email"));
            }
            else {
                System.out.printf("Cliente não de ID%d encotrado",id);
            }


        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


}
}
