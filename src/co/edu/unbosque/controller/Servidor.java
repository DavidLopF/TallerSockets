package co.edu.unbosque.controller;

import co.edu.unbosque.model.UsuarioDAO;
import co.edu.unbosque.model.persitence.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;

public class Servidor {
    private static Set<PrintWriter> writers = new HashSet<>();

    public static void main(String args[]) throws IOException {
        try (var listener = new ServerSocket(2212)) {


            System.out.println("Chat bot server corriendo ........");
            var pool = Executors.newFixedThreadPool(10);

            while (true) {
                pool.execute(new Chat(listener.accept()));
            }
        }
    }

    private static class Chat implements Runnable {

        private Socket socket;
        private UsuarioDAO usuarioDAO;

        public Chat(Socket socket) {
            this.socket = socket;
            usuarioDAO = new UsuarioDAO();

        }


        @Override
        public void run() {
            safePrintln("conectado usuario " + socket);
            usuarioDAO.cargarUsuarios();

            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);

                while (in.hasNextLine()) {
                    String mensaje = in.nextLine();
                    if (mensaje.substring(mensaje.length() - 1, mensaje.length()).equals("1")) {

                        String[] ingreso = mensaje.split(";");


                        if (usuarioDAO.ingresoUsuario(ingreso[0], ingreso[1])) {
                            writers.add(out);
                            safePrintln("" + writers.size());
                            out.println("Usuario logueado con exito. ");
                            usuarioDAO.activarUsuario(ingreso[0], ingreso[1]);
                        } else {

                            out.println("Error ingresando nombre de usuario o contraseña.");
                        }
                        Usuario primero = usuarioDAO.buscarUsuario(ingreso[0], ingreso[1]);
                        out.println(primero.getTipo());

                        if (primero.getTipo().equals("cliente")) {
                            String opcion = in.nextLine();

                            if (opcion.equals("1")) {

                                //parte de crear caso

                            } else if (opcion.equals("2")) {
                                while (true) {
                                    String input = in.nextLine();
                                    if (input.equals("salir")) {
                                        break;
                                    } else {
                                        for (PrintWriter writer : writers) {
                                            writer.println("MENSAJE DE " + ingreso[0] + " : " + input);
                                        }

                                    }
                                }

                            }
                        } else if (primero.getTipo().equals("asesor")) {
                            while (true) {
                                String input = in.nextLine();
                                if (input.equals("salir")) {
                                    break;
                                } else {
                                    for (PrintWriter writer : writers) {
                                        writer.println("MENSAJE DE " + ingreso[0] + " : " + input);
                                    }

                                }
                            }


                        }


                    } else if (mensaje.substring(mensaje.length() - 1, mensaje.length()).equals("2")) {

                        String[] registro = mensaje.split(";");

                        if (!registro[0].isEmpty() && !registro[1].isEmpty() && !registro[2].isEmpty()) {

                            Usuario user = new Usuario(registro[0], registro[1], registro[2], false);
                            usuarioDAO.registroUsuario(user);
                            safePrintln(user.toString());
                            out.println(true);
                        } else {
                            out.println(false);
                        }


                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void safePrintln(String s) {
            synchronized (System.out) {
                System.out.println(s);
            }
        }

    }


}
