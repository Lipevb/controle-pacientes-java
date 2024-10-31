package com.main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PacientesTabela {
    private final SimpleStringProperty name;
    private final SimpleStringProperty cpf;
    private final SimpleIntegerProperty code;

    public PacientesTabela(String name, String cpf, int code) {
        this.name = new SimpleStringProperty(name);
        this.cpf = new SimpleStringProperty(cpf);
        this.code = new SimpleIntegerProperty(code);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCpf() {
        return cpf.get();
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public int getCode() {
        return code.get();
    }

    public SimpleIntegerProperty codeProperty() {
        return code;
    }
}
