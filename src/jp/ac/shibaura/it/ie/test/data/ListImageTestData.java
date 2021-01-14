package jp.ac.shibaura.it.ie.test.data;

import jp.ac.shibaura.it.ie.domain.model.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ListImageTestData {
    public List<ImageData> getUrls() {
        return urls;
    }

    public void setUrls(List<ImageData> urls) {
        this.urls = urls;
    }

    private List<ImageData> urls= new ArrayList<>();
}
