package uce.edu.ec.ProyectoApiNasa.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uce.edu.ec.ProyectoApiNasa.Consumer.NasaApiService;
import uce.edu.ec.ProyectoApiNasa.Model.MarsPhoto;
import uce.edu.ec.ProyectoApiNasa.View.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Controller
public class Container {
    private final NasaApiService nasaApiService;
    private final Window window;

    @Autowired
    public Container(NasaApiService nasaApiService, Window window) {
        this.nasaApiService = nasaApiService;
        this.window = window;
        this.window.filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });
    }

    private void applyFilters() {
        String camera = (String) window.camera.getSelectedItem();
        String rover = (String) window.rover.getSelectedItem();
        int sol = (Integer) window.sol.getSelectedItem();

        List<MarsPhoto> photos = nasaApiService.getPhotosByFilters(camera, rover, sol);

        StringBuilder resultHtml = new StringBuilder();
        resultHtml.append("<html><body><ul>");

        for (MarsPhoto photo : photos) {
            resultHtml.append("<li>")
                    .append("El link de la foto es").append("<br/>")
                    .append("URL: ").append("<a href=\"").append(photo.getImg_src()).append("\">").append(photo.getImg_src()).append("</a>")
                    .append("</li>");
        }

        resultHtml.append("</ul>");

        if (photos.isEmpty()) {
            resultHtml.append("No existen fotos con ese filtro.");
        }

        resultHtml.append("</body></html>");

        window.resultPane.setText(resultHtml.toString());
    }
}

