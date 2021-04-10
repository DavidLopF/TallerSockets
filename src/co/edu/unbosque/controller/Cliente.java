package co.edu.unbosque.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public Cliente() throws IOException {
        try (var socket = new Socket("127.0.0.27", 2212)) {

            System.out.println("Usuario conectado " + socket);

            System.out.println("....:: BIENVENIDO  USUARIO ::.....");
            menuLogin(socket);
        }
    }

    public void menuLogin(Socket socket) throws IOException {
        var in = new Scanner(socket.getInputStream());
        var out = new PrintWriter(socket.getOutputStream(), true);

        Scanner leer = new Scanner(System.in);


        System.out.println("\nSeleccione una opcion: ");
        System.out.println("\n1. Ingresar" + "\n2. Registrarse");
        String mensaje = leer.nextLine();

        if (mensaje.equals("1")) {
            System.out.println("...: INICIO DE SESION :...");
            System.out.println("\n1. Ingrese nombre de usuario :");
            mensaje = leer.nextLine();

            System.out.println("\n2. Ingrese contrase�a : ");
            out.println(mensaje + ";" + leer.nextLine() + ";" + "1");

            mensaje = in.nextLine();

            System.out.println("_________________________________________________");
            if (mensaje.equals("Usuario logueado con exito. ")) {
                System.out.println("Usuario logueado con exito. ");

                String tipo = in.nextLine();

                if (tipo.equals("cliente")) {

                    System.out.println("\nSeleccione una opcion:" + "\n 1. Crear caso." + "\n 2. Hablar con un agente.");
                    out.println(leer.nextInt());

                    System.out.println("Esperando por asesores...........");
                    while (leer.hasNextLine()) {
                        String input = in.nextLine();
                        if (input.equals("salir")) {
                            return;
                        } else {
                            System.out.println(input);
                            out.println(leer.nextLine());
                        }
                    }

                    System.out.println("...:: HASTA PRONTO :...");
                } else if (tipo.equals("asesor")) {

                    System.out.println("Esperando por clientes............");

                    while (leer.hasNextLine()) {
                        out.println(leer.nextLine());
                        System.out.println(in.nextLine());
                    }

                }
            } else {
                System.out.println("Error ingresando nombre de usuario o contraseña.");
                menuLogin(socket);
            }


        } else if (mensaje.equals("2")) {

            System.out.println("...: REGISTRARSE :...");

            System.out.println("\n1. Ingrese nombre de usuario :");
            mensaje = leer.nextLine();

            System.out.println("\n2. Seleccione tipo de usuario :\n" + "\n1. Cliente" + "\n2. Asesor");
            String temp = leer.nextLine();

            if (temp.equals("1")) {
                mensaje += ";" + "cliente";
            } else if (temp.equals("2")) {
                mensaje += ";" + "asesor";
            }

            System.out.println("\n3. Ingrese contraseña : ");
            mensaje += ";" + leer.nextLine() + ";" + 2;
            out.println(mensaje);
            if (in.nextBoolean()) {
                System.out.println("Usuario registrado con exito");
                System.out.println("Ahora inicie sesion :).");
                menuLogin(socket);
            } else {
                System.out.println("Error en el ingreso de los datos, verifique !!!!");
                menuLogin(socket);
            }

        }
    }


}
