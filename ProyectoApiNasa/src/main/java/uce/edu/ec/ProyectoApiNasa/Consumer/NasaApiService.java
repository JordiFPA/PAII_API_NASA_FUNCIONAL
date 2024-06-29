package uce.edu.ec.ProyectoApiNasa.Consumer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uce.edu.ec.ProyectoApiNasa.Model.MarsPhoto;

@Service
public class NasaApiService {

    @Value("${nasa.api.key}")
    private String apiKey;

    @Value("${nasa.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public NasaApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MarsPhoto> getPhotosByFilters(String camera, String rover, int sol) {
        String url = buildUrl(camera, rover, sol);
        NasaResponse response = restTemplate.getForObject(url, NasaResponse.class);

        List<MarsPhoto> photos = response != null ? response.getPhotos() : Collections.emptyList();

        if (!rover.equals("All")) {
            photos = filterPhotosByRover(photos, rover);
        }

        return Collections.unmodifiableList(photos); // Lista inmutable después de filtrar
    }

    private String buildUrl(String camera, String rover, int sol) {
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?api_key=").append(apiKey).append("&sol=").append(sol);
        if (!camera.equals("All")) {
            urlBuilder.append("&camera=").append(camera);
        }
        return urlBuilder.toString();
    }

    private List<MarsPhoto> filterPhotosByRover(List<MarsPhoto> photos, String rover) {
        return photos.stream()
                .filter(photo -> photo.getRover().getName().equalsIgnoreCase(rover))
                .collect(Collectors.toList());
    }

    static class NasaResponse {
        private List<MarsPhoto> photos;

        public List<MarsPhoto> getPhotos() {
            return photos;
        }

        public void setPhotos(List<MarsPhoto> photos) {
            this.photos = photos;
        }
    }
}
