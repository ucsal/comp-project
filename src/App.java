import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {

    private JTextArea areaDeTexto;
    private JButton botaoSelecionarArquivo;
    private String cabecalho = "Código da equipe: 04" + "\n" +
            "Componentes:" + "\n" +
            "       Cândido Ribeiro Peralva Neto;candido.neto@ucsal.edu.br;(74)988484843" + "\n" +
            "       Davi Brito de Moura Pires;davi.pires@ucsal.edu.br;(71) 9134-9388" + "\n" +
            "       SAMIR DANTAS;samir.barreto@ucsal.edu.br;(71) 8650-0010" + "\n" +
            "       Yllo Luis;yllo.jesus@ucsal.edu.br;(71) 8129-0020";

    public App() {
        setTitle("Leitura de Arquivo");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaDeTexto = new JTextArea(10, 40);
        areaDeTexto.setEditable(false); // Impede a edição do texto
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);
        botaoSelecionarArquivo = new JButton("Selecionar Arquivo");
        botaoSelecionarArquivo.addActionListener(e -> selecionarArquivo());

        setLayout(new BorderLayout());

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
                File arquivoSelecionado = fileChooser.getSelectedFile();
                String caminhoDoArquivo232 = arquivoSelecionado.getAbsolutePath();
                String nomeDoArquivoSemExtensao = getNomeDoArquivoSemExtensao(arquivoSelecionado);
                String diretorioDoArquivo = arquivoSelecionado.getParent();

                /**
                 * 
                 * Linhas do arquivo original onde vamos trabalhar
                 * 
                 */
                String[] linhas = lerArquivo(caminhoDoArquivo232);

                areaDeTexto.setText("");
                for (String linha : linhas) {
                    areaDeTexto.append(linha + "\n");
                }

                // Parte do compilador que vai fazer o TAB e devolver um array de linhas
                String caminhoArquivoTAB = diretorioDoArquivo + File.separator + nomeDoArquivoSemExtensao + ".TAB";
                salvarComoArquivo(linhas, caminhoArquivoTAB);

                // Parte do compilador que vai fazer o LEX e devolver um array de linhas
                String caminhoArquivoLEX = diretorioDoArquivo + File.separator + nomeDoArquivoSemExtensao + ".LEX";
                salvarComoArquivo(linhas, caminhoArquivoLEX);

                JOptionPane.showMessageDialog(this,
                        "Arquivos salvos em:\n" + caminhoArquivoTAB + "\n" + caminhoArquivoLEX);
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

        return linhas.toArray(new String[0]);
    }

    private void salvarComoArquivo(String[] linhas, String caminhoDoArquivo) {

        try (PrintWriter escritor = new PrintWriter(new FileWriter(caminhoDoArquivo))) {
            escritor.println(cabecalho + "\n");
            for (String linha : linhas) {
                escritor.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getNomeDoArquivoSemExtensao(File arquivo) {
        String nomeDoArquivo = arquivo.getName();
        int posicaoPonto = nomeDoArquivo.lastIndexOf('.');

        if (posicaoPonto > 0 && posicaoPonto < nomeDoArquivo.length() - 1) {
            return nomeDoArquivo.substring(0, posicaoPonto);
        }

        return nomeDoArquivo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App janela = new App();
            janela.setVisible(true);
        });
    }
}
