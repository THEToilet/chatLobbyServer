package jp.ac.shibaura.it.ie.usecases.image;

import jp.ac.shibaura.it.ie.domain.model.image.Image;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import java.util.ArrayList;
import java.util.List;

public class ImageListOutputData implements OutputData {
    private List<Image> urls = new ArrayList<>();
    public ImageListOutputData(List<Image> urls){
        this.urls = urls;
    }

    public List<Image> getUrls() {
        return urls;
    }
}
