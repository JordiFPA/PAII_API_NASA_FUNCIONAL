package uce.edu.ec.ProyectoApiNasa.Model;

public class MarsPhoto {
    private int id;
    private int sol;
    private String img_src;
    private String earth_date;
    private Camera camera;
    private Rover rover;

    public MarsPhoto(int id, int sol, String img_src, String earth_date, Camera camera, Rover rover) {
        this.id = id;
        this.sol = sol;
        this.img_src = img_src;
        this.earth_date = earth_date;
        this.camera = camera;
        this.rover = rover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getEarth_date() {
        return earth_date;
    }

    public void setEarth_date(String earth_date) {
        this.earth_date = earth_date;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }
}

