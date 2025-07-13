import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class BotonTraductor extends JComboBox<String> {

    private static final String API_URL = "https://api.mymemory.translated.net/get";
    private final String[] codigosIdiomas = {"es", "en", "fr"};

    public BotonTraductor() {
        super(new String[]{"Idioma", "Español", "Inglés", "Francés"});
        setPreferredSize(new Dimension(150, 25));

        addActionListener(e -> {
            String idiomaSeleccionado = (String) getSelectedItem();
            if (idiomaSeleccionado == null || idiomaSeleccionado.equals("Idioma")) return;
            
            traducirEtiquetas(idiomaSeleccionado);
        });
    }

    private void traducirEtiquetas(String idiomaDestino) {
        Container padre = getTopLevelAncestor();
        if (padre != null) {
            traducirComponentes(padre, idiomaDestino);
        }
    }

    private void traducirComponentes(Container contenedor, String idiomaDestino) {
        String codigoDestino = codigosIdiomas[getSelectedIndex() - 1];
        
        for (Component comp : contenedor.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel lbl = (JLabel) comp;
                String textoOriginal = lbl.getText();
                
                if (!textoOriginal.isEmpty() && !textoOriginal.equals("Idioma:")) {
                    new Thread(() -> {
                        try {
                            String textoTraducido = traducir(textoOriginal, "es", codigoDestino);
                            SwingUtilities.invokeLater(() -> {
                                if (!textoTraducido.isEmpty()) {
                                    lbl.setText(textoTraducido);
                                } else {
                                    lbl.setText(textoOriginal + " (Error)");
                                }
                            });
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(() -> lbl.setText(textoOriginal + " (Error)"));
                        }
                    }).start();
                }
            } else if (comp instanceof Container) {
                traducirComponentes((Container) comp, idiomaDestino);
            }
        }
    }

    private String traducir(String texto, String idiomaOrigen, String idiomaDestino) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8.toString());
            String url = String.format("%s?q=%s&langpair=%s%%7C%s", 
                                     API_URL, 
                                     textoCodificado, 
                                     idiomaOrigen, 
                                     idiomaDestino);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("User-Agent", "Java/HttpClient") // Algunas APIs requieren User-Agent
                .GET()
                .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                if (jsonResponse.has("responseData")) {
                    JSONObject responseData = jsonResponse.getJSONObject("responseData");
                    if (responseData.has("translatedText")) {
                        String traduccion = responseData.getString("translatedText");
                        if (!traduccion.isEmpty()) {
                            return traduccion;
                        }
                    }
                }
                System.err.println("Respuesta vacía o incompleta de la API");
                return "";
            } else {
                System.err.println("Error HTTP " + response.statusCode() + ": " + response.body());
                return "";
            }
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Traductor con ComboBox (MyMemory)");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(300, 250);
            ventana.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            Font font = new Font("SansSerif", Font.PLAIN, 16);

            JLabel lbl1 = new JLabel("Nombre");
            JLabel lbl2 = new JLabel("Edad");
            JLabel lbl3 = new JLabel("Correo");
            JLabel lbl4 = new JLabel("Dirección");

            for (JLabel lbl : new JLabel[]{lbl1, lbl2, lbl3, lbl4}) {
                lbl.setFont(font);
                lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.add(lbl);
                panel.add(Box.createRigidArea(new Dimension(0, 8)));
            }

            BotonTraductor combo = new BotonTraductor();
            combo.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(new JLabel("Idioma:"));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(combo);

            ventana.setContentPane(panel);
            ventana.setVisible(true);
        });
    }
}