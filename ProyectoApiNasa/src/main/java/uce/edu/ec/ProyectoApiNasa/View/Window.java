package uce.edu.ec.ProyectoApiNasa.View;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
public class Window extends JFrame {
    public JComboBox<String> camera;
    public JComboBox<String> rover;
    public JComboBox<Integer> sol;
    public JEditorPane resultPane;
    public JButton filterButton;

    public Window() {
        setTitle("Filtros con API NASA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Creación de listas inmutables para JComboBox
        List<String> cameraOptions = Collections.unmodifiableList(List.of("Todas", "FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
        List<String> roverOptions = Collections.unmodifiableList(List.of("Todos", "Curiosity", "Opportunity", "Spirit"));

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Espacio entre componentes
        filterPanel.setBackground(Color.LIGHT_GRAY);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        JLabel cameraLabel = new JLabel("Cámara:");
        cameraLabel.setForeground(Color.BLACK);
        camera = new JComboBox<>(cameraOptions.toArray(new String[0]));

        JLabel roverLabel = new JLabel("Rover:");
        roverLabel.setForeground(Color.BLACK);
        rover = new JComboBox<>(roverOptions.toArray(new String[0]));

        JLabel solLabel = new JLabel("Sol:");
        solLabel.setForeground(Color.BLACK);
        sol = new JComboBox<>(new Integer[]{1000, 1001, 1002});

        filterButton = new JButton("Filtrar");
        filterButton.setBackground(Color.DARK_GRAY);
        filterButton.setForeground(Color.WHITE);
        filterButton.setFocusPainted(false); // Quita el borde alrededor del texto del botón
        filterButton.addActionListener(e -> {
            System.out.println("Filtrando...");
        });

        filterPanel.add(roverLabel);
        filterPanel.add(rover);
        filterPanel.add(solLabel);
        filterPanel.add(sol);
        filterPanel.add(cameraLabel);
        filterPanel.add(camera);
        filterPanel.add(new JLabel()); // Espacio en blanco
        filterPanel.add(filterButton);

        resultPane = new JEditorPane();
        resultPane.setBackground(Color.WHITE);
        resultPane.setContentType("text/html");
        resultPane.setEditable(false);
        resultPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del scrollPane

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
