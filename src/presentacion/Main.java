package presentacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ConversionDto;
import logica.Conversion;
import logica.Divisa;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int opcionBase;
        do {
            opcionBase = menu("Digite la divisa base: ");
        }while(opcionBase == -1);

        boolean continuar;
        float cantidadAConvertir = 0;
        do {
            try{
                 cantidadAConvertir= Float.parseFloat(JOptionPane.showInputDialog("Digite la cantidad a convertir"));
                 continuar = true;
            }catch (NumberFormatException | NullPointerException e){
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un valor numerico");
                continuar = false;
            }
        }while(!continuar);


        int opcionConvertir;
        do {
            opcionConvertir = menu("Digite la divisa a convertir: ");
        }while(opcionConvertir == -1);


        String divisaBase = asignarDivisa(opcionBase);
        String divisaConvertir = asignarDivisa(opcionConvertir);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/b112f271b18c787e5d03f8e7/pair/"+divisaBase+"/"+divisaConvertir+"/"+cantidadAConvertir))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ConversionDto conversionDto = gson.fromJson(response.body(), ConversionDto.class);

        Conversion conversion = new Conversion(conversionDto);
        conversion.setCantidadAConvertir(cantidadAConvertir);

        JOptionPane.showMessageDialog(null, conversion.getCantidadAConvertir()+" "+conversion.getDivisaBase()+" son "+conversion.getCantidadConvertida()+ " "+conversion.getDivisaConvertir());

        System.out.println("Ejecucion finalizada");
    }

    public static int menu(String peticion){
        int opcion;
        do {
            String texto = ".:MENU:.\n" +
                    "1. COP (Peso colombiano).\n" +
                    "2. USD (Estados unidos).\n" +
                    "3. BRL (Real Brasileño).\n" +
                    "4. ARS (Peso Argentino).\n" +
                    "\n" +
                    peticion;


            try{
                opcion= Integer.parseInt(JOptionPane.showInputDialog(texto));

                if(opcion < 1 || opcion > 4){
                    JOptionPane.showMessageDialog(null, "Opcion no valida!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException e){
                System.err.println("Debe digitar un valor numerico");
                opcion = -1;
                break;
            }


        }while(opcion < 1 || opcion > 4);

        return opcion;

    }

    public static String asignarDivisa(int opcion){
        //La divisa sera elegida dado el numero que el usuario haya seleccionado en el menu

        return switch (opcion) {
            case 1 -> Divisa.PESO_COLOMBIANO.getCodigo();
            case 2 -> Divisa.DOLAR.getCodigo();
            case 3 -> Divisa.REAL_BRASILENO.getCodigo();
            case 4 -> Divisa.PESO_ARGENTINO.getCodigo();
            default ->
                    ""; //Esta posibilidad nunca va a entrar porque en el metodo menu ya confirmo que se estan digitando numeros del 1 al 4
        };
    }
}