package com.bnaqica.loanapp.testhelper;

import org.testcontainers.containers.PostgreSQLContainer;

public class LoanAppPostgresqlContainer extends PostgreSQLContainer<LoanAppPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres";
    private static LoanAppPostgresqlContainer container;

    private LoanAppPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static LoanAppPostgresqlContainer getInstance() {
        if (container == null) {
            container = new LoanAppPostgresqlContainer();
        }

        Integer mappedPort = container.getMappedPort(POSTGRESQL_PORT);
        String containerIpAddress = container.getContainerIpAddress();

        System.out.println("jdbc:" + container.getJdbcUrl());
        System.out.println("username:" + container.getUsername());
        System.out.println("username:" + container.getPassword());

        return container;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("jdbc:" + container.getJdbcUrl());
        System.out.println("username:" + container.getUsername());
        System.out.println("username:" + container.getPassword());

        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
