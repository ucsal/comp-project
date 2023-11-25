import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JTextArea areaDeCodigo;
    private JButton botaoEnviar;

    public App() {
        setTitle("Inserção de Código");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaDeCodigo = new JTextArea(80, 80);
        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(e -> enviarCodigo());

        setLayout(new BorderLayout());

        add(new JScrollPane(areaDeCodigo), BorderLayout.CENTER);
        add(botaoEnviar, BorderLayout.SOUTH);
    }

    private void enviarCodigo() {
        /*
         * 
         * 
         * Metodos de compilação e tradução
         * 
         * 
         */
        mostrarMensagem(areaDeCodigo.getText());
    }

    private void mostrarMensagem(String mensagem) {
        JTextArea areaDeTextoDialog = new JTextArea(mensagem);
        areaDeTextoDialog.setEditable(false);

        JOptionPane.showMessageDialog(this, new JScrollPane(areaDeTextoDialog), "Código Inserido",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App janela = new App();
            janela.setVisible(true);
        });
    }
}
