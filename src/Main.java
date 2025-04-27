import org.json.JSONObject;
import org.json.XML;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("XML JSON konvertáló - v1.0");
            frame.setSize(650, 400);
            frame.setResizable(false);

            // kilépéskor (x) megerősítő ablak
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    Object[] options = {"Kilépés", "Mégsem"};
                    int valasz = JOptionPane.showOptionDialog(frame,
                            "Biztosan ki szeretnél lépni?",
                            "Kilépés megerősítése",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[1]);

                    if (valasz == 0) {
                        frame.dispose();
                    }
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel cimHely = new JLabel("Fájlok konvertálása", SwingConstants.CENTER);
            cimHely.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            cimHely.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel gombHely = new JPanel(new GridLayout(1, 3, 10, 10));

            JPanel balOszlop = new JPanel();

            JPanel kozepOszlop = new JPanel(new GridLayout(3, 1, 5, 5));

            JButton xmlJsonGomb = new JButton("XML -> JSON");
            xmlJsonGomb.setPreferredSize(new Dimension(250, 50));
            xmlJsonGomb.setFont(new Font("Arial", Font.BOLD, 18));
            xmlJsonGomb.addActionListener(e -> Xml2JsonKonvertalas());

            JButton jsonXmlGomb = new JButton("JSON -> XML");
            jsonXmlGomb.setPreferredSize(new Dimension(250, 50));
            jsonXmlGomb.setFont(new Font("Arial", Font.BOLD, 18));
            jsonXmlGomb.addActionListener(e -> Json2XmlKonvertalas());

            // kilépéskor (gomb) megerősítő ablak
            JButton kilepesGomb = new JButton("Kilépés");
            kilepesGomb.setPreferredSize(new Dimension(200, 40));
            kilepesGomb.setFont(new Font("Arial", Font.BOLD, 18));
            kilepesGomb.addActionListener(e -> {

                Object[] options = {"Kilépés", "Mégsem"};
                int valasz = JOptionPane.showOptionDialog(frame,
                        "Biztosan ki szeretnél lépni?",
                        "Kilépés megerősítése",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (valasz == JOptionPane.YES_OPTION) {
                    System.exit(0);
               }
            });

            kozepOszlop.add(xmlJsonGomb);
            kozepOszlop.add(jsonXmlGomb);
            kozepOszlop.add(kilepesGomb);

            JPanel jobbOszlop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton aboutButton = new JButton("Névjegy");
            aboutButton.setPreferredSize(new Dimension(100, 30));
            aboutButton.setFont(new Font("Arial", Font.BOLD, 12));
            aboutButton.addActionListener(e -> nevjegyAblak());
            jobbOszlop.add(aboutButton);

            gombHely.add(balOszlop);
            gombHely.add(kozepOszlop);
            gombHely.add(jobbOszlop);

            JLabel lablecSzoveg = new JLabel("Made by: Bártfai Attila - THE PTI @2025", SwingConstants.CENTER);
            lablecSzoveg.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            lablecSzoveg.setFont(new Font("Arial", Font.PLAIN, 16));

            panel.add(cimHely, BorderLayout.NORTH);
            panel.add(gombHely, BorderLayout.CENTER);
            panel.add(lablecSzoveg, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void Xml2JsonKonvertalas() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Kérlek válassz XML fájlt...", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        if (fileName != null) {
            File xmlFile = new File(directory, fileName);

            try {
                String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFile.getPath())));
                if (!xmlContent.trim().startsWith("<")) {
                    JOptionPane.showMessageDialog(null, "A kiválasztott fájl érvénytelen XML fájl!", "Hibás a fájlformátum", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JSONObject jsonObject = XML.toJSONObject(xmlContent);

                FileDialog saveDialog = new FileDialog((Frame) null, "JSON fájl mentése...", FileDialog.SAVE);
                saveDialog.setVisible(true);

                String saveDirectory = saveDialog.getDirectory();
                String saveFileName = saveDialog.getFile();

                if (saveFileName != null) {
                    File jsonFile = new File(saveDirectory, saveFileName);
                    try (FileWriter fileWriter = new FileWriter(jsonFile)) {
                        fileWriter.write(jsonObject.toString(4));
                        JOptionPane.showMessageDialog(null, "Az XML átalakítása elkészült!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Váratlan hiba történt: " + ex.getMessage());
            }
        }
    }

    private static void Json2XmlKonvertalas() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Kérlek válassz JSON fájlt...", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        if (fileName != null) {
            File jsonFile = new File(directory, fileName);

            try {
                String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFile.getPath())));
                if (!jsonContent.trim().startsWith("{") && !jsonContent.trim().startsWith("[")) {
                    JOptionPane.showMessageDialog(null, "A kiválasztott fájl érvénytelen JSON fájl!", "Hibás a fájlformátum", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JSONObject jsonObject = new JSONObject(jsonContent);
                String xmlContent = XML.toString(jsonObject);

                FileDialog saveDialog = new FileDialog((Frame) null, "XML fájl mentése...", FileDialog.SAVE);
                saveDialog.setVisible(true);

                String saveDirectory = saveDialog.getDirectory();
                String saveFileName = saveDialog.getFile();

                if (saveFileName != null) {
                    File xmlFile = new File(saveDirectory, saveFileName);
                    try (FileWriter fileWriter = new FileWriter(xmlFile)) {
                        fileWriter.write(xmlContent);
                        JOptionPane.showMessageDialog(null, "A JSON átalakítás elkészült!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Váratlan hiba történt: " + ex.getMessage());
            }
        }
    }

    private static void nevjegyAblak() {
        JDialog nevjegyAblak = new JDialog((Frame) null, "XML JSON konvertáló - Névjegy", true);
        nevjegyAblak.setSize(400, 170);
        nevjegyAblak.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel NevMezo = new JLabel("XML JSON konvertáló - v1.0");
        JLabel keszitoMezo = new JLabel("Készítette: Bártfai Attila");
        JLabel programMezo = new JLabel("Fejlesztőkörnyezet: IntelliJ IDEA 2024.3.4.1 / JetBrains");
        JLabel verzioMezo = new JLabel("Verzió: 1.0");
        JLabel evMEzo = new JLabel("Készítés éve: 2025");

        panel.add(NevMezo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(keszitoMezo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(programMezo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(verzioMezo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(evMEzo);

        nevjegyAblak.add(panel);
        nevjegyAblak.setVisible(true);
    }
}