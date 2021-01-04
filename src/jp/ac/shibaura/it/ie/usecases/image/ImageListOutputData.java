package jp.ac.shibaura.it.ie.usecases.image;

import jp.ac.shibaura.it.ie.domain.model.image.Image;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import java.util.ArrayList;
import java.util.List;

public class ImageListOutputData implements OutputData {
    private List<Image> images = new ArrayList<>();
    public ImageListOutputData(List<Image> images){
        this.images = images;
    }

    public List<Image> getUrls() {
        return images;
    }
}
