package logica;

public enum Divisa {
    PESO_COLOMBIANO("COP"),
    DOLAR("USD"),
    REAL_BRASILENO("BRL"),
    PESO_ARGENTINO("ARS");

    private final String codigo;

    Divisa(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

