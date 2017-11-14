package diary.bean;

import javax.persistence.*;

/**
 * Created by MSI on 2017/11/12.
 */
@Entity
@Table(name="images")
public class Images {
    private int id;
    private String imageUrl;
    private String tumourUrl;
    private String fatUrl;
    private String ultrasonicResult;
    private String tumourResult;
    private String theriomaResult;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "tumour_url")
    public String getTumourUrl() {
        return tumourUrl;
    }

    public void setTumourUrl(String tumourUrl) {
        this.tumourUrl = tumourUrl;
    }

    @Basic
    @Column(name = "fat_url")
    public String getFatUrl() {
        return fatUrl;
    }

    public void setFatUrl(String fatUrl) {
        this.fatUrl = fatUrl;
    }

    @Basic
    @Column(name = "ultrasonic_result")
    public String getUltrasonicResult() {
        return ultrasonicResult;
    }

    public void setUltrasonicResult(String ultrasonicResult) {
        this.ultrasonicResult = ultrasonicResult;
    }

    @Basic
    @Column(name = "tumour_result")
    public String getTumourResult() {
        return tumourResult;
    }

    public void setTumourResult(String tumourResult) {
        this.tumourResult = tumourResult;
    }

    @Basic
    @Column(name = "therioma_result")
    public String getTheriomaResult() {
        return theriomaResult;
    }

    public void setTheriomaResult(String theriomaResult) {
        this.theriomaResult = theriomaResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Images images = (Images) o;

        if (id != images.id) return false;
        if (imageUrl != null ? !imageUrl.equals(images.imageUrl) : images.imageUrl != null) return false;
        if (tumourUrl != null ? !tumourUrl.equals(images.tumourUrl) : images.tumourUrl != null) return false;
        if (fatUrl != null ? !fatUrl.equals(images.fatUrl) : images.fatUrl != null) return false;
        if (ultrasonicResult != null ? !ultrasonicResult.equals(images.ultrasonicResult) : images.ultrasonicResult != null)
            return false;
        if (tumourResult != null ? !tumourResult.equals(images.tumourResult) : images.tumourResult != null)
            return false;
        if (theriomaResult != null ? !theriomaResult.equals(images.theriomaResult) : images.theriomaResult != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (tumourUrl != null ? tumourUrl.hashCode() : 0);
        result = 31 * result + (fatUrl != null ? fatUrl.hashCode() : 0);
        result = 31 * result + (ultrasonicResult != null ? ultrasonicResult.hashCode() : 0);
        result = 31 * result + (tumourResult != null ? tumourResult.hashCode() : 0);
        result = 31 * result + (theriomaResult != null ? theriomaResult.hashCode() : 0);
        return result;
    }
}
