package jp.ac.shibaura.it.ie.usecases.image;

import jp.ac.shibaura.it.ie.usecases.core.OutputData;

import java.util.ArrayList;
import java.util.List;

public class ImageListOutputData implements OutputData {
    private List<String> urls = new ArrayList<>();
    public ImageListOutputData(List<String> urls){
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }
}
