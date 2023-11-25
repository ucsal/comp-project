import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private JTextArea areaDeCodigo;
    private JButton botaoEnviar;

    public App() {
        // Configurações da janela
        setTitle("Inserção de Código");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        areaDeCodigo = new JTextArea(10, 40); // 10 linhas, 40 colunas
        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(e -> enviarCodigo());

        // Configuração do layout
        setLayout(new BorderLayout());

        // Adição dos componentes à janela
        add(new JScrollPane(areaDeCodigo), BorderLayout.CENTER);
        add(botaoEnviar, BorderLayout.SOUTH);
    }

    private void enviarCodigo() {
        String codigoInserido = areaDeCodigo.getText();

        // Usando JOptionPane com um JTextArea para permitir seleção e cópia
        JTextArea areaDeTextoDialog = new JTextArea(codigoInserido);
        areaDeTextoDialog.setEditable(false); // Impede a edição do texto

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
