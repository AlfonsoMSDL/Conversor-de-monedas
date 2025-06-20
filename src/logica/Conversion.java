package logica;

import dto.ConversionDto;

public class Conversion {
    private String divisaBase;
    private String divisaConvertir;
    private float cantidadAConvertir;
    private float cantidadConvertida;

    public Conversion(String divisaBase, String divisaConvertir, float cantidad) {
        this.divisaBase = divisaBase;
        this.divisaConvertir = divisaConvertir;
        this.cantidadConvertida = cantidad;
    }

    public Conversion(ConversionDto conversionDto) {
        this.divisaBase = conversionDto.base_code();
        this.divisaConvertir = conversionDto.target_code();
        this.cantidadConvertida = conversionDto.conversion_result();
    }

    public String getDivisaBase() {
        return divisaBase;
    }

    public void setDivisaBase(String divisaBase) {
        this.divisaBase = divisaBase;
    }

    public float getCantidadConvertida() {
        return cantidadConvertida;
    }

    public void setCantidadConvertida(float cantidadConvertida) {
        this.cantidadConvertida = cantidadConvertida;
    }

    public String getDivisaConvertir() {
        return divisaConvertir;
    }

    public void setDivisaConvertir(String divisaConvertir) {
        this.divisaConvertir = divisaConvertir;
    }

    public float getCantidadAConvertir() {
        return cantidadAConvertir;
    }

    public void setCantidadAConvertir(float cantidadAConvertir) {
        this.cantidadAConvertir = cantidadAConvertir;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "divisaBase='" + divisaBase + '\'' +
                ", divisaConvertir='" + divisaConvertir + '\'' +
                ", cantidadAConvertir='" + cantidadAConvertir + '\'' +
                ", cantidadConvertida=" + cantidadConvertida +
                '}';
    }
}
