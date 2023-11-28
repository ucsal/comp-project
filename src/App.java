import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {

    private JTextArea areaDeTexto;
    private JButton botaoSelecionarArquivo;

    public App() {
        // Configurações da janela
        setTitle("Leitura de Arquivo");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        areaDeTexto = new JTextArea(10, 40);
        areaDeTexto.setEditable(false); // Impede a edição do texto
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);
        botaoSelecionarArquivo = new JButton("Selecionar Arquivo");
        botaoSelecionarArquivo.addActionListener(e -> selecionarArquivo());

        // Configuração do layout
        setLayout(new BorderLayout());

        // Adição dos componentes à janela
        add(scrollPane, BorderLayout.CENTER);
        add(botaoSelecionarArquivo, BorderLayout.SOUTH);
    }

    private void selecionarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos .232", "232");
        fileChooser.setFileFilter(filter);
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                String caminhoDoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

                /*
                 * 
                 * É aqui que pegamos as linhas para o compilador
                 * 
                 */
                String[] linhas = lerArquivo(caminhoDoArquivo);

                areaDeTexto.setText("");
                for (String linha : linhas) {
                    areaDeTexto.append(linha + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String[] lerArquivo(String caminhoDoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        }

        // Converter a lista para um array de strings
        return linhas.toArray(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App janela = new App();
            janela.setVisible(true);
        });
    }
}
