package com.jdbc;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcAprendendoApplication implements CommandLineRunner {

    @Autowired

   public ClienteRepository clienteRepository;
    public static void main(String[] args) {

        SpringApplication.run(JdbcAprendendoApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {

       clienteRepository.buscarCliente(2);



    }
}
